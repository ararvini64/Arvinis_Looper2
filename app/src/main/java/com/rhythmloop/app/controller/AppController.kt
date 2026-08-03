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
