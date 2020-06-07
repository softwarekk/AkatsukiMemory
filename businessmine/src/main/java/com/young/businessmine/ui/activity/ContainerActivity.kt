package com.young.businessmine.ui.activity

import android.os.Bundle
import com.young.businessmine.BR
import com.young.businessmine.R
import com.young.businessmine.databinding.ActivityMainBinding
import com.young.businessmine.ui.viewmodel.ContainerVM
import com.young.commomlib.base.CommonBaseActivity
/*
* fragment的容器 可以充当组件通讯控制器
* 控制下发
* */
class ContainerActivity : CommonBaseActivity<ActivityMainBinding, ContainerVM>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun getViewModel(): ContainerVM {
        return getActivityViewModelProvider(this)!!.get(ContainerVM::class.java) //To change body of created functions use File | Settings | File Templates.
    }
    override val bindingVariable: Int
        get() = BR.containervm//To change initializer of created properties use File | Settings | File Templates.
    override val layoutId: Int
        get() = R.layout.activity_main//To change initializer of created properties use File | Settings | File Templates.
}
