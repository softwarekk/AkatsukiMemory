package com.young.businessmine.ui.widget

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class StartBitmapCardView :View{

    constructor(context: Context?) : super(context){
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init()

    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){        init()
    }


    private lateinit var paintBitmap:Paint//字体

    fun init(){
        paintBitmap= Paint()

    }
}