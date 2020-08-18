package com.young.supportlib.player

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.young.supportlib.player.audio.IAudioMap
import com.young.supportlib.player.audio.IAudioStatus
import com.zs.base_library.play.MediaPlayerHelper
import kotlin.random.Random

/*
*
* 播放器同意管理实例
* 播放数据
* 播放模式
* 播放方式
* 这是个单例 构建完 就和调用者没有关系 调用者吃奶就行
*
* */
object AppAudioManager :IAudioStatus,LifecycleObserver {



    private lateinit var audioPlayer: IAudioMap//播放器映射实例
    private lateinit var currentPlayMode: PlayMode//播放模式
    private lateinit var managerStatus: IAudioObserver//播放回调给业务
    private lateinit var audioData: AudioListBean//播放数据
    private lateinit var mContext:Context

    /*
    * 这个对象里是单例 单独维护每次第一次初始化的功能具体实现的类 例如MediaPlayerHelper
    * 用多个功能实现实例 切换不同音频 不需要保存数据 只需控制pause 操作 或start
    * 现在是在map 用功能类型key value MediaPlayerHelper实例
    * 同意业务类型维护同一实例
    * */
    private var mediaInstanceList:HashMap<BusinessMode,MediaPlayerHelper> ?=null

    private var isPause:Boolean=false//控制一下pause resume 状态 

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun  onPause(){
        audioPlayer.pause()
        isPause=true
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        if(isPause) audioPlayer.start()
        isPause=false
    }
    fun init(context:Context){
        mContext=context
    }
    //第一步 build播放实例类型
    fun build(audio: AudioMode,businessMode: BusinessMode?): AppAudioManager {
        when(audio){
            AudioMode.MEDIA_PLAYER ->{
                var mode=businessMode
                var mediaInstance:MediaPlayerHelper?=null
                if(businessMode== null) mode=BusinessMode.APP_AUDIO
                if(mediaInstanceList==null) {
                    val mediaInstance=MediaPlayerHelper(this,mContext)
                    mediaInstanceList?.put(mode!!,mediaInstance)
                    audioPlayer=mediaInstance
                }else{
                    mediaInstanceList?.forEach {
                        if(it.key==mode){
                            mediaInstance=it.value
                            audioPlayer= mediaInstance!!
                            return@forEach
                        }
                    }
                }
            }
            AudioMode.PLD_PLAYER->{

            }
        }
        return this
    }
    //第二步 build播放模式
    fun playMode(playMode:PlayMode):AppAudioManager{
        currentPlayMode=playMode
        return this
    }

    //第三步build回调
    fun buildCallBack(observer: IAudioObserver):AppAudioManager{
        managerStatus=observer
        return this
    }
    //第四步build数据源
    fun buildData(audios:AudioListBean):AppAudioManager{
        audioData=audios
        return this
    }
    fun play():AppAudioManager{
        audioPlayer.stop()
        when(currentPlayMode){
            PlayMode.SINGLE->{
                audioPlayer.play(audioData.playingBean().url)
            }
            PlayMode.RANDOM->{
                audioPlayer.play(audioData.playingBean().url)
            }
            PlayMode.ORDER->{
                audioPlayer.play(audioData.playingBean().url)
            }
        }
        return this
    }
    fun playWithIndex(index:Int){
        audioData.position= index
        audioPlayer.play(audioData.playingBean().url)
    }
    fun destory(){
        audioPlayer.stop()
    }
    override fun onComplete() {
        when(currentPlayMode){
            PlayMode.SINGLE->{//播放位置不动
            }
            PlayMode.RANDOM->{//position播放前设置
                audioData.position= makeRandom(audioData.audioDatas.size)
            }
            PlayMode.ORDER->{//顺序循环
                audioData.position= if(audioData.isLast()) 0 else audioData.position+1
            }
        }
        managerStatus.onNextPlaying(audioData.position)
        play()
    }

    override fun seekPosition(position: Int) {

    }

    override fun buffingPosition(position: Int) {
    }

    private fun makeRandom(max:Int):Int{
        val r = (0 until max).random()
        return r
    }


    /*
    * 维护功能实例的类型 对应assets audio资源类型
    * */
    enum class BusinessMode{
        APP_AUDIO,
        PLAYER_AUDIO
    }
    /*
    * 实现功能的实例类型 对应音频播放不同框架
    * */
    enum class AudioMode{
         MEDIA_PLAYER,
         PLD_PLAYER
    }
    /*
    * 控制播放模式
    * */
    enum class PlayMode{
        SINGLE,
        RANDOM,
        ORDER
    }

}