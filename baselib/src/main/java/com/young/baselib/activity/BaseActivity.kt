package com.young.baselib.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.young.baselib.viewmodel.BaseViewModel

/**
 * BaseActivity 最基件
 */
abstract class BaseActivity<V : ViewDataBinding?, VM : BaseViewModel?> :
    AppCompatActivity() {
    private var viewModel: VM? = null
    private var viewDataBinding: V? = null
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
}