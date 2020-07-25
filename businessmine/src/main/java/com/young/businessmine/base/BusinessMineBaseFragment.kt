package com.young.businessmine.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.young.baselib.viewmodel.BaseUIViewMode
import com.young.businessmine.ui.viewmodel.ContainerShareVM
import com.young.commomlib.base.CommonFragment

/*
 * Des
 * Author YOung
 * Date 2020-06-21
 */abstract class BusinessMineBaseFragment <V : ViewDataBinding, VM : BaseUIViewMode> : CommonFragment<V,VM> {
    constructor() : super()

    lateinit  var mContainerVM: ContainerShareVM//app 分享数据模块
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContainerVM= getAppViewModelProvider()?.get(ContainerShareVM::class.java)!!
    }
}