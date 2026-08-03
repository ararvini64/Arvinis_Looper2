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
