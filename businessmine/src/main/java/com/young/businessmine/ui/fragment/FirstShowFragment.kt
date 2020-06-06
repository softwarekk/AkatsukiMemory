package com.young.businessmine.ui.fragment

import android.os.Bundle
import android.view.View
import com.young.businessmine.BR
import com.young.businessmine.R
import com.young.businessmine.databinding.FragmentFirstShowLayoutBinding
import com.young.businessmine.ui.viewmodel.FirstShowVM
import com.young.commomlib.Constants
import com.young.commomlib.base.CommonFragment

class FirstShowFragment : CommonFragment<FragmentFirstShowLayoutBinding,FirstShowVM>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        getBinding()?.loadingView?.setPageStatus(Constants.PAGE_LOADING)
    }
    override val bindingVariable: Int
        get() =BR.lanucher_vm
    override val layoutId: Int
        get() = R.layout.fragment_first_show_layout

    override fun getUIViewModel(): FirstShowVM {
        return getFragmentViewModelProvider(this)?.get(FirstShowVM::class.java)!!
    }
}