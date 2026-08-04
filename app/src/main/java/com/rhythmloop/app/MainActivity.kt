package com.rhythmloop.app

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var rhythmPlayer: MediaPlayer? = null
    private var harmonyPlayer: MediaPlayer? = null

    private lateinit var categorySpinner: Spinner
    private lateinit var rhythmSpinner: Spinner
    private lateinit var harmonySpinner: Spinner
    private lateinit var playButton: Button
    private lateinit var stopButton: Button
    private lateinit var favoriteButton: ImageButton
    private lateinit var favoritesListView: ListView
    private lateinit var bpmSeekBar: SeekBar
    private lateinit var bpmTextView: TextView

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("RhythmLoopPrefs", Context.MODE_PRIVATE)
    }

    private var currentCategory: String = ""
    private var currentRhythmPath: String? = null
    private var currentHarmonyPath: String? = null

    private val favoritesSet = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        loadFavorites()
        setupAssetsData()
        setupListeners()
    }

    private fun initViews() {
        categorySpinner = findViewById(R.id.categorySpinner)
        rhythmSpinner = findViewById(R.id.rhythmSpinner)
        harmonySpinner = findViewById(R.id.harmonySpinner)
        playButton = findViewById(R.id.playButton)
        stopButton = findViewById(R.id.stopButton)
        favoriteButton = findViewById(R.id.favoriteButton)
        favoritesListView = findViewById(R.id.favoritesListView)
        bpmSeekBar = findViewById(R.id.bpmSeekBar)
        bpmTextView = findViewById(R.id.bpmTextView)
    }

    private fun setupAssetsData() {
        // ۱. اسکن پوشه‌های سبک‌ها (مثل persian, arabic و ...)
        val categories = getAssetDirectories("rhythms")
        if (categories.isNotEmpty()) {
            val catAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
            categorySpinner.adapter = catAdapter

            categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    currentCategory = categories[position]
                    loadRhythmsForCategory(currentCategory)
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }

        // ۲. بارگذاری تمام فایل‌های آکورد (Harmony)
        val harmonies = getAssetFiles("harmony")
        val harmonyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, harmonies.map { it.removeSuffix(".mp3") })
        harmonySpinner.adapter = harmonyAdapter
        harmonySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentHarmonyPath = "harmony/${harmonies[position]}"
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun loadRhythmsForCategory(category: String) {
        val path = "rhythms/$category"
        val rhythms = getAssetFiles(path)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, rhythms.map { it.removeSuffix(".mp3") })
        rhythmSpinner.adapter = adapter

        rhythmSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentRhythmPath = "$path/${rhythms[position]}"
                updateFavoriteButtonState()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupListeners() {
        playButton.setOnClickListener {
            startLoopPlayback()
        }

        stopButton.setOnClickListener {
            stopLoopPlayback()
        }

        favoriteButton.setOnClickListener {
            toggleFavorite()
        }

        bpmSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val speed = 0.5f + (progress / 100f) // از 0.5x تا 1.5x
                bpmTextView.text = "Speed: ${String.format("%.1f", speed)}x"
                setPlaybackSpeed(speed)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun startLoopPlayback() {
        stopLoopPlayback()

        currentRhythmPath?.let { rhythm ->
            rhythmPlayer = createMediaPlayerFromAsset(rhythm)?.apply {
                isLooping = true
                start()
            }
        }

        currentHarmonyPath?.let { harmony ->
            harmonyPlayer = createMediaPlayerFromAsset(harmony)?.apply {
                isLooping = true
                start()
            }
        }
    }

    private fun stopLoopPlayback() {
        rhythmPlayer?.stop()
        rhythmPlayer?.release()
        rhythmPlayer = null

        harmonyPlayer?.stop()
        harmonyPlayer?.release()
        harmonyPlayer = null
    }

    private fun setPlaybackSpeed(speed: Float) {
        try {
            rhythmPlayer?.playbackParams = rhythmPlayer?.playbackParams?.setSpeed(speed) ?: return
            harmonyPlayer?.playbackParams = harmonyPlayer?.playbackParams?.setSpeed(speed) ?: return
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun createMediaPlayerFromAsset(assetPath: String): MediaPlayer? {
        return try {
            val afd = assets.openFd(assetPath)
            MediaPlayer().apply {
                setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                prepare()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "خطا در بارگذاری فایل: $assetPath", Toast.LENGTH_SHORT).show()
            null
        }
    }

    private fun getAssetDirectories(path: String): List<String> {
        return try {
            assets.list(path)?.filter { !it.contains(".") } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun getAssetFiles(path: String): List<String> {
        return try {
            assets.list(path)?.filter { it.endsWith(".mp3") } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    // --- مدیریت علاقه‌مندی‌ها (Favorites) ---
    private fun loadFavorites() {
        val saved = sharedPreferences.getStringSet("favorites", emptySet()) ?: emptySet()
        favoritesSet.clear()
        favoritesSet.addAll(saved)
        updateFavoritesList()
    }

    private fun toggleFavorite() {
        val item = "$currentCategory | ${currentRhythmPath?.substringAfterLast("/")}"
        if (favoritesSet.contains(item)) {
            favoritesSet.remove(item)
            Toast.makeText(this, "از علاقه‌مندی‌ها حذف شد", Toast.LENGTH_SHORT).show()
        } else {
            favoritesSet.add(item)
            Toast.makeText(this, "به علاقه‌مندی‌ها اضافه شد", Toast.LENGTH_SHORT).show()
        }
        sharedPreferences.edit().putStringSet("favorites", favoritesSet).apply()
        updateFavoriteButtonState()
        updateFavoritesList()
    }

    private fun updateFavoriteButtonState() {
        val item = "$currentCategory | ${currentRhythmPath?.substringAfterLast("/")}"
        if (favoritesSet.contains(item)) {
            favoriteButton.setImageResource(android.R.drawable.btn_star_big_on)
        } else {
            favoriteButton.setImageResource(android.R.drawable.btn_star_big_off)
        }
    }

    private fun updateFavoritesList() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, favoritesSet.toList())
        favoritesListView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLoopPlayback()
    }
}
