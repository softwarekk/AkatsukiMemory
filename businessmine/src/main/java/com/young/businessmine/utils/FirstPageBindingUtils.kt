package com.young.businessmine.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.young.baselib.utils.TLog
import com.young.businessmine.ui.widget.StartTextView
import com.young.commomlib.utils.DisplayUtils
import com.young.commomlib.utils.ScreenUtils
import org.w3c.dom.Text

/*
* 首页展示抽出UI逻辑
*
* */
class FirstPageBindingUtils {

    companion object {
        //三行动效 不用回调 控制层用时间控制
        @JvmStatic
        @BindingAdapter(value = ["lineAni"], requireAll = false)
        fun lineAni(view: View, milSec:Int) {
            TLog.log("lineAni", "milSec${milSec}")
            if(milSec==0){
                return
            }
            view.animate()
                .rotationX(360f)
                .alpha(0.8f)
                .translationX(ScreenUtils.getScreenWidth()*1f)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .setDuration(milSec.toLong())
                .setStartDelay(0)
        }
        @JvmStatic
        @BindingAdapter(value = ["startTextView"], requireAll = false)
        fun startTextView(view: StartTextView, content:String) {
            TLog.log("startTextView", "content${content}")
            view.setTextContent(content)
        }

        @JvmStatic
        @BindingAdapter(value = ["startSubAni"], requireAll = false)
        fun subAni(view: StartTextView, milSec:Int) {
            if(milSec==0){
                return
            }
            var objectAnimator: ObjectAnimator =
                ObjectAnimator.ofFloat(view, "alpha", 0f,1f)
            var animatorSet =  AnimatorSet()
            animatorSet.play(objectAnimator)
            animatorSet.interpolator= AccelerateDecelerateInterpolator()
            animatorSet.duration = 3000
            animatorSet.start()
        }
        @JvmStatic
        @BindingAdapter(value = ["audioItemSize"], requireAll = false)
        fun audioItemSize(view: TextView, isPlaying:Boolean) {
            if(isPlaying){
                view.setPadding(DisplayUtils.dp2px(10f),0,DisplayUtils.dp2px(35f),0)
            }else{
                view.setPadding(DisplayUtils.dp2px(10f),0,DisplayUtils.dp2px(10f),0)
            }
        }
    }
}