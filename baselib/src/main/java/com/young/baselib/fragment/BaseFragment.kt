package com.young.baselib.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.young.baselib.viewmodel.BaseViewModel

/**
 * fragment 最基件
 */
abstract class BaseFragment<V : ViewDataBinding?, VM : BaseViewModel?> :
    Fragment() {
    private var viewModel: VM? = null
    private var viewDataBinding: V? = null
    private var mFragmentTag = ""
    abstract val bindingVariable: Int

    @get:LayoutRes
    abstract val layoutId: Int
    abstract fun getViewModel(): VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding?.getRoot()
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel()
        if (bindingVariable > 0 && viewModel != null) {
            viewDataBinding?.setVariable(bindingVariable, viewModel)
        } else {
        }
        viewDataBinding?.lifecycleOwner = this
    }
}