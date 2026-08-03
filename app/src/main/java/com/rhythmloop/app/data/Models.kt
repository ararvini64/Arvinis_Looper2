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
