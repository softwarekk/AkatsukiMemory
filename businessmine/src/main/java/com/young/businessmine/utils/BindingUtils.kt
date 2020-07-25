package com.young.businessmine.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.young.baselib.CustomProjectLiveData
import com.young.baselib.utils.TLog
import com.young.businessmine.ui.widget.StartTextView
import com.young.commomlib.base.BaseBindingAdapter
import com.young.supportlib.floating.FloatingActionButton
import com.young.supportlib.floating.FloatingActionMenu

/*
 * Des app 通用binding 业务 例如图片加载圆角啥的
 * Author Young
 * Date 2020-05-30
 */class BindingUtils {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["visible"], requireAll = false)
        fun visible(view: View, visible: Boolean) {
            TLog.log("BindingAdapter", "visible")
            view.visibility = if (visible) View.VISIBLE else View.GONE
        }

        @JvmStatic
        @BindingAdapter(value = ["floatingclick"], requireAll = false)
        fun clickEvent(view: FloatingActionButton, click: View.OnClickListener) {
            TLog.log("BindingAdapter", "visible")
            view.setOnClickListener(click)
        }
        @JvmStatic
        @BindingAdapter(value = ["floatingShow"], requireAll = false)
        fun floatingShow(view: FloatingActionMenu, isShow:Boolean) {
            TLog.log("BindingAdapter", "visible$isShow")
            if(isShow)view.open(true) else view.close(true)
        }
        @JvmStatic
        @BindingAdapter(value = ["lottieDo"], requireAll = false)
        fun lottieDo(view: LottieAnimationView, isDo:Boolean) {
            TLog.log("lottieDo", "visible$isDo")
                if(isDo)view.playAnimation()
                else{
                    view.cancelAnimation()
                    view.progress=0f
                }
        }
    }
}