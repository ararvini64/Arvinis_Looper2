package com.rhythmplayer.app

import android.content.Context
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.max
import kotlin.math.roundToLong

data class RhythmItem(
    val id: String,
    val name: String,
    val category: String,
    val uri: Uri? = null,
    val isFavorite: Boolean = false,
    val startMs: Long = 0L,
    val endMs: Long = 0L,
    val originalDurationMs: Long = 0L
)

enum class Screen {
    MAIN_DASHBOARD,
    CATEGORY_DETAIL,
    MY_OWN,
    RHYTHM_STUDIO
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF121212)
                ) {
                    ArvinisRhythmApp()
                }
            }
        }
    }
}

@Composable
fun ArvinisRhythmApp() {
    var currentScreen by remember { mutableStateOf(Screen.MAIN_DASHBOARD) }
    var selectedCategory by remember { mutableStateOf("") }
    var selectedCategoryColor by remember { mutableStateOf(Color.Gray) }
    var activeStudioRhythm by remember { mutableStateOf<RhythmItem?>(null) }

    val myOwnRhythms = remember { mutableStateListOf<RhythmItem>() }

    when (currentScreen) {
        Screen.MAIN_DASHBOARD -> {
            MainDashboardScreen(
                onCategoryClick = { category, color ->
                    selectedCategory = category
                    selectedCategoryColor = color
                    if (category == "My Own") {
                        currentScreen = Screen.MY_OWN
                    } else {
                        currentScreen = Screen.CATEGORY_DETAIL
                    }
                }
            )
        }

        Screen.CATEGORY_DETAIL -> {
            CategoryDetailScreen(
                categoryName = selectedCategory,
                themeColor = selectedCategoryColor,
                onBack = { currentScreen = Screen.MAIN_DASHBOARD }
            )
        }

        Screen.MY_OWN -> {
            MyOwnScreen(
                rhythms = myOwnRhythms,
                onBack = { currentScreen = Screen.MAIN_DASHBOARD },
                onAddRhythm = { newRhythm ->
                    myOwnRhythms.add(newRhythm)
                    activeStudioRhythm = newRhythm
                    currentScreen = Screen.RHYTHM_STUDIO
                },
                onOpenStudio = { rhythm ->
                    activeStudioRhythm = rhythm
                    currentScreen = Screen.RHYTHM_STUDIO
                }
            )
        }

        Screen.RHYTHM_STUDIO -> {
            activeStudioRhythm?.let { rhythm ->
                RhythmStudioScreen(
                    rhythm = rhythm,
                    onBack = { currentScreen = Screen.MY_OWN },
                    onSave = { updatedRhythm ->
                        val index = myOwnRhythms.indexOfFirst { it.id == updatedRhythm.id }
                        if (index != -1) {
                            myOwnRhythms[index] = updatedRhythm
                        }
                        currentScreen = Screen.MY_OWN
                    }
                )
            }
        }
    }
}

