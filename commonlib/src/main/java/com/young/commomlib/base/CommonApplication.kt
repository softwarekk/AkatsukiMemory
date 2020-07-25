package com.young.commomlib.base

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.billy.cc.core.component.BuildConfig
import com.billy.cc.core.component.CC
import com.young.baselib.BaseApplication
import com.young.commomlib.viewmodel.CommonShareVM

/*
 * Des common业务基建
 * Author Young
 * Date 2020-05-18
 */abstract class CommonApplication :BaseApplication(){


    override fun onCreate() {
        super.onCreate()
        CC.enableDebug(BuildConfig.DEBUG)
        CC.enableVerboseLog(BuildConfig.DEBUG)
        CC.enableRemoteCC(BuildConfig.DEBUG)
    }

}