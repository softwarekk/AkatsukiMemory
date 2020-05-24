package com.young.businessmine.ui.fragment

import com.young.businessmine.BR
import com.young.businessmine.R
import com.young.businessmine.databinding.FragmentFirstShowLayoutBinding
import com.young.businessmine.ui.viewmodel.FirstShowVM
import com.young.commomlib.base.CommonFragment

class FirstShowFragment : CommonFragment<FragmentFirstShowLayoutBinding,FirstShowVM>() {
    override val bindingVariable: Int
        get() =BR.lanucher_vm
    override val layoutId: Int
        get() = R.layout.fragment_first_show_layout

    override fun getViewModel(): FirstShowVM {
        return getFragmentViewModelProvider(this)?.get(FirstShowVM::class.java)!!
    }
}