package com.young.businessmine.utils

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Closeable
import java.io.IOException


class AssetsUtils {

    /**
     * 获取assets目录下的音频资源
     *
     * @param fileName
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getAudio(context: Context, fileName: String?): AssetFileDescriptor? {
        var afd: AssetFileDescriptor? = null
        try {
            // 打开指定音乐文件,获取assets目录下指定文件的AssetFileDescriptor对象
            afd = context.getAssets().openFd(fileName!!)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            close(afd!!)
        }
        return afd
    }

    /**
     * 获取所有文件
     *
     * @param path 目录
     * @return
     */
    fun getfiles(
        context: Context,
        path: String?
    ): Array<String?>? {
        val assetManager = context.assets
        var files: Array<String?>? = null
        try {
            files = assetManager.list(path!!)
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } finally {
            assetManager.close()
        }
        return files
    }

    /**
     * 关闭流
     *
     * @param is
     */
    private fun close(vararg `is`: Closeable) {
        for (i in `is`) {
            try {
                i.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}