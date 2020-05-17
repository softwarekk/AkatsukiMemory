package com.young.baselib

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Process
import com.billy.cc.core.component.CC

/**
 */
open class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CC.enableDebug(BuildConfig.DEBUG)
        CC.enableVerboseLog(BuildConfig.DEBUG)
        CC.enableRemoteCC(BuildConfig.DEBUG)
        sApplication = this
    }

    companion object {
        var sApplication: BaseApplication? = null
        var sIsDebug = false
        fun setIsDebug(isDebug: Boolean) {
            sIsDebug = isDebug
        }

        /**
         * 获取进程名
         *
         * @param context
         * @return
         */
        fun getCurrentProcessName(context: Context): String? {
            val am =
                context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val runningApps =
                am.runningAppProcesses ?: return null
            for (proInfo in runningApps) {
                if (proInfo.pid == Process.myPid()) {
                    if (proInfo.processName != null) {
                        return proInfo.processName
                    }
                }
            }
            return null
        }
    }
}