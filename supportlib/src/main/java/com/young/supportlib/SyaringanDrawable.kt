package com.young.supportlib

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import com.young.baselib.utils.TLog
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt


/*
* 写轮眼
* */
class SyaringanDrawable :View {


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
    ){
        initData()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)


    private val mPaint=Paint()
    private val mPath=Path()
    private val OTHER_ALPHA = 110
    private val middlePoint=PointF()//中心坐标
    private var centerWith=100f//中心红眼圆半径
    private val centerJudeWidth=20f//组成勾玉的圆半径
    private val LOG_TAG="SYARINGVIEW_LOG"
    private var rotatoRange=0f//旋转角度

    private fun initData() {
//        centerWith=if (width==height||width>height) width/2f else height*2f
        mPaint.style=Paint.Style.FILL_AND_STROKE
        mPaint.isAntiAlias=true
        mPaint.isDither=true
        mPaint.setARGB(OTHER_ALPHA, 244, 92, 71)
        doAni()

    }
    /*
    * 绘制右边贝塞尔
    * startX 右边 起点x
    * startY 右边 起点y
    * */
    private fun drawTaiChi(canvas: Canvas,startX:Float,startY:Float,endX:Float,endY:Float){

        mPath.reset()
        mPaint.color=Color.BLACK
        mPath.moveTo(startX, startY)
        mPath.quadTo(startX-centerJudeWidth-10f, startY, endX, endY)
        canvas.drawPath(mPath,mPaint)
        canvas.save()
        canvas.restore()
        mPath.reset()
        mPaint.color=Color.RED
        mPath.moveTo(startX, startY)
        mPath.quadTo(startX-(centerJudeWidth/1.5.toFloat()), (startY-endY)/1.5.toFloat()+endY, endX,endY)
        canvas.drawPath(mPath,mPaint)
        canvas.save()
        canvas.restore()
    }
    /*
    *
    * https://cubic-bezier.com/#.17,.67,.83,.67
    * ubic-bezier(0,.27,0,.98) left
    * cubic-bezier(0,.3,.43,1.02)
    * */
    private var judeMiddleX=0f
    private var judeMiddleY=0f
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        middlePoint.x=width/2f
        middlePoint.y=height/2f
        TLog.log(LOG_TAG,"${if (width==height||width>height) width/2f else height*2f}___${middlePoint.y}___$centerWith ___ $left ___ $top ___ $width ___ $height")
        //红目和黑瞳心
        mPaint.color=Color.RED
        canvas.drawCircle(middlePoint.x, middlePoint.y,centerWith!!,mPaint)
        mPaint.color=Color.BLACK
        canvas.drawCircle(middlePoint.x, middlePoint.y,centerWith!!/1.5.toFloat(),mPaint)
        mPaint.color=Color.RED
        canvas.drawCircle(middlePoint.x, middlePoint.y,centerWith!!/1.5.toFloat()-20f,mPaint)
        mPaint.color=Color.BLACK
        canvas.drawCircle(middlePoint.x, middlePoint.y,centerWith!!/15.toFloat(),mPaint)
        val toJudeSize=centerWith!!/1.5.toFloat()-20f
        judeMiddleX= (cos(rotatoRange.toDouble())*(centerWith!!/1.5.toFloat()+10)).toFloat()
        judeMiddleY= (sin(rotatoRange.toDouble()) *(centerWith!!/1.5.toFloat()+10)).toFloat()
        drawJudeTaichi(canvas,middlePoint.x+judeMiddleX,middlePoint.y-judeMiddleY)//黑线一半
    }

    /*
    * 根据圆心绘制单勾玉
    * 先以圆心画圆 左贝塞尔 右60度贝塞尔
    * 绘制成单勾玉
    * startX 圆中心x
    * startY 圆中心y
    * */
    private var leftStartX=0f
    private var leftStartY=0f
    private var endPointX=0f
    private var endPointY=0f
    private lateinit var endPoint:PointF
    private fun drawJudeTaichi(canvas: Canvas,startX:Float,startY: Float){
        mPath.reset()
        mPaint.color=Color.BLACK
        canvas.drawCircle(startX,startY,centerJudeWidth,mPaint)

        endPointX= (sqrt(centerJudeWidth.toDouble().pow(2.toDouble())+(centerJudeWidth * 2.toDouble()).pow(2.toDouble()))* cos(rotatoRange%90.toFloat()).toDouble()).toFloat()
        endPointY=
            (sqrt(centerJudeWidth.toDouble().pow(2.toDouble())+(centerJudeWidth * 2.toDouble()).pow(2.toDouble()))* sin(rotatoRange%90.toFloat()).toDouble()).toFloat()
        endPoint=PointF(startX+centerJudeWidth,startY-(centerJudeWidth*2))//结束坐标
        //计算left 开始坐标
        leftStartX=startX-(cos(Math.toRadians(rotatoRange.toDouble())).toFloat()*centerJudeWidth)
        leftStartY=startY-(sin(Math.toRadians(rotatoRange.toDouble())).toFloat()*centerJudeWidth)
        // 将画笔移动到起始点
        mPath.moveTo(leftStartX, leftStartY)
        // 二阶贝塞尔曲线
        mPath.quadTo(leftStartX, endPoint.y, endPoint.x, endPoint.y)
        canvas.drawPath(mPath,mPaint)//right
        val pointStartX= cos(Math.toRadians(60.0+rotatoRange)) *centerJudeWidth
        val pointStartY= sin(Math.toRadians(60.0+rotatoRange))*centerJudeWidth
        drawTaiChi(canvas,startX+pointStartX.toFloat(),startY-pointStartY.toFloat(),startX+centerJudeWidth,startY-(centerJudeWidth*2))
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        TLog.log(LOG_TAG,"${event?.x} __ ${event?.y}")
        return super.onTouchEvent(event)
    }

    private fun doAni(){
        // 1.2*n = 整数 1.5 *n = 整数 ==》 公倍数

        // 1.2  1.5 ==》 10
        // 1.2*n = 整数 1.5 *n = 整数 ==》 公倍数
        val valueAnimator = ValueAnimator.ofFloat(0f, 3600f)
        // 动画周期
        // 动画周期
        valueAnimator.duration = 10 * 1000.toLong()
        // 重复的模式：重新开始
        // 重复的模式：重新开始
        valueAnimator.repeatMode = ValueAnimator.RESTART
        // 重复的次数
        // 重复的次数
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        // 插值器
        // 插值器
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.addUpdateListener { animator ->
            rotatoRange = animator.animatedValue as Float
            invalidate()
        }
        valueAnimator.start()
    }
}