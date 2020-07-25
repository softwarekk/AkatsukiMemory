package com.young.businessmine.utils.callback

import android.view.View

interface IBindingItemClick<T> {//adapter使用
    fun onClick(postion:Int,dataBean:T)
}
interface IBindingViewClick {//页面使用
    fun onClick(view: View)
}