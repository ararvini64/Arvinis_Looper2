#!/bin/bash

# ۱. ساخت پوشه‌ها
mkdir -p .github/workflows
mkdir -p gradle/wrapper
mkdir -p app/src/main/assets/rhythms/Persian
mkdir -p app/src/main/assets/harmony
mkdir -p app/src/main/java/com/rhythmloop/app/data
mkdir -p app/src/main/java/com/rhythmloop/app/engine
mkdir -p app/src/main/java/com/rhythmloop/app/controller
mkdir -p app/src/main/res/layout

# ۲. فایل settings.gradle.kts
cat << 'EOF' > settings.gradle.kts
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Rhythm Loop App"
include(":app")
EOF

# ۳. فایل build.gradle.kts (ریشه)
cat << 'EOF' > build.gradle.kts
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
}
EOF

# ۴. فایل gradlew
cat << 'EOF' > gradlew
#!/usr/bin/env sh
exec gradle "$@"
EOF
chmod +x gradlew

# ۵. فایل gradle-wrapper.properties
cat << 'EOF' > gradle/wrapper/gradle-wrapper.properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.2-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
EOF

# ۶. فایل app/build.gradle.kts
cat << 'EOF' > app/build.gradle.kts
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.rhythmloop.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rhythmloop.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
}
EOF

# ۷. فایل AndroidManifest.xml
cat << 'EOF' > app/src/main/AndroidManifest.xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.rhythmloop.app">

    <application
        android:allowBackup="true"
        android:label="Rhythm Loop"
        android:supportsRtl="true"
        android:theme="@style/Theme.AppCompat.Light.NoActionBar">
        
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:screenOrientation="landscape">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
EOF

# ۸. فایل activity_main.xml
cat << 'EOF' > app/src/main/res/layout/activity_main.xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal"
    android:padding="16dp"
    android:background="#121212">

    <LinearLayout
        android:layout_width="0dp"
        android:layout_height="match_parent"
        android:layout_weight="1"
        android:orientation="vertical"
        android:padding="8dp">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Rhythm Engine"
            android:textColor="#FFFFFF"
            android:textSize="20sp"
            android:textStyle="bold" />

        <TextView
            android:id="@+id/txtBpm"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="BPM: 90"
            android:textColor="#00E676"
            android:textSize="18sp"
            android:layout_marginTop="8dp"/>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            android:layout_marginTop="8dp">

            <Button
                android:id="@+id/btnBpmMinus10"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="-10" />

            <Button
                android:id="@+id/btnBpmPlus10"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="+10" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            android:layout_marginTop="16dp">

            <Button
                android:id="@+id/btnRhythmPlay"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="PLAY" />

            <Button
                android:id="@+id/btnRhythmStop"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="STOP" />
        </LinearLayout>
    </LinearLayout>

    <View
        android:layout_width="1dp"
        android:layout_height="match_parent"
        android:background="#333333"/>

    <LinearLayout
        android:layout_width="0dp"
        android:layout_height="match_parent"
        android:layout_weight="1"
        android:orientation="vertical"
        android:padding="8dp">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Harmony Engine"
            android:textColor="#FFFFFF"
            android:textSize="20sp"
            android:textStyle="bold" />

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            android:layout_marginTop="16dp">

            <Button
                android:id="@+id/btnHarmonyPlay"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="PLAY" />

            <Button
                android:id="@+id/btnHarmonyStop"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="STOP" />
        </LinearLayout>
    </LinearLayout>
</LinearLayout>
EOF

# ۹. فایل Models.kt
cat << 'EOF' > app/src/main/java/com/rhythmloop/app/data/Models.kt
package com.rhythmloop.app.data

enum class PlaybackState { STOPPED, READY, PLAYING, PAUSED }
enum class RhythmCategory { PERSIAN, TURKISH, AZERI, KURDISH, ARABIC, EUROPEAN, MY_OWN, FAVORITES }

data class FavoriteModel(
    val id: String,
    val category: RhythmCategory,
    val rhythmFile: String,
    val harmonyFile: String? = null,
    val bpm: Int,
    val rhythmVolume: Float,
    val harmonyVolume: Float
)
EOF

# ۱۰. فایل RhythmEngine.kt
cat << 'EOF' > app/src/main/java/com/rhythmloop/app/engine/RhythmEngine.kt
package com.rhythmloop.app.engine

import android.content.Context
import android.media.MediaPlayer
import android.media.PlaybackParams
import com.rhythmloop.app.data.PlaybackState

class RhythmEngine(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null
    var currentState: PlaybackState = PlaybackState.STOPPED
        private set
    var currentBpm: Int = 90
        private set
    private var defaultBpm: Int = 90
    var volume: Float = 1.0f
        private set

    fun loadRhythm(assetPath: String, defaultBpm: Int = 90) {
        stop()
        this.defaultBpm = defaultBpm
        this.currentBpm = defaultBpm
        val descriptor = context.assets.openFd(assetPath)
        mediaPlayer = MediaPlayer().apply {
            setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
            isLooping = true
            prepare()
        }
        descriptor.close()
        currentState = PlaybackState.READY
    }

    fun play() {
        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.setVolume(volume, volume)
                applyPlaybackParams()
                it.start()
                currentState = PlaybackState.PLAYING
            }
        }
    }

    fun stop() {
        mediaPlayer?.let {
            if (it.isPlaying) it.stop()
            it.release()
        }
        mediaPlayer = null
        currentState = PlaybackState.STOPPED
    }

    fun setBpm(newBpm: Int) {
        if (newBpm in 40..240) {
            currentBpm = newBpm
            applyPlaybackParams()
        }
    }

    private fun applyPlaybackParams() {
        mediaPlayer?.let {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                val speed = currentBpm.toFloat() / defaultBpm.toFloat()
                it.playbackParams = PlaybackParams().apply { this.speed = speed }
            }
        }
    }
}
EOF