@Composable
fun MainDashboardScreen(onCategoryClick: (String, Color) -> Unit) {
    // تغییر رنگ کارت My Own به زرد برای تشخیص آنی
    val categories = listOf(
        "Persian" to Color(0xFF1E88E5),
        "Kurdish" to Color(0xFF4CAF50),
        "Turkish" to Color(0xFFE53935),
        "Azeri" to Color(0xFFFB8C00),
        "Arabic" to Color(0xFF8E24AA),
        "European" to Color(0xFF00ACC1),
        "My Own" to Color(0xFFFFEB3B), // رنگ زرد فسفری
        "Favorites" to Color(0xFFFDD835)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // تیتر جدید با علامت‌های ضربدر ❌
        Text(
            text = "❌ Arvinis Rhythm Player [X-TEST-BUILD-v4] ❌",
            color = Color.Yellow,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                .padding(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(categories) { (name, color) ->
                val textColor = if (name == "My Own") Color.Black else Color.White
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .background(color, RoundedCornerShape(12.dp))
                        .clickable { onCategoryClick(name, color) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = name,
                        color = textColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryDetailScreen(
    categoryName: String,
    themeColor: Color,
    onBack: () -> Unit
) {
    var tempo by remember { mutableFloatStateOf(100f) }
    var volume by remember { mutableFloatStateOf(80f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "(Back)", tint = Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$categoryName (Rhythms)",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("(Tempo): ${tempo.toInt()}", color = Color.LightGray, fontSize = 12.sp)
                Slider(value = tempo, onValueChange = { tempo = it }, valueRange = 50f..180f)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Button(
                    onClick = { tempo = (tempo - 1).coerceAtLeast(50f) },
                    contentPadding = PaddingValues(4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF333333))
                ) { Text("(-T)", fontSize = 11.sp) }

                Button(
                    onClick = { tempo = (tempo + 1).coerceAtMost(180f) },
                    contentPadding = PaddingValues(4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF333333))
                ) { Text("(+T)", fontSize = 11.sp) }
            }

            Column(modifier = Modifier.weight(1f)) {
                Text("(Volume): ${volume.toInt()}%", color = Color.LightGray, fontSize = 12.sp)
                Slider(value = volume, onValueChange = { volume = it }, valueRange = 0f..100f)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val sampleRhythms = listOf("R4", "R5", "Dsa Edited")
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(sampleRhythms) { rhythmName ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .background(themeColor.copy(alpha = 0.8f), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(rhythmName, color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

fun getAudioDuration(context: Context, uri: Uri): Long {
    return try {
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(context, uri)
        val time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        retriever.release()
        time?.toLong() ?: 180000L
    } catch (e: Exception) {
        180000L
    }
}

@Composable
fun MyOwnScreen(
    rhythms: List<RhythmItem>,
    onBack: () -> Unit,
    onAddRhythm: (RhythmItem) -> Unit,
    onOpenStudio: (RhythmItem) -> Unit
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            val fileName = uri.lastPathSegment?.substringAfterLast('/') ?: "Custom Rhythm"
            val duration = getAudioDuration(context, uri)

            val newRhythm = RhythmItem(
                id = System.currentTimeMillis().toString(),
                name = fileName,
                category = "My Own",
                uri = uri,
                startMs = 0L,
                endMs = duration,
                originalDurationMs = duration
            )
            onAddRhythm(newRhythm)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "(Back)", tint = Color.White)
                }
                Text("(My Own Rhythms)", color = Color(0xFFD81B60), fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { launcher.launch("audio/*") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD81B60))
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text("(+ Add Rhythm)")
            }
        }

        if (rhythms.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    "هنوز ریتمی اضافه نشده است.\nدکمه (+ Add Rhythm) را بزنید.",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(rhythms) { rhythm ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .background(Color(0xFFD81B60).copy(alpha = 0.8f), RoundedCornerShape(10.dp))
                            .clickable { onOpenStudio(rhythm) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(rhythm.name, color = Color.White, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
            }
        }
    }
}

@Composable
fun RhythmStudioScreen(
    rhythm: RhythmItem,
    onBack: () -> Unit,
    onSave: (RhythmItem) -> Unit
) {
    val context = LocalContext.current
    val maxDurationMs = max(1L, rhythm.originalDurationMs)

    var startMs by remember(rhythm.id) {
        mutableLongStateOf(rhythm.startMs.coerceIn(0L, max(0L, maxDurationMs - 100L)))
    }
    var endMs by remember(rhythm.id) {
        mutableLongStateOf(
            if (rhythm.endMs > 0L) rhythm.endMs.coerceIn(100L, maxDurationMs) else maxDurationMs
        )
    }

    var isPlaying by remember { mutableStateOf(false) }
    var currentPositionMs by remember { mutableLongStateOf(startMs) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    DisposableEffect(rhythm.uri) {
        if (rhythm.uri != null) {
            try {
                val player = MediaPlayer().apply {
                    setDataSource(context, rhythm.uri)
                    prepare()
                }
                mediaPlayer = player
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    LaunchedEffect(isPlaying, startMs, endMs) {
        while (isPlaying) {
            mediaPlayer?.let { player ->
                val pos = player.currentPosition.toLong()
                currentPositionMs = pos

                if (pos >= endMs || pos < startMs) {
                    player.seekTo(startMs.toInt())
                }
            }
            delay(30)
        }
    }

    fun updateStart(newVal: Long) {
        val minAllowed = 0L
        val maxAllowed = max(0L, endMs - 100L)
        val clamped = newVal.coerceIn(minAllowed, maxAllowed)
        startMs = clamped

        mediaPlayer?.let { player ->
            if (player.currentPosition < startMs) {
                player.seekTo(startMs.toInt())
            }
        }
    }

    fun updateEnd(newVal: Long) {
        val minAllowed = startMs + 100L
        val maxAllowed = maxDurationMs
        val clamped = newVal.coerceIn(minAllowed, maxAllowed)
        endMs = clamped

        mediaPlayer?.let { player ->
            if (player.currentPosition > endMs) {
                player.seekTo(startMs.toInt())
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181818))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                mediaPlayer?.stop()
                onBack()
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "(Back)", tint = Color.White)
            }
            Text("(Rhythm Studio)", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Button(onClick = {
                mediaPlayer?.stop()
                onSave(rhythm.copy(startMs = startMs, endMs = endMs))
            }) {
                Text("ذخیره")
            }
        }

        Text("نام فایل: ${rhythm.name} | طول کل: ${maxDurationMs}ms", color = Color.LightGray, fontSize = 13.sp)

        Button(
            onClick = {
                mediaPlayer?.let { player ->
                    if (isPlaying) {
                        player.pause()
                        isPlaying = false
                    } else {
                        player.seekTo(startMs.toInt())
                        player.start()
                        isPlaying = true
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = if (isPlaying) Color.Red else Color(0xFF4CAF50))
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = null)
            Spacer(modifier = Modifier.width(6.dp))
            Text(if (isPlaying) "توقف پخش" else "پخش لوپ کات‌شده")
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("(Start Point): ${startMs}ms (${String.format("%.2f", startMs / 1000f)}s)", color = Color.Green, fontSize = 13.sp)
            Text("(Current): ${currentPositionMs}ms", color = Color.Yellow, fontSize = 13.sp)
            Text("(End Point): ${endMs}ms (${String.format("%.2f", endMs / 1000f)}s)", color = Color.Red, fontSize = 13.sp)
        }

        Column {
            Text("تنظیم کلی نقطه شروع (Start)", color = Color.Green, fontSize = 11.sp)
            Slider(
                value = startMs.toFloat(),
                onValueChange = { updateStart(it.roundToLong()) },
                valueRange = 0f..maxDurationMs.toFloat()
            )

            Text("تنظیم کلی نقطه پایان (End)", color = Color.Red, fontSize = 11.sp)
            Slider(
                value = endMs.toFloat(),
                onValueChange = { updateEnd(it.roundToLong()) },
                valueRange = 0f..maxDurationMs.toFloat()
            )
        }

        HorizontalDivider(color = Color.DarkGray)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FineTuneBlock(
                title = "(Fine-Tune Start)",
                color = Color.Green,
                onAdjust = { delta -> updateStart(startMs + delta) }
            )

            FineTuneBlock(
                title = "(Fine-Tune End)",
                color = Color.Red,
                onAdjust = { delta -> updateEnd(endMs + delta) }
            )
        }
    }
}

@Composable
fun FineTuneBlock(title: String, color: Color, onAdjust: (Long) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(title, color = color, fontSize = 12.sp)
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Button(onClick = { onAdjust(-100L) }, contentPadding = PaddingValues(2.dp)) { Text("(-100ms)", fontSize = 10.sp) }
            Button(onClick = { onAdjust(-10L) }, contentPadding = PaddingValues(2.dp)) { Text("(-10ms)", fontSize = 10.sp) }
            Button(onClick = { onAdjust(10L) }, contentPadding = PaddingValues(2.dp)) { Text("(+10ms)", fontSize = 10.sp) }
            Button(onClick = { onAdjust(100L) }, contentPadding = PaddingValues(2.dp)) { Text("(+100ms)", fontSize = 10.sp) }
        }
    }
}
