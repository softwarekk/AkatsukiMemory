package com.young.supportlib.player.audio

/*
* 播放器业务状态
* */
interface IAudioStatus {
    fun onComplete()
    fun seekPosition(position:Int)//单位秒
    fun buffingPosition(position:Int)//单位秒
}