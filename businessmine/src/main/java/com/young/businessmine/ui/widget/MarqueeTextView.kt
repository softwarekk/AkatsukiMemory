package com.young.businessmine.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.TextView

class MarqueeTextView :androidx.appcompat.widget.AppCompatButton{

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun isFocused(): Boolean {
        return true
    }
}
