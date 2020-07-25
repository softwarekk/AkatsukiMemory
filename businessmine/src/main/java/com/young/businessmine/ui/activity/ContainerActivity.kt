package com.young.businessmine.ui.activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.slidingpanelayout.widget.SlidingPaneLayout.PanelSlideListener
import com.young.baselib.CustomProjectLiveData
import com.young.baselib.utils.TLog
import com.young.businessmine.BR
import com.young.businessmine.R
import com.young.businessmine.base.BusinessMineBaseActivity
import com.young.businessmine.data.AudioItemUIBean
import com.young.supportlib.player.AudioListDataBean
import com.young.businessmine.databinding.ActivityMainBinding
import com.young.businessmine.ui.adapter.AudioItemAdapter
import com.young.businessmine.ui.viewmodel.ContainerUIViewModel
import com.young.businessmine.utils.AssetsUtils
import com.young.businessmine.utils.callback.IBindingItemClick
import com.young.supportlib.player.AppAudioManager
import com.young.supportlib.player.AudioListBean
import com.young.supportlib.player.IAudioObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
* 现在项目中只有businessmine一个mdule 现在这个activity 生命周期几乎相当于app
* 这哥activity ViewModel可以为app 级别的
* fragment的容器 可以充当组件通讯控制器
* 控制下发
* */
class ContainerActivity : BusinessMineBaseActivity<ActivityMainBinding, ContainerUIViewModel>(),NavController.OnDestinationChangedListener,IBindingItemClick<AudioItemUIBean> {

    private var navigationCT:NavController?=null
    private var build:AppAudioManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TLog.log(LOG_TAG,"11111")
        navigationCT= findNavController(R.id.main_fragment_host)
        navigationCT?.addOnDestinationChangedListener(this)
        initObserVer()
        audioItemUI()
        initAudioBean(0)
        getBinding().slidingLayout.setPanelSlideListener(object :PanelSlideListener{
            override fun onPanelSlide(panel: View, slideOffset: Float) {
                TLog.log("slidingLayout_onPanelSlide")
                if(getUIViewModel().isLeftShow.value==false) getUIViewModel().isLeftShow.postValue(true)
            }

            override fun onPanelClosed(panel: View) {
                TLog.log("slidingLayout_onPanelClosed")
                if(getUIViewModel().isLeftShow.value==true) getUIViewModel().isLeftShow.postValue(false)
            }

            override fun onPanelOpened(panel: View) {
                TLog.log("slidingLayout_onPanelOpened")
            }

        })
    }
    /*
    *   先把initAudioBean 初始audio数据转化为AppAudioManager的播放数据
    *   初始化audio播放器
    *   buildCallBack 是播放器给喂的奶
    * */
    private fun  initLeftAudio(){
        GlobalScope.launch {
            withContext(Dispatchers.IO){
                //把业务音频数据转化微AppAudioManager的管理数据
                var audioData:AudioListBean?=null
                var audioListDataBean:AudioListDataBean?=null
                var listDataBean= arrayListOf<AudioListDataBean>()
                var playingIndex=0
                for ((i,e) in mContainerVM.audioLists.value?.withIndex()!!){
                    audioListDataBean=AudioListDataBean(e.audioName,"player_audio/${e.audioName}")
                    listDataBean.add(audioListDataBean)
                    if(e.isPlaying) playingIndex=i
                }
                audioData= AudioListBean(0,listDataBean)
                mContainerVM.audioLists.value
                //初始化音频播放
                if(build==null) {
                    build = AppAudioManager
                        .build(AppAudioManager.AudioMode.MEDIA_PLAYER,null)
                        .playMode(AppAudioManager.PlayMode.RANDOM)
                        .buildCallBack(object : IAudioObserver {
                            override fun onNextPlaying(position: Int) {
                                initAudioBean(position)
                            }
                        }).buildData(audioData)
                        .play()
                }else{
                    build?.buildData(audioData)
                        ?.playWithIndex(playingIndex)
                }
                lifecycle.addObserver(build!!)
            }
        }
    }

    /*
    * 从assets 初始化audio列表数据
    * 初始化给sharedataviewmodel
    * */
    private fun initAudioBean(position:Int) {
        GlobalScope.launch {
            withContext(Dispatchers.IO){
                Thread.sleep(1000)
                val audioNames = AssetsUtils().getfiles(this@ContainerActivity, "player_audio")!!
                var audioBeanList= arrayListOf<AudioItemUIBean>()
                for ((i,e) in audioNames.withIndex()) {
                    var bean= AudioItemUIBean(
                        e.toString(), i==position
                    )
                    audioBeanList.add(bean)
                }
                mContainerVM.audioLists.postValue(audioBeanList)
            }
        }
    }
    /*
    *业务观察
    * 把data数据转化UI数据 -》UIViewModel
    * */
    private fun initObserVer() {
        mContainerVM.startHide.observe(this@ContainerActivity, Observer<Boolean> {
            getUIViewModel().graphChange.postValue(it)
            getUIViewModel().isLeftShow.postValue(true)//在启动页消失显示audio left list
        })
    }
    /*
    *双击推出的逻辑
    * 本activity基本为app生命周期
    *先写这把
    * */
    var clickTime:Long?=null
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        TLog.log(LOG_TAG,event?.action.toString())
        when(event?.action){
            KeyEvent.KEYCODE_UNKNOWN->{
                TLog.log(LOG_TAG, "111$event"+navigationCT?.currentDestination?.label?.equals("FirstShowFragment"))
                if(navigationCT?.currentDestination?.label?.equals("FirstShowFragment")!!) {
                    if(clickTime!=null) {
                        var curretnTime = System.currentTimeMillis()
                        var intervalTime = curretnTime.minus(clickTime!!)
                        TLog.log(LOG_TAG, intervalTime.toString())
                        if (intervalTime <= 1500) {
                            finish()
                            return false
                        }else{
                            clickTime=System.currentTimeMillis()
                            showToast("再次点击退出")
                            return false
                        }
                    }else{
                        clickTime=System.currentTimeMillis()
                        showToast("再次点击退出")
                        return false
                    }
                }else{
                    TLog.log(LOG_TAG, "111$event")
                    navigationCT?.navigateUp()
                    return false
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }
    /*
    * 左侧audio player 初始化UI 和数据处理
    * */
    fun audioItemUI(){
        val audioAdapter=AudioItemAdapter(this,this)
        getUIViewModel()?.run {
            getBinding()?.run {
                leftRecycleview.adapter=audioAdapter
            }
            //每次歌曲播放的数据源变换变量更新adapter 通知Appaudiomanager
            mContainerVM?.audioLists.observe(this@ContainerActivity, Observer<List<AudioItemUIBean>> {
                TLog.log("audio_data_change","123${it.size}+${it.get(0).audioName}")
                audioAdapter.dataList=it //adapter 会diff做增量刷新
                initLeftAudio()
            })
        }
    }
    /*
    * 左侧audio列表点击事件
    * */
    override fun onClick(postion: Int, dataBean: AudioItemUIBean) {
        TLog.log("audio_click","$postion"+dataBean.audioName)
        initAudioBean(postion)
    }

    override fun getUIViewModel(): ContainerUIViewModel {
        return getActivityViewModelProvider(this)!!.get(ContainerUIViewModel::class.java) //To change body of created functions use File | Settings | File Templates.
    }
    override val bindingVariable: Int = BR.containervm //To change initializer of created properties use File | Settings | File Templates.
    override val layoutId: Int = R.layout.activity_main //To change initializer of created properties use File | Settings | File Templates.
    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    override fun onResume() {
        super.onResume()
    }
}
