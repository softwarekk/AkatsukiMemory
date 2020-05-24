package com.young.commomlib.base

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import com.billy.cc.core.component.BuildConfig
import com.billy.cc.core.component.CC
import com.young.baselib.BaseApplication

/*
 * Des 业务基建
 * Author Young
 * Date 2020-05-18
 */abstract class CommonApplication :BaseApplication(){
    override fun onCreate() {
        super.onCreate()
        CC.enableDebug(BuildConfig.DEBUG)
        CC.enableVerboseLog(BuildConfig.DEBUG)
        CC.enableRemoteCC(BuildConfig.DEBUG)
    }



    override fun getAppViewModelProvider(activity: Activity): ViewModelProvider? {
        return super.getAppViewModelProvider(activity)
    }

    override fun getAppFactory(activity: Activity): ViewModelProvider.Factory {
        return super.getAppFactory(activity)
    }
}