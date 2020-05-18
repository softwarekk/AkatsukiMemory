package com.young.baselib.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.young.baselib.BaseApplication
import com.young.baselib.viewmodel.BaseViewModel

/**

- BaseActivity 最基件
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> :
    AppCompatActivity {


    private var viewModel: VM? = null
    private var viewDataBinding: V? = null

    constructor() : super() {
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        byDataBindingAttach()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    protected abstract fun getViewModel(): VM
    abstract val bindingVariable: Int
    @get:LayoutRes
    abstract val layoutId: Int
    private fun byDataBindingAttach() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = if (viewModel == null) getViewModel() else viewModel
        if (bindingVariable > 0 && viewModel != null) {
            viewDataBinding?.setVariable(bindingVariable, viewModel)
        } else {
        }
        viewDataBinding?.setLifecycleOwner(this)
    }
    // 2020 用法 ViewModelProvider
    protected  fun getAppViewModelProvider(): ViewModelProvider? {
        return (applicationContext as BaseApplication).getAppViewModelProvider(this)
    }

    protected  fun getActivityViewModelProvider(activity: AppCompatActivity): ViewModelProvider? {
        return ViewModelProvider(activity, activity.defaultViewModelProviderFactory)
    }
}