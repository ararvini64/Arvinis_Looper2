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
