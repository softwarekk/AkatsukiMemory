package com.young.businessmine.ui.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.recyclerview.widget.PagerSnapHelper
import com.young.baselib.utils.TLog
import com.young.businessmine.BR
import com.young.businessmine.R
import com.young.businessmine.base.BusinessMineBaseFragment
import com.young.businessmine.databinding.FragmentFirstShowLayoutBinding
import com.young.businessmine.ui.viewmodel.FirstShowVM
import com.young.commomlib.utils.ScreenUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
* 逻辑 ： 所有的UI 逻辑都由 UIVIewModel 控制
*  FirstShowVM 控制xml UI 变动通知vm就可以
* 动画执行流程 由协程控制时间进行控制
* 单向UI触发UI逻辑由bindingUtils 处理 （动效执行、复杂显示隐藏）
* 数据控制 由dataViewModel 通知UI viewModel 触发
* 比如根据是否第一次安装进入 显示
* 现在只做控制管理 data 和 UI 的转换
* */
class FirstShowFragment : BusinessMineBaseFragment<FragmentFirstShowLayoutBinding,FirstShowVM>(),View.OnClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBinding().floatingclick=this
        getBinding().testBtn.setOnClickListener(View.OnClickListener {
            nav()?.navigate(R.id.action_firstshowfragment_to_listFragment)
        })
        beginAniLogic()
    }
    //UI卷首总控 用时间控制动效 不使用回调
    fun  beginAniLogic(){
        getUIViewModel()?.run{
            getBinding()?.run {
                GlobalScope.launch {
                    withContext(Dispatchers.Main){
                        isShowSub.value=false
                        lineOneAni.value=3500
                    }
                    withContext(Dispatchers.IO){
                        Thread.sleep(500)
                        withContext(Dispatchers.Main){
                            lineTwoAni.value=3500 //卷首行2
                        }
                    }
                    withContext(Dispatchers.IO){
                        Thread.sleep(1000)
                        withContext(Dispatchers.Main){
                            lineThreeAni.value=3500//卷首行13
                        }
                        Thread.sleep(3500)
                        withContext(Dispatchers.Main){
                            isShowSub.value=true
                            subAniTime.value=3000//卷首subtitle
                        }
                        Thread.sleep(4500)
                        withContext(Dispatchers.Main){
                            isShowBegin.value=false
                        }
                    }
                }
            }
        }
    }
    fun pageLogic(){

    }




    override fun onResume() {
        super.onResume()
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