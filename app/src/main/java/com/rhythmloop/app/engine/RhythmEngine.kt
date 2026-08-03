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
