package com.young.businessmine.ui.activity

import android.os.Bundle
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
import com.young.commomlib.base.CommonBaseActivity
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
    }

    private fun initObserVer() {
       mContainerVM.startHide.observe(this, Observer<Boolean> {
            getViewModel().graphChange.value=it
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
        if(destination.label?.equals("FirstShowFragment")!!){
            navigationCT?.popBackStack(destination.id,false)
        }
    }
}
