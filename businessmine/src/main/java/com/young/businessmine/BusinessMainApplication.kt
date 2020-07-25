package com.young.businessmine

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.young.baselib.BaseApplication
import com.young.businessmine.ui.viewmodel.ContainerShareVM
import com.young.businessmine.ui.viewmodel.ContainerUIViewModel
import com.young.businessmine.utils.AssetsUtils
import com.young.commomlib.base.CommonApplication
import com.young.supportlib.AudioPlayer
import com.young.supportlib.player.AppAudioManager

class BusinessMainApplication : CommonApplication() {
    override fun onCreate() {
        super.onCreate()
        AudioPlayer.initAudio(this)
        AppAudioManager.init(this)
        val vm= appVMProvider()?.get(ContainerUIViewModel::class.java)
    }
    fun appVMProvider(): ViewModelProvider? {
        return ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        )
    }
}