# ۱۱. فایل HarmonyEngine.kt
cat << 'EOF' > app/src/main/java/com/rhythmloop/app/engine/HarmonyEngine.kt
package com.rhythmloop.app.engine

import android.content.Context
import android.media.MediaPlayer
import com.rhythmloop.app.data.PlaybackState

class HarmonyEngine(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null
    var currentState: PlaybackState = PlaybackState.STOPPED
        private set
    var volume: Float = 1.0f
        private set

    fun loadHarmony(assetPath: String) {
        stop()
        val descriptor = context.assets.openFd(assetPath)
        mediaPlayer = MediaPlayer().apply {
            setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
            isLooping = true
            prepare()
        }
        descriptor.close()
        currentState = PlaybackState.READY
    }

    fun play() {
        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.setVolume(volume, volume)
                it.start()
                currentState = PlaybackState.PLAYING
            }
        }
    }

    fun stop() {
        mediaPlayer?.let {
            if (it.isPlaying) it.stop()
            it.release()
        }
        mediaPlayer = null
        currentState = PlaybackState.STOPPED
    }
}
EOF

# ۱۲. فایل AppController.kt
cat << 'EOF' > app/src/main/java/com/rhythmloop/app/controller/AppController.kt
package com.rhythmloop.app.controller

import android.content.Context
import com.rhythmloop.app.engine.HarmonyEngine
import com.rhythmloop.app.engine.RhythmEngine

class AppController(context: Context) {
    val rhythmEngine = RhythmEngine(context)
    val harmonyEngine = HarmonyEngine(context)

    fun stopAll() {
        rhythmEngine.stop()
        harmonyEngine.stop()
    }
}
EOF

# ۱۳. فایل MainActivity.kt
cat << 'EOF' > app/src/main/java/com/rhythmloop/app/MainActivity.kt
package com.rhythmloop.app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rhythmloop.app.controller.AppController

class MainActivity : AppCompatActivity() {
    private lateinit var controller: AppController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller = AppController(this)

        val txtBpm = findViewById<TextView>(R.id.txtBpm)
        val btnBpmMinus10 = findViewById<Button>(R.id.btnBpmMinus10)
        val btnBpmPlus10 = findViewById<Button>(R.id.btnBpmPlus10)
        
        val btnRhythmPlay = findViewById<Button>(R.id.btnRhythmPlay)
        val btnRhythmStop = findViewById<Button>(R.id.btnRhythmStop)
        
        val btnHarmonyPlay = findViewById<Button>(R.id.btnHarmonyPlay)
        val btnHarmonyStop = findViewById<Button>(R.id.btnHarmonyStop)

        btnBpmMinus10.setOnClickListener {
            controller.rhythmEngine.setBpm(controller.rhythmEngine.currentBpm - 10)
            txtBpm.text = "BPM: ${controller.rhythmEngine.currentBpm}"
        }

        btnBpmPlus10.setOnClickListener {
            controller.rhythmEngine.setBpm(controller.rhythmEngine.currentBpm + 10)
            txtBpm.text = "BPM: ${controller.rhythmEngine.currentBpm}"
        }

        btnRhythmPlay.setOnClickListener {
            try {
                if (controller.rhythmEngine.currentState == com.rhythmloop.app.data.PlaybackState.STOPPED) {
                    controller.rhythmEngine.loadRhythm("rhythms/Persian/P1.mp3")
                }
                controller.rhythmEngine.play()
            } catch (e: Exception) {
                Toast.makeText(this, "فایل صوتی یافت نشد.", Toast.LENGTH_SHORT).show()
            }
        }

        btnRhythmStop.setOnClickListener { controller.rhythmEngine.stop() }

        btnHarmonyPlay.setOnClickListener {
            try {
                if (controller.harmonyEngine.currentState == com.rhythmloop.app.data.PlaybackState.STOPPED) {
                    controller.harmonyEngine.loadHarmony("harmony/H1.mp3")
                }
                controller.harmonyEngine.play()
            } catch (e: Exception) {
                Toast.makeText(this, "فایل هارمونی یافت نشد.", Toast.LENGTH_SHORT).show()
            }
        }

        btnHarmonyStop.setOnClickListener { controller.harmonyEngine.stop() }
    }

    override fun onDestroy() {
        super.onDestroy()
        controller.stopAll()
    }
}
EOF

# ۱۴. فایل بیلد GitHub Actions (.github/workflows/build.yml)
cat << 'EOF' > .github/workflows/build.yml
name: Build Android APK

on:
  push:
    branches: [ "main", "master" ]
  pull_request:
    branches: [ "main", "master" ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout Repository
        uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Setup Gradle
        uses: gradle/actions/setup-gradle@v3

      - name: Build Debug APK
        run: gradle assembleDebug

      - name: Upload APK
        uses: actions/upload-artifact@v4
        with:
          name: RhythmLoopApp-APK
          path: app/build/outputs/apk/debug/app-debug.apk
EOF

echo "تمامی پوشه‌ها و فایل‌ها با موفقیت ساخته شدند!"