package com.young.commomlib.base

import android.graphics.Color
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.young.baselib.activity.BaseActivity
import com.young.baselib.viewmodel.BaseUIViewMode
import com.young.commomlib.utils.BarUtils
import com.young.commomlib.viewmodel.CommonShareVM

/*
 * Des 业务级别
 * Author Young
 * Date 2020-05-18
 */
abstract class CommonBaseActivity<V : ViewDataBinding, VM : BaseUIViewMode> : BaseActivity<V,VM> (){
    protected var mCommonModel: CommonShareVM? = null //app 分享数据模块

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 给工具类初始化
        BarUtils.setStatusBarVisibility(this,false)
        mCommonModel= getAppViewModelProvider()!!.get(CommonShareVM::class.java)
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}