package com.young.businessmine.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.young.businessmine.R
import com.young.businessmine.databinding.StartLayoutViewBinding

/*
*控制首页开场白业务‘
* 动画
* */
class StartViewControl : RelativeLayout {

    constructor(context: Context?) : super(context){
        initView()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        initView()

    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){        initView()
    }

    fun initView(){
        var binding= DataBindingUtil.inflate<StartLayoutViewBinding>(LayoutInflater.from(context),
            R.layout.start_layout_view,this,false)
    }
}