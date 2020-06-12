package com.young.businessmine

import com.young.commomlib.base.CommonApplication
import com.young.supportlib.AudioPlayer

class BusinessMainApplication : CommonApplication() {
    override fun onCreate() {
        super.onCreate()
        AudioPlayer.initAudio(this)
    }
}