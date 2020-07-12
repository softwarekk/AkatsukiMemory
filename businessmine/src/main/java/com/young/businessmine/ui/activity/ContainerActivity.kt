package com.young.businessmine.ui.activity

import android.os.Bundle
import android.view.KeyEvent
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.young.baselib.utils.TLog
import com.young.businessmine.BR
import com.young.businessmine.R
import com.young.businessmine.base.BusinessMineBaseActivity
import com.young.businessmine.databinding.ActivityMainBinding
import com.young.businessmine.ui.viewmodel.ContainerShareVM
import com.young.businessmine.ui.viewmodel.ContainerUIViewModel
import com.young.businessmine.utils.AssetsUtils
import com.young.commomlib.base.CommonBaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

/*
* fragment的容器 可以充当组件通讯控制器
* 控制下发
* */
class ContainerActivity : BusinessMineBaseActivity<ActivityMainBinding, ContainerUIViewModel>(),NavController.OnDestinationChangedListener {
    var navigationCT:NavController?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TLog.log(LOG_TAG,"11111")
        navigationCT= findNavController(R.id.main_fragment_host)
        navigationCT?.addOnDestinationChangedListener(this)
        initObserVer()
        GlobalScope.launch {
            withContext(Dispatchers.IO){
            }
        }
    }
    private fun initAudioList() {//初始化本地压缩的音频数据
        var audioNames = AssetsUtils().getfiles(this, "")
        TLog.log("audio_data",audioNames?.size.toString())
//        mContainerVM.audioLists.value=audioNames
    }

    private fun initObserVer() {
        mContainerVM.startHide.observe(this, Observer<Boolean> {
            getViewModel().graphChange.value=it
        })
        mContainerVM.audioLists.observe(this, Observer<Array<String?>> {
            getViewModel().audioLists.value=it
        })
    }

    override fun getViewModel(): ContainerUIViewModel {
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
}
