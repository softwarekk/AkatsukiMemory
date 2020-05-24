package com.young.commomlib.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.young.baselib.fragment.BaseFragment
import com.young.baselib.viewmodel.BaseUIViewMode
import com.young.commomlib.viewmodel.CommonShareVM

/*
 * Des
 * Author Young
 * Date 2020-05-24
 */abstract class CommonFragment<V : ViewDataBinding, VM : BaseUIViewMode>  : BaseFragment<V,VM>()
{
    protected var mCommonModel: CommonShareVM? = null //app 分享数据模块
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCommonModel = getAppViewModelProvider()?.get(CommonShareVM::class.java)
    }

    /**
     * 为了给所有的fragment，导航跳转fragment的
     * @return
     */
    protected open fun nav(): NavController? {
        return NavHostFragment.findNavController(this)
    }
    /**
     * 对外暴露 SharedViewModel，所有的fragment都可以使用 共享的SharedViewModel
     * @return
     */
    open fun getCommonVM(): CommonShareVM? {
        return mCommonModel
    }
}