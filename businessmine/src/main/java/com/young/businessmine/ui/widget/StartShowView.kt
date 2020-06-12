package com.young.businessmine.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi

class StartShowView :View {

    constructor(context: Context?) : super(context){

    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
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

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}