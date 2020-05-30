package com.young.businessmine.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.young.baselib.utils.TLog

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
    }
}