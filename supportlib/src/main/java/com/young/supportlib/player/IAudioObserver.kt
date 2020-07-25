package com.young.supportlib.player

interface IAudioObserver {
    fun onNextPlaying(playingPosition:Int)//每播放新歌都会回调新歌position
}