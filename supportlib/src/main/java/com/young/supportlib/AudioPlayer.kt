package com.young.supportlib

import android.content.Context
import android.media.AudioManager
import android.os.PowerManager
import com.pili.pldroid.player.*
import com.young.baselib.utils.TLog
import java.io.IOException

object AudioPlayer {


    private lateinit var mMediaPlayer:PLMediaPlayer
    private lateinit var mAVOptions:AVOptions

    fun  initAudio(context: Context){
        mAVOptions=AVOptions()
        // the unit of timeout is ms
        mAVOptions.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000)
        // 1 -> hw codec enable, 0 -> disable [recommended]
        val audioManager =
            context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.requestAudioFocus(
            null,
            AudioManager.STREAM_MUSIC,
            AudioManager.AUDIOFOCUS_GAIN
        )
        // codec＝AVOptions.MEDIA_CODEC_HW_DECODE，硬解
        // codec=AVOptions.MEDIA_CODEC_SW_DECODE, 软解
        // codec=AVOptions.MEDIA_CODEC_AUTO, 硬解优先，失败后自动切换到软解
        // 默认值是：MEDIA_CODEC_SW_DECODE
        val codec: Int = AVOptions.MEDIA_CODEC_SW_DECODE
        mAVOptions.setInteger(AVOptions.KEY_MEDIACODEC, codec)
        val startPos: Int = 0
        mAVOptions.setInteger(AVOptions.KEY_START_POSITION, startPos * 1000)
        prepare(context)
    }
    private fun prepare(context: Context){
        mMediaPlayer = PLMediaPlayer(context, mAVOptions)
        mMediaPlayer.setLooping(false)
        mMediaPlayer.setOnPreparedListener(mOnPreparedListener)
        mMediaPlayer.setOnCompletionListener(mOnCompletionListener)
        mMediaPlayer.setOnErrorListener(mOnErrorListener)
        mMediaPlayer.setOnInfoListener(mOnInfoListener)
        mMediaPlayer.setOnBufferingUpdateListener(mOnBufferingUpdateListener)
        mMediaPlayer.setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK)
    }
    fun setSourceStart(mAudioPath:String){
        try {
            mMediaPlayer.setDataSource(mAudioPath)
            mMediaPlayer.prepareAsync()
        } catch (e: IOException) {
            e.printStackTrace()
            TLog.log("prepare error")
        }
    }
    fun release(){
        if (mMediaPlayer != null) {
            mMediaPlayer.stop()
            mMediaPlayer.release()
        }
    }
    //暂停
    fun pause(){
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
    }
    //继续播放
    fun resume(){
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
        }
    }
    //结束
    fun stop(){
        if (mMediaPlayer != null) {
            mMediaPlayer.stop()
            mMediaPlayer.release()
        }
    }
    private val mOnPreparedListener = PLOnPreparedListener {
        mMediaPlayer.start()
    }

    private val mOnInfoListener =
        PLOnInfoListener { what, extra ->
            when (what) {
                PLOnInfoListener.MEDIA_INFO_BUFFERING_START ->{
                    TLog.log("this should loading")
                }
                PLOnInfoListener.MEDIA_INFO_BUFFERING_END, PLOnInfoListener.MEDIA_INFO_AUDIO_RENDERING_START -> {
                    TLog.log("this loading  end")
                }
                PLOnInfoListener.MEDIA_INFO_AUDIO_FRAME_RENDERING -> {
                    TLog.log("this progress  update")
                }
                else -> {
                }
            }
        }

    private val mOnBufferingUpdateListener =
        PLOnBufferingUpdateListener { percent ->

        }

    /**
     * Listen the event of playing complete
     * For playing local file, it's called when reading the file EOF
     * For playing network stream, it's called when the buffered bytes played over
     *
     *
     * If setLooping(true) is called, the player will restart automatically
     * And ｀onCompletion｀ will not be called
     */
    private val mOnCompletionListener = PLOnCompletionListener {

    }

    private val mOnErrorListener =
        PLOnErrorListener { errorCode ->

            when (errorCode) {
                PLOnErrorListener.ERROR_CODE_IO_ERROR -> {
                    /**
                     * SDK will do reconnecting automatically
                     */
                    return@PLOnErrorListener false
                }
                PLOnErrorListener.ERROR_CODE_OPEN_FAILED ->{
                    TLog.log("open failed")
                }
                PLOnErrorListener.ERROR_CODE_SEEK_FAILED -> {
                    TLog.log("fail to seek")
                }
                else -> {
                    TLog.log("no matched")
                }
            }
            true
        }
}