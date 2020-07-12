package com.young.businessmine.ui.fragment

import android.os.Bundle
import com.young.businessmine.R
import com.young.businessmine.databinding.ListShowLayoutBinding
import com.young.businessmine.ui.viewmodel.LanucherVM
import com.young.commomlib.base.CommonFragment

class ListArticleFragment : CommonFragment<ListShowLayoutBinding,LanucherVM>() {

    companion object {
        fun newInstance() = ListArticleFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override val bindingVariable: Int
        get() = 0
    override val layoutId: Int
        get() =  R.layout.list_show_layout

    override fun getUIViewModel(): LanucherVM {
        return getFragmentViewModelProvider(this)?.get(LanucherVM::class.java)!!
    }

}