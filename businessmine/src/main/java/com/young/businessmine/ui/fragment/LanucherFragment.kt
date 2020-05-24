package com.young.businessmine.ui.fragment

import android.os.Bundle
import com.young.baselib.fragment.BaseFragment
import com.young.businessmine.BR
import com.young.businessmine.R
import com.young.businessmine.databinding.FragmentLancherLayoutBinding
import com.young.businessmine.ui.viewmodel.LanucherVM
import com.young.commomlib.base.CommonFragment

/*
 * Des
 * Author Young
 * Date 2020-05-24
 */
class LanucherFragment : CommonFragment<FragmentLancherLayoutBinding, LanucherVM>(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nav()?.navigate(R.id.action_lanucherFragment_to_firstshowfragment)

    }

    override val bindingVariable: Int
        get() =BR.lanucher_vm
    override val layoutId: Int
        get() = R.layout.fragment_lancher_layout

    override fun getViewModel(): LanucherVM {
        return getFragmentViewModelProvider(this)?.get(LanucherVM::class.java)!!
    }

}