//package com.young.supportlib;
//
//public class test {
//    package com.young.supportlib
//
//import android.content.Context
//import android.graphics.*
//            import android.graphics.drawable.Drawable
//import android.os.Build
//import android.util.AttributeSet
//import android.view.MotionEvent
//import android.view.View
//import android.widget.ImageView
//import androidx.annotation.RequiresApi
//import com.young.baselib.utils.TLog
//import com.young.commomlib.utils.ScreenUtils
//import kotlin.math.cos
//import kotlin.math.sin
//
//
//    /*
//     * 写轮眼
//     * */
//    class SyaringanDrawable :View {
//
//
//        constructor(context: Context?) : super(context){
//            initData()
//        }
//
//        constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
//            initData()
//        }
//        constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
//                context,
//                attrs,
//                defStyleAttr
//        ){
//            initData()
//        }
//
//        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//                constructor(
//                context: Context?,
//                attrs: AttributeSet?,
//                defStyleAttr: Int,
//                defStyleRes: Int
//    ) : super(context, attrs, defStyleAttr, defStyleRes)
//
//
//        private val mPaint=Paint()
//        private val mPath=Path()
//        private val OTHER_ALPHA = 110
//        private val middlePoint=PointF()//中心坐标
//        private var centerWith=100f//中心红眼圆半径
//        private val centerJudeWidth=20f//组成勾玉的圆半径
//        private val LOG_TAG="SYARINGVIEW_LOG"
//        private var rotatoRange=0//旋转角度
//
//        private fun initData() {
////        centerWith=if (width==height||width>height) width/2f else height*2f
//            mPaint.style=Paint.Style.FILL_AND_STROKE
//            mPaint.isAntiAlias=true
//            mPaint.isDither=true
//            mPaint.setARGB(OTHER_ALPHA, 244, 92, 71)
//        }
//        /*
//         * 绘制右边贝塞尔
//         * startX 右边 起点x
//         * startY 右边 起点y
//         * */
//        private fun drawTaiChi(canvas: Canvas,startX:Float,startY:Float,endX:Float,endY:Float){
//
//            mPath.reset()
//            mPaint.color=Color.BLACK
//            mPath.moveTo(startX, startY)
//            mPath.quadTo(startX-centerJudeWidth-10f, startY, endX, endY)
//            canvas.drawPath(mPath,mPaint)
//            canvas.save()
//            canvas.restore()
//            mPath.reset()
//            mPaint.color=Color.RED
//            mPath.moveTo(startX, startY)
//            mPath.quadTo(startX-(centerJudeWidth/1.5.toFloat()), (startY-endY)/1.5.toFloat()+endY, endX,endY)
//            canvas.drawPath(mPath,mPaint)
//            canvas.save()
//            canvas.restore()
//        }
//        /*
//         *
//         * https://cubic-bezier.com/#.17,.67,.83,.67
//         * ubic-bezier(0,.27,0,.98) left
//         * cubic-bezier(0,.3,.43,1.02)
//         * */
//        override fun draw(canvas: Canvas) {
//            super.draw(canvas)
//            middlePoint.x=width/2f
//            middlePoint.y=height/2f
//            TLog.log(LOG_TAG,"${if (width==height||width>height) width/2f else height*2f}___${middlePoint.y}___$centerWith ___ $left ___ $top ___ $width ___ $height")
//            //红目和黑瞳心
//            mPaint.color=Color.RED
//            canvas.drawCircle(middlePoint.x, middlePoint.y,centerWith!!,mPaint)
//            mPaint.color=Color.BLACK
//            canvas.drawCircle(middlePoint.x, middlePoint.y,centerWith!!/1.5.toFloat(),mPaint)
//            mPaint.color=Color.RED
//            canvas.drawCircle(middlePoint.x, middlePoint.y,centerWith!!/1.5.toFloat()-20f,mPaint)
//            mPaint.color=Color.BLACK
//            canvas.drawCircle(middlePoint.x, middlePoint.y,centerWith!!/15.toFloat(),mPaint)
//            val toJudeSize=centerWith!!/1.5.toFloat()-20f;
//            drawJudeTaichi(canvas,middlePoint.x,middlePoint.y-toJudeSize-5f)//黑线一半
//        }
//
//        /*
//         * 根据圆心绘制单勾玉
//         * 先以圆心画圆 左贝塞尔 右60度贝塞尔
//         * 绘制成单勾玉
//         * startX 圆中心x
//         * startY 圆中心y
//         * */
//        private var leftStartX=0f
//        private var leftStartY=0f
//        private fun drawJudeTaichi(canvas: Canvas,startX:Float,startY: Float){
//            mPath.reset()
//            mPaint.color=Color.BLACK
//            canvas.drawCircle(startX,startY,centerJudeWidth,mPaint)
//            val endPoint=PointF(startX+centerJudeWidth,startY-(centerJudeWidth*2))//结束坐标
//            //计算left 开始坐标
//            leftStartX=startX-()
//            // 将画笔移动到起始点
//            mPath.moveTo(startX-centerJudeWidth, startY)
//            // 二阶贝塞尔曲线
//            mPath.quadTo(startX-centerJudeWidth, endPoint.y, endPoint.x, endPoint.y)
//            canvas.drawPath(mPath,mPaint)//right
//            val pointStartX= cos(Math.toRadians(60.0)) *centerJudeWidth
//            val pointStartY= sin(Math.toRadians(60.0))*centerJudeWidth
//            drawTaiChi(canvas,startX+pointStartX.toFloat(),startY-pointStartY.toFloat(),startX+centerJudeWidth,startY-(centerJudeWidth*2))
//        }
//
//        override fun onTouchEvent(event: MotionEvent?): Boolean {
//            TLog.log(LOG_TAG,"${event?.x} __ ${event?.y}")
//            return super.onTouchEvent(event)
//        }
//    }
//}
