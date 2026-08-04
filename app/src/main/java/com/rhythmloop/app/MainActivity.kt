package com.rhythmloop.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // تراک ریتم
    private var rhythmPlayer: MediaPlayer? = null
    private var isRhythmPlaying = false
    private var rhythmVolume = 1.0f
    private var isRhythmMuted = false
    private var currentRhythmPath: String? = null
    private var customRhythmUri: Uri? = null

    // تراک هارمونی
    private var harmonyPlayer: MediaPlayer? = null
    private var isHarmonyPlaying = false
    private var harmonyVolume = 1.0f
    private var isHarmonyMuted = false
    private var currentHarmonyPath: String? = null
    private var customHarmonyUri: Uri? = null

    // ویوها - ریتم
    private lateinit var categorySpinner: Spinner
    private lateinit var rhythmSpinner: Spinner
    private lateinit var btnRhythmPlayStop: Button
    private lateinit var rhythmVolumeSeekBar: SeekBar
    private lateinit var rhythmMuteButton: ToggleButton
    private lateinit var btnSelectCustomRhythm: Button
    private lateinit var txtCustomRhythm: TextView

    // ویوها - هارمونی
    private lateinit var harmonySpinner: Spinner
    private lateinit var btnHarmonyPlayStop: Button
    private lateinit var harmonyVolumeSeekBar: SeekBar
    private lateinit var harmonyMuteButton: ToggleButton
    private lateinit var btnSelectCustomHarmony: Button
    private lateinit var txtCustomHarmony: TextView

    // کنترل عمومی
    private lateinit var btnMasterPlay: Button
    private lateinit var btnMasterStop: Button
    private lateinit var favoriteButton: ImageButton
    private lateinit var favoritesContainer: LinearLayout
    private lateinit var bpmSeekBar: SeekBar
    private lateinit var bpmTextView: TextView

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("RhythmLoopPrefs", Context.MODE_PRIVATE)
    }

    private var currentCategory: String = ""
    private val favoritesSet = mutableSetOf<String>()

    // پیکر فایل ریتم شخصی
    private val customRhythmLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                customRhythmUri = uri
                txtCustomRhythm.text = "My Own Rhythm: ${uri.lastPathSegment ?: "Selected"}"
                txtCustomRhythm.visibility = View.VISIBLE
                if (isRhythmPlaying) {
                    playRhythm()
                }
            }
        }
    }

    // پیکر فایل هارمونی شخصی
    private val customHarmonyLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                customHarmonyUri = uri
                txtCustomHarmony.text = "My Own Harmony: ${uri.lastPathSegment ?: "Selected"}"
                txtCustomHarmony.visibility = View.VISIBLE
                if (isHarmonyPlaying) {
                    playHarmony()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        loadFavorites()
        setupSpinnersData()
        setupListeners()
    }

    private fun initViews() {
        categorySpinner = findViewById(R.id.categorySpinner)
        rhythmSpinner = findViewById(R.id.rhythmSpinner)
        btnRhythmPlayStop = findViewById(R.id.btnRhythmPlayStop)
        rhythmVolumeSeekBar = findViewById(R.id.rhythmVolumeSeekBar)
        rhythmMuteButton = findViewById(R.id.rhythmMuteButton)
        btnSelectCustomRhythm = findViewById(R.id.btnSelectCustomRhythm)
        txtCustomRhythm = findViewById(R.id.txtCustomRhythm)

        harmonySpinner = findViewById(R.id.harmonySpinner)
        btnHarmonyPlayStop = findViewById(R.id.btnHarmonyPlayStop)
        harmonyVolumeSeekBar = findViewById(R.id.harmonyVolumeSeekBar)
        harmonyMuteButton = findViewById(R.id.harmonyMuteButton)
        btnSelectCustomHarmony = findViewById(R.id.btnSelectCustomHarmony)
        txtCustomHarmony = findViewById(R.id.txtCustomHarmony)

        btnMasterPlay = findViewById(R.id.btnMasterPlay)
        btnMasterStop = findViewById(R.id.btnMasterStop)
        favoriteButton = findViewById(R.id.favoriteButton)
        favoritesContainer = findViewById(R.id.favoritesContainer)
        bpmSeekBar = findViewById(R.id.bpmSeekBar)
        bpmTextView = findViewById(R.id.bpmTextView)
    }

    private fun setupSpinnersData() {
        var categories = getAssetDirectories("rhythms")
        if (categories.isEmpty()) categories = listOf("persian", "arabic", "kurdish", "turkish")

        val catAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
        categorySpinner.adapter = catAdapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentCategory = categories[position]
                loadRhythmsForCategory(currentCategory)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        var harmonies = getAssetFiles("harmony")
        if (harmonies.isEmpty()) harmonies = listOf("h1.mp3", "h2.mp3", "h3.mp3")

        val harmonyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, harmonies.map { it.removeSuffix(".mp3") })
        harmonySpinner.adapter = harmonyAdapter
        harmonySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentHarmonyPath = "harmony/${harmonies[position]}"
                customHarmonyUri = null
                txtCustomHarmony.visibility = View.GONE
                if (isHarmonyPlaying) playHarmony()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun loadRhythmsForCategory(category: String) {
        val path = "rhythms/$category"
        var rhythms = getAssetFiles(path)
        if (rhythms.isEmpty()) rhythms = listOf("rhythm1.mp3", "rhythm2.mp3")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, rhythms.map { it.removeSuffix(".mp3") })
        rhythmSpinner.adapter = adapter

        rhythmSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentRhythmPath = "$path/${rhythms[position]}"
                customRhythmUri = null
                txtCustomRhythm.visibility = View.GONE
                updateFavoriteButtonState()
                if (isRhythmPlaying) playRhythm()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupListeners() {
        // استارت/استاپ مجزای ریتم
        btnRhythmPlayStop.setOnClickListener {
            if (isRhythmPlaying) stopRhythm() else playRhythm()
        }

        // استارت/استاپ مجزای هارمونی
        btnHarmonyPlayStop.setOnClickListener {
            if (isHarmonyPlaying) stopHarmony() else playHarmony()
        }

        // استارت/استاپ کلی
        btnMasterPlay.setOnClickListener {
            playRhythm()
            playHarmony()
        }

        btnMasterStop.setOnClickListener {
            stopRhythm()
            stopHarmony()
        }

        // انتخاب ریتم شخصی
        btnSelectCustomRhythm.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply { type = "audio/*" }
            customRhythmLauncher.launch(intent)
        }

        // انتخاب هارمونی شخصی
        btnSelectCustomHarmony.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply { type = "audio/*" }
            customHarmonyLauncher.launch(intent)
        }

        // ولوم و میوت ریتم
        rhythmVolumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rhythmVolume = progress / 100f
                if (!isRhythmMuted) rhythmPlayer?.setVolume(rhythmVolume, rhythmVolume)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        rhythmMuteButton.setOnCheckedChangeListener { _, isChecked ->
            isRhythmMuted = isChecked
            val vol = if (isRhythmMuted) 0f else rhythmVolume
            rhythmPlayer?.setVolume(vol, vol)
        }

        // ولوم و میوت هارمونی
        harmonyVolumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                harmonyVolume = progress / 100f
                if (!isHarmonyMuted) harmonyPlayer?.setVolume(harmonyVolume, harmonyVolume)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        harmonyMuteButton.setOnCheckedChangeListener { _, isChecked ->
            isHarmonyMuted = isChecked
            val vol = if (isHarmonyMuted) 0f else harmonyVolume
            harmonyPlayer?.setVolume(vol, vol)
        }

        // تنظیم سرعت
        bpmSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val speed = 0.5f + (progress / 100f)
                bpmTextView.text = "سرعت پخش: ${String.format("%.1f", speed)}x"
                setSpeed(speed)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        favoriteButton.setOnClickListener { toggleFavorite() }
    }

    private fun playRhythm() {
        stopRhythm()
        rhythmPlayer = if (customRhythmUri != null) {
            createMediaPlayerFromUri(customRhythmUri!!)
        } else {
            currentRhythmPath?.let { createMediaPlayerFromAsset(it) }
        }

        rhythmPlayer?.let {
            it.isLooping = true
            val vol = if (isRhythmMuted) 0f else rhythmVolume
            it.setVolume(vol, vol)
            it.start()
            isRhythmPlaying = true
            btnRhythmPlayStop.text = "⏹ توقف ریتم"
        }
    }

    private fun stopRhythm() {
        rhythmPlayer?.stop()
        rhythmPlayer?.release()
        rhythmPlayer = null
        isRhythmPlaying = false
        btnRhythmPlayStop.text = "▶ پخش ریتم"
    }

    private fun playHarmony() {
        stopHarmony()
        harmonyPlayer = if (customHarmonyUri != null) {
            createMediaPlayerFromUri(customHarmonyUri!!)
        } else {
            currentHarmonyPath?.let { createMediaPlayerFromAsset(it) }
        }

        harmonyPlayer?.let {
            it.isLooping = true
            val vol = if (isHarmonyMuted) 0f else harmonyVolume
            it.setVolume(vol, vol)
            it.start()
            isHarmonyPlaying = true
            btnHarmonyPlayStop.text = "⏹ توقف هارمونی"
        }
    }

    private fun stopHarmony() {
        harmonyPlayer?.stop()
        harmonyPlayer?.release()
        harmonyPlayer = null
        isHarmonyPlaying = false
        btnHarmonyPlayStop.text = "▶ پخش هارمونی"
    }

    private fun setSpeed(speed: Float) {
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
        } catch (e: Exception) { null }
    }

    private fun createMediaPlayerFromUri(uri: Uri): MediaPlayer? {
        return try {
            MediaPlayer().apply {
                setDataSource(applicationContext, uri)
                prepare()
            }
        } catch (e: Exception) { null }
    }

    private fun getAssetDirectories(path: String): List<String> {
        return try { assets.list(path)?.filter { !it.contains(".") } ?: emptyList() } catch (e: Exception) { emptyList() }
    }

    private fun getAssetFiles(path: String): List<String> {
        return try { assets.list(path)?.filter { it.endsWith(".mp3") } ?: emptyList() } catch (e: Exception) { emptyList() }
    }

    private fun loadFavorites() {
        val saved = sharedPreferences.getStringSet("favorites", emptySet()) ?: emptySet()
        favoritesSet.clear()
        favoritesSet.addAll(saved)
        updateFavoritesUI()
    }

    private fun toggleFavorite() {
        val rhythmName = customRhythmUri?.lastPathSegment ?: currentRhythmPath?.substringAfterLast("/") ?: "ریتم"
        val item = "$currentCategory / $rhythmName"

        if (favoritesSet.contains(item)) {
            favoritesSet.remove(item)
            Toast.makeText(this, "حذف شد", Toast.LENGTH_SHORT).show()
        } else {
            favoritesSet.add(item)
            Toast.makeText(this, "اضافه شد", Toast.LENGTH_SHORT).show()
        }

        sharedPreferences.edit().putStringSet("favorites", favoritesSet).apply()
        updateFavoriteButtonState()
        updateFavoritesUI()
    }

    private fun updateFavoriteButtonState() {
        val rhythmName = customRhythmUri?.lastPathSegment ?: currentRhythmPath?.substringAfterLast("/") ?: "ریتم"
        val item = "$currentCategory / $rhythmName"

        if (favoritesSet.contains(item)) {
            favoriteButton.setImageResource(android.R.drawable.btn_star_big_on)
        } else {
            favoriteButton.setImageResource(android.R.drawable.btn_star_big_off)
        }
    }

    private fun updateFavoritesUI() {
        favoritesContainer.removeAllViews()
        if (favoritesSet.isEmpty()) {
            val tv = TextView(this).apply {
                text = "لیست علاقه‌مندی‌ها خالی است."
                setTextColor(Color.GRAY)
                setPadding(16, 16, 16, 16)
            }
            favoritesContainer.addView(tv)
        } else {
            for (fav in favoritesSet) {
                val tv = TextView(this).apply {
                    text = "⭐ $fav"
                    setTextColor(Color.WHITE)
                    textSize = 15f
                    setPadding(16, 12, 16, 12)
                }
                favoritesContainer.addView(tv)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopRhythm()
        stopHarmony()
    }
}
