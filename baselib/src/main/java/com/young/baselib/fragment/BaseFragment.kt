package com.young.baselib.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.young.baselib.BaseApplication
import com.young.baselib.viewmodel.BaseUIViewMode

/**
 * fragment 最基件
 */
abstract class BaseFragment<V : ViewDataBinding, VM : BaseUIViewMode?> :
    Fragment() {
    protected var mActivity: AppCompatActivity? = null
    private var viewModel: VM? = null
    private var viewDataBinding: V? = null
    private var mFragmentTag = ""



    abstract val bindingVariable: Int
    @get:LayoutRes
    abstract val layoutId: Int
    abstract fun getUIViewModel(): VM


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
        viewModel = getUIViewModel()
        if (bindingVariable > 0 && viewModel != null) {
            viewDataBinding?.setVariable(bindingVariable, viewModel)
        } else {
        }
        viewDataBinding?.lifecycleOwner = this
    }

    protected open fun getAppViewModelProvider(): ViewModelProvider? {
        return (mActivity?.getApplicationContext() as BaseApplication).getAppViewModelProvider(mActivity!!)
    }

    // 给所有的fragment提供的函数，可以顺利的拿到 ViewModel
    protected open fun getFragmentViewModelProvider(fragment: Fragment): ViewModelProvider? {
        return ViewModelProvider(fragment, fragment.defaultViewModelProviderFactory)
    }

    // 给所有的fragment提供的函数，可以顺利的拿到 ViewModel
    protected open fun getActivityViewModelProvider(activity: AppCompatActivity): ViewModelProvider? {
        return ViewModelProvider(activity, activity.defaultViewModelProviderFactory)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }
    fun getBinding() : V{
        return viewDataBinding!!
    }
    fun showToast(message:String){
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}