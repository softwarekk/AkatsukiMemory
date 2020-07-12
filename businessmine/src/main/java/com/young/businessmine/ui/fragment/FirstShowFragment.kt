package com.young.businessmine.ui.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
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
        getBinding().run {
            GlobalScope.launch {
                withContext(Dispatchers.Main){
                    TLog.log("ani_test","1111")
                    textAniLogic(textLineOne)
                }
                withContext(Dispatchers.IO){
                    TLog.log("ani_test","2222")
                    Thread.sleep(500)
                    withContext(Dispatchers.Main){
                        textAniLogic(textLineTwo)
                    }
                }
                withContext(Dispatchers.IO){
                    TLog.log("ani_test","3333")
                    Thread.sleep(1000)
                    withContext(Dispatchers.Main){
                        textAniLogic(textLineThree)
                    }
                    Thread.sleep(3500)
                    withContext(Dispatchers.Main){
                        textLineFour.visibility=View.VISIBLE
                        textLineFour.animate()
                            .scaleX(1.1f)
                            .alpha(0.5f)
                            .setInterpolator(AccelerateDecelerateInterpolator())
                            .setDuration(1000)
                            .setStartDelay(0)
                            .setListener(object :
                                Animator.AnimatorListener {
                                override fun onAnimationRepeat(animation: Animator?) {
                                }

                                override fun onAnimationEnd(animation: Animator?) {
                                }

                                override fun onAnimationCancel(animation: Animator?) {
                                }

                                override fun onAnimationStart(animation: Animator?) {
                                }
                            })


                    }
                }
            }
        }

    }
    fun textAniLogic(view:View){
        view.animate()
            .rotationX(360f)
            .alpha(0.8f)
            .translationX(ScreenUtils.getScreenWidth()*1f)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(3500)
            .setStartDelay(0)
//        var objectAnimator: ObjectAnimator =
//            ObjectAnimator.ofFloat(view, "translationX", 0f, ScreenUtils.getScreenWidth()*1f)
//        var animatorSet =  AnimatorSet()
//        animatorSet.play(objectAnimator)
//        animatorSet.interpolator= AccelerateDecelerateInterpolator()
//        animatorSet.duration = 4000
//        animatorSet.start()
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
}