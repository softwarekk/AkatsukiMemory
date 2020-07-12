package com.young.businessmine.ui.widget

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.young.baselib.utils.TLog
import com.young.businessmine.R
import com.young.businessmine.databinding.StartLayoutViewBinding
import com.young.businessmine.utils.BindingUtils
import com.young.commomlib.utils.DisplayUtils
import com.young.commomlib.utils.ImageUtils

/*
* 首页的开场白
* 应该构建个viewmodel 用数据驱动UI
* 业务固定万年不动一切从简
* */
class StartShowView :View {

    constructor(context: Context?) : super(context!!){
        initData()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs){
        initData()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    )

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context!!, attrs, defStyleAttr, defStyleRes)


    companion object{
        const val DRAW_TEXT:Int=1
        const val DRAW_TEXT_LINE:Int=2
        const val DRAW_SUB_END_TEXT:Int=3

    }
    private lateinit var mCavans:Canvas
    private var paintBg:Paint?=null//背景
    private lateinit var paintMember:Paint//成员图
    private lateinit var paintText:Paint//字体
    private lateinit var bgBitmap:Bitmap//背景图
    private var mDensity = 0f
    private val mRadius: Int = DisplayUtils.dp2px(100f)
    private val mCy: Int =  DisplayUtils.dp2px(550f)
    private var memberBitmaps=ArrayList<Bitmap>()
    private var titleTextContent="卷首语"
    private var missionControl=DrawHandler()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mCavans=canvas!!
        drawBG(canvas)
        drawText(canvas)
    }

    private fun initData() {
        var displayMetrics = DisplayMetrics()
        displayMetrics = context.resources.displayMetrics
        mDensity = displayMetrics.density
        bgBitmap=BitmapFactory.decodeResource(this.context.resources, R.drawable.bg_juan_shou)
        memberBitmaps.add(ImageUtils.getBitmap(R.drawable.member_tobi,DisplayUtils.dp2px(60f),DisplayUtils.dp2px(152f)))
        memberBitmaps.add(ImageUtils.getBitmap(R.drawable.member_deidara,DisplayUtils.dp2px(60f),DisplayUtils.dp2px(152f)) )
        memberBitmaps.add(ImageUtils.getBitmap(R.drawable.member_sasori,DisplayUtils.dp2px(60f),DisplayUtils.dp2px(152f)))
        memberBitmaps.add(ImageUtils.getBitmap(R.drawable.member_hidan,DisplayUtils.dp2px(60f),DisplayUtils.dp2px(152f)))
        memberBitmaps.add(ImageUtils.getBitmap(R.drawable.member_kakuzu,DisplayUtils.dp2px(60f),DisplayUtils.dp2px(152f)))
        memberBitmaps.add(ImageUtils.getBitmap(R.drawable.member_kisame,DisplayUtils.dp2px(60f),DisplayUtils.dp2px(152f)))
        memberBitmaps.add(ImageUtils.getBitmap(R.drawable.member_konan,DisplayUtils.dp2px(60f),DisplayUtils.dp2px(152f)))
        memberBitmaps.add(ImageUtils.getBitmap(R.drawable.member_itach,DisplayUtils.dp2px(60f),DisplayUtils.dp2px(152f)))
        memberBitmaps.add(ImageUtils.getBitmap(R.drawable.member_pain,DisplayUtils.dp2px(60f),DisplayUtils.dp2px(152f)))
        paintBg=Paint()
        paintText=Paint()
        paintMember= Paint()
    }


    private fun drawBG(canvas: Canvas) {
        TLog.log("draw_bigmap","${width}___${height}__${bgBitmap.width}__${bgBitmap.height}")
        var offwidth=width.minus(bgBitmap.width)/2
        var offHeight=height.minus(bgBitmap.height)/2
        canvas.drawBitmap(bgBitmap,offwidth*1f,offHeight*1f,paintBg)
    }
    private fun drawText(canvas: Canvas){
        paintText.color = Color.RED // 设置画笔颜色96955c
        paintText.strokeWidth = 5f // 设置画笔宽度
        paintText.isAntiAlias = true
        // 指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
        paintText.textSize = 80f // 设置文字大小
        paintText.style = Paint.Style.FILL // 绘图样式，设置为填充
        paintText.color = Color.parseColor("#6d5129") // 设置画笔颜色
        canvas.translate(100 * mDensity, 5 * mDensity)
        paintText.textAlign = Paint.Align.CENTER
        val textWidth: Float = paintText.measureText(titleTextContent)
        canvas.drawText(titleTextContent, 0, 3,textWidth, 70*mDensity, paintText)
    }
    inner class DrawHandler : Handler {
        constructor() : super()

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                DRAW_TEXT->{

                }
                DRAW_TEXT_LINE->{

                }
                DRAW_SUB_END_TEXT->{
                }
            }
        }
    }
}