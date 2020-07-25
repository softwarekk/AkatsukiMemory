package com.zs.base_library.play

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.*
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.young.baselib.CustomProjectLiveData
import com.young.baselib.utils.TLog
import com.young.supportlib.player.audio.IAudioMap
import com.young.supportlib.player.audio.IAudioStatus
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

/**
 * des 基于MediaPlayer实现的音频播放
 */
class MediaPlayerHelper(statusCallBack : IAudioStatus,context:Context) : IAudioMap,
    OnCompletionListener,
    OnBufferingUpdateListener,
    OnErrorListener,
    OnPreparedListener{

    private val mContext:Context = context
    private val mediaPlayer by lazy { MediaPlayer() }
    private val status:IAudioStatus=statusCallBack
    private var disposable: Disposable? = null//进度主动通知的rx管道

    init {
        //播放完成监听
        mediaPlayer.setOnCompletionListener(this)
        //缓冲更新监听
        mediaPlayer.setOnBufferingUpdateListener(this)
        //错误监听
        mediaPlayer.setOnErrorListener(this)
        //播放器准备完成监听
        mediaPlayer.setOnPreparedListener(this)
        //进度回调机制
        initSeekObs()
    }
    private fun initSeekObs(){
        disposable=Observable.interval(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                //仅在播放状态下通知观察者
                if (mediaPlayer.isPlaying) {
                    status.seekPosition(mediaPlayer.currentPosition)
                }
        }
    }
    override fun play(path: String) {
        mediaPlayer.reset()
        //可能会抛FileNotFound异常
        kotlin.runCatching {
            if(path.startsWith("/")) {
                mediaPlayer.setDataSource(path)
            }else if(path.startsWith("http:")){
                var uri = Uri.parse(path)
                mediaPlayer.setDataSource(mContext,uri)
            }else{
                val fileDescriptor= mContext.assets.openFd(path)
                mediaPlayer.setDataSource(fileDescriptor.fileDescriptor,
                    fileDescriptor.startOffset,
                    fileDescriptor.length)
            }
        }.onSuccess {
            mediaPlayer.prepare()
        }.onFailure {
            TLog.log("audio_failure",it.message)
        }
    }


    override fun pause() {
        mediaPlayer.pause()
    }

    override fun stop() {
        mediaPlayer.stop()
    }

    override fun seekTo(duration: Int) {
        mediaPlayer.seekTo(duration)
    }

    override fun start() {
        mediaPlayer.start()
    }

    /**
     * 播放完成
     */
    override fun onCompletion(mp: MediaPlayer?) {
        status.onComplete()
    }

    /**
     * 缓冲更新
     */
    override fun onBufferingUpdate(mp: MediaPlayer?, percent: Int) {
        status?.buffingPosition(percent)
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        TLog.log("audio_onError","$extra---$what")
        return true
    }

    /**
     * mediaPlayer准备完毕直接播放
     */
    override fun onPrepared(mp: MediaPlayer?) {
        mediaPlayer.start()
    }

}