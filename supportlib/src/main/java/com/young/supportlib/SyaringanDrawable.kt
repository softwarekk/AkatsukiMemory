package com.young.supportlib

import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import com.young.commomlib.utils.ScreenUtils
import kotlin.math.cos
import kotlin.math.sin


/*
* 写轮眼
* */
class SyaringanDrawable :Drawable {

    constructor() : super(){
        initData()
    }


    private val mPaint=Paint()
    private val mPath=Path()
    private val OTHER_ALPHA = 110
    private val middlePoint=PointF()//中心坐标
    private val centerWith=150f//中心红眼圆半径
    private val centerJudeWidth=25f//组成勾玉的圆半径

    private fun initData() {
        mPaint.style=Paint.Style.FILL_AND_STROKE
        mPaint.isAntiAlias=true
        mPaint.isDither=true
        mPaint.setARGB(OTHER_ALPHA, 244, 92, 71)
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
    override fun draw(canvas: Canvas) {
        middlePoint.x=ScreenUtils.getScreenWidth()/2.toFloat()
        middlePoint.y=ScreenUtils.getScreenHeight()/2.toFloat()

        //红目和黑瞳心
        mPaint.color=Color.RED
        canvas.drawCircle(ScreenUtils.getScreenWidth()/2.toFloat(),ScreenUtils.getScreenHeight()/2.toFloat(),centerWith,mPaint)
        mPaint.color=Color.BLACK
        canvas.drawCircle(ScreenUtils.getScreenWidth()/2.toFloat(),ScreenUtils.getScreenHeight()/2.toFloat(),centerWith/1.5.toFloat(),mPaint)
        mPaint.color=Color.RED
        canvas.drawCircle(ScreenUtils.getScreenWidth()/2.toFloat(),ScreenUtils.getScreenHeight()/2.toFloat(),centerWith/1.5.toFloat()-20f,mPaint)
        mPaint.color=Color.BLACK
        canvas.drawCircle(ScreenUtils.getScreenWidth()/2.toFloat(),ScreenUtils.getScreenHeight()/2.toFloat(),centerWith/15.toFloat(),mPaint)
        val toJudeSize=centerWith/1.5.toFloat()-20f;
        drawJudeTaichi(canvas,middlePoint.x,middlePoint.y-toJudeSize-5f)//黑线一半
    }

    /*
    * 根据圆心绘制单勾玉
    * 先以圆心画圆 左贝塞尔 右60度贝塞尔
    * 绘制成单勾玉
    * startX 圆中心x
    * startY 圆中心y
    * */
    private fun drawJudeTaichi(canvas: Canvas,startX:Float,startY: Float){
        mPath.reset()
        mPaint.color=Color.BLACK
        canvas.drawCircle(startX,startY,centerJudeWidth,mPaint)
        val endPoint=PointF(startX+centerJudeWidth,startY-(centerJudeWidth*2))//结束坐标
        // 将画笔移动到起始点
        mPath.moveTo(startX-centerJudeWidth, startY)
        // 二阶贝塞尔曲线
        mPath.quadTo(startX-centerJudeWidth, endPoint.y, endPoint.x, endPoint.y)
        canvas.drawPath(mPath,mPaint)//right
        val pointStartX= cos(Math.toRadians(60.0)) *centerJudeWidth
        val pointStartY= sin(Math.toRadians(60.0))*centerJudeWidth
        drawTaiChi(canvas,startX+pointStartX.toFloat(),startY-pointStartY.toFloat(),startX+centerJudeWidth,startY-(centerJudeWidth*2))
    }


    override fun setAlpha(alpha: Int) {
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }
}