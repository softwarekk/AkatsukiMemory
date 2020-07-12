package com.young.businessmine.ui.fragment

import com.young.businessmine.BR
import com.young.businessmine.R
import com.young.businessmine.base.BusinessMineBaseFragment
import com.young.businessmine.databinding.FragmentMemberCardBinding
import com.young.businessmine.ui.viewmodel.MemberCardUIVM

/**
 * A fragment representing a list of Items.
 */
class MemberCardFragment : BusinessMineBaseFragment<FragmentMemberCardBinding,MemberCardUIVM> {
    constructor() : super()

    override val bindingVariable: Int=BR.vm
    override val layoutId: Int=R.layout.fragment_member_card

    override fun getUIViewModel(): MemberCardUIVM = getFragmentViewModelProvider(this)?.get(
        MemberCardUIVM::class.java)!!
}