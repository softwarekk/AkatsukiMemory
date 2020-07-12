package com.young.businessmine.utils

import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.databinding.BindingAdapter
import com.young.baselib.CustomProjectLiveData
import com.young.baselib.utils.TLog
import com.young.supportlib.floating.FloatingActionButton
import com.young.supportlib.floating.FloatingActionMenu

/*
 * Des
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
        @BindingAdapter(value = ["startTextAni"], requireAll = false)
        fun startAni(mTarget: View, time:Int) {
            TLog.log("BindingAdapter", "visible$time")
            mTarget.animate()
                .translationX(500f)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .setDuration(time.toLong())
                .setStartDelay(0);
        }

        @JvmStatic
        @BindingAdapter(value = ["startEndTitleAni"], requireAll = false)
        fun startAni2(mTarget: View, time:Int) {
            TLog.log("BindingAdapter", "visible$time")
            mTarget.animate()
                .alpha(0.5f)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .setDuration(time.toLong())
                .setStartDelay(0);
        }
    }
}