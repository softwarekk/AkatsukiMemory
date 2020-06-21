package com.young.businessmine.ui.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.young.businessmine.R

class StartShowView :View {

    constructor(context: Context?) : super(context){
        initData()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        initData()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)



   var paintBg:Paint?=null//背景
   var paintText:Paint?=null//字体
   var bgBitmap:Bitmap?=null//背景图



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBG(canvas!!)
    }

    private fun initData() {
        bgBitmap=BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bg_juan_shou);
        paintBg=Paint()
        paintText= Paint()
    }

    private fun drawBG(canvas: Canvas) {
        canvas.drawBitmap(bgBitmap!!,0f,0f,paintBg)
    }


}