package com.young.supportlib.player.audio

/*
* 所有的播放器（mediaplayer实现、或者其他IJK实现也好、pl实现也好）实现的功能
* 都通过这个接口映射实现
* */
interface IAudioMap {
    fun  play(path:String)
    fun pause()
    fun stop()
    fun seekTo(position:Int)
    fun start()
}