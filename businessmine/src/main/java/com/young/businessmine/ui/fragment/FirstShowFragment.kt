package com.young.businessmine.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.PagerSnapHelper
import com.young.baselib.utils.TLog
import com.young.businessmine.BR
import com.young.businessmine.R
import com.young.businessmine.base.BusinessMineBaseFragment
import com.young.businessmine.databinding.FragmentFirstShowLayoutBinding
import com.young.businessmine.ui.viewmodel.FirstShowVM
import com.young.supportlib.SyaringanDrawable

class FirstShowFragment : BusinessMineBaseFragment<FragmentFirstShowLayoutBinding,FirstShowVM>(),View.OnClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        getBinding()?.loadingView?.setPageStatus(Constants.PAGE_LOADING)
//        nav()?.navigate(R.id.action_firstshowfragment_to_listFragment)
        TLog.log(LOG_TAG,"2222")
        getBinding().floatingclick=this
        getBinding().testBtn.setOnClickListener(View.OnClickListener {
            nav()?.navigate(R.id.action_firstshowfragment_to_listFragment)
        })
        getBinding().testDrawable.setImageDrawable(SyaringanDrawable())
    }

    override val bindingVariable: Int
        get() =BR.first_vm
    override val layoutId: Int
        get() = R.layout.fragment_first_show_layout

    override fun getUIViewModel(): FirstShowVM {
        return getFragmentViewModelProvider(this)?.get(FirstShowVM::class.java)!!
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ling_book_bt->{
                showToast("临之书")
            }
            R.id.bing_book_bt->{
                showToast("兵之书")
            }
            R.id.dou_book_bt->{
                showToast("斗之书")
            }
            R.id.zhe_book_bt->{
                showToast("者之书")
            }
        }
        getUIViewModel().isMenuOpen.value=false
        TLog.log(LOG_TAG,"33333")
    }

    fun initSnap(){
        var snapHelper: PagerSnapHelper = PagerSnapHelper()
//        snapHelper.attachToRecyclerView(databinding?.chivoxContentRc)
//        addOnScrollListener(RecyclerViewPageChangeListenerHelper(snapHelper, onPageChangeListener))
    }
}