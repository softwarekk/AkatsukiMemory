package com.young.supportlib.player.audio

import android.media.MediaPlayer

class PLDAudioPlayer : IAudioMap {

    private val mediaPlayer by lazy { MediaPlayer() }
    private lateinit var status:IAudioStatus

    constructor(statusCallBack : IAudioStatus){

    }


    override fun play(path: String) {
    }

    override fun pause() {
    }

    override fun stop() {
    }

    override fun seekTo(position: Int) {
    }

    override fun start() {
    }
}