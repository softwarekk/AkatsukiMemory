package com.young.commomlib.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.young.baselib.activity.BaseActivity
import com.young.baselib.viewmodel.BaseViewModel
import com.young.commomlib.viewmodel.CommonShareVM

/*
 * Des
 * Author Young
 * Date 2020-05-18
 */
abstract class CommonBaseActivity<V : ViewDataBinding, VM : BaseViewModel> : BaseActivity<V,VM> (){
    protected var mCommonModel: CommonShareVM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCommonModel= getAppViewModelProvider()!!.get(CommonShareVM::class.java)
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}