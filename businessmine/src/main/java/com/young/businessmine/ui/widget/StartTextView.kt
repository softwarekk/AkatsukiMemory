package com.young.businessmine.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import com.young.baselib.utils.TLog
import com.young.supportlib.R

class StartTextView : View {

    constructor(context: Context?) : super(context){initData()}

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){initAttrs(attrs)
        initData()}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){  initAttrs(attrs)
        initData()
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val attr = context.obtainStyledAttributes(
            attrs,
            R.styleable.StartTextView,
            0,
            0
        )
        textContent=attr.getString(R.styleable.StartTextView_text_content)!!
        textColor=attr.getColor(R.styleable.StartTextView_text_color,Color.parseColor("#6d5129"))!!
        bzOffSet=attr.getDimension(R.styleable.StartTextView_text_bz_off,0f)!!
        textSize=attr.getDimension(R.styleable.StartTextView_text_size,80f)
        textShadowColor=attr.getColor(R.styleable.StartTextView_text_color,Color.RED)!!
        if(textShadowColor!=null){
            isShadowText=true
        }
        if(bzOffSet!=0f){
            isBZText=true
        }

    }

    private var mDensity = 0f
    private var textSize=80f//文字大小
    private var textContent=""//文字内容
    private var bzOffSet=0f//文字流动偏移
    private var isBZText=false//文字使用贝塞尔绘制  bzOffSet!=0 使用贝塞尔
    private var isShadowText=false//是否绘制阴影 textShadowColor!=0 使用阴影
    private val bSplinePath = Path()//文字绘制的路径
    private lateinit var paintText:Paint//字体
    private var textShadowColor:Int?=null//阴影字体颜色
    private var textColor:Int=Color.parseColor("#6d5129")//字体阴影颜色



    fun initData(){
        var displayMetrics = DisplayMetrics()
        displayMetrics = context.resources.displayMetrics
        mDensity = displayMetrics.density
        paintText= Paint()
    }
    fun drawText(canvas: Canvas){
        paintText.setStrokeWidth(5f) // 设置画笔宽度
        paintText.setAntiAlias(true)
        // 指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
        paintText.setTextSize(textSize) // 设置文字大小
        paintText.setColor(textColor) // 设置文字大小
        paintText.setStyle(Paint.Style.FILL) // 绘图样式，设置为填充

        if(isShadowText) {
            paintText.setStyle(Paint.Style.STROKE)
            paintText.setShadowLayer(10f, 15f, 15f,textShadowColor!!) // 设置阴影
        }
        if(isBZText) {
            val textWidth=paintText.measureText(textContent)
            val offWidth=(width-textWidth)/2
            TLog.log("draw_text","${width}___${offWidth}")
            paintText.setTextAlign(Paint.Align.CENTER)
            bSplinePath.moveTo(offWidth, this.height / 2f)
            bSplinePath.cubicTo(//加个20 左右边距
                width / 3f +offWidth, 10f,
                width / 3f * 2 , height*1f,
                width*1f-offWidth , height/3f
            )
            canvas.drawTextOnPath(textContent, bSplinePath, 0f, 15f, paintText)
        }else{
            paintText.setTextAlign(Paint.Align.RIGHT)
            val textWidth=paintText.measureText(textContent)
            val text: Paint.FontMetricsInt? = paintText.getFontMetricsInt()
            val textHeight=text?.bottom?.minus(text?.top)
            TLog.log("draw_text","${width}___${height/2f}++${ height/2f+
                    (textHeight?.div(4f)!!)}")
            canvas.drawText(textContent, 0, textContent.length, width*1f-30, height/2f+
                    (textHeight/4f!!
            ), paintText) // 对文字有效
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        TLog.log("draw_text","${width}___${event?.y}")

        return super.onTouchEvent(event)
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.run { drawText(canvas) }
    }
}