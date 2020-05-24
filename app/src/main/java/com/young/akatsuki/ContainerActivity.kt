package com.young.akatsuki

import android.os.Bundle
import com.young.akatsuki.databinding.ActivityMainBinding
import com.young.akatsuki.viewmodel.ContainerVM
import com.young.commomlib.base.CommonBaseActivity

class ContainerActivity : CommonBaseActivity<ActivityMainBinding, ContainerVM>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun getViewModel(): ContainerVM {
        return getActivityViewModelProvider(this)!!.get(ContainerVM::class.java) //To change body of created functions use File | Settings | File Templates.
    }
    override val bindingVariable: Int
        get() = BR.containervm //To change initializer of created properties use File | Settings | File Templates.
    override val layoutId: Int
        get() =R.layout.activity_main//To change initializer of created properties use File | Settings | File Templates.
}
