////package com.young.businessmine.ui.widget;
////
////import android.animation.AnimatorSet;
////import android.animation.TimeInterpolator;
////import android.content.Context;
////import android.graphics.Bitmap;
////import android.graphics.BitmapFactory;
////import android.graphics.Canvas;
////import android.graphics.Color;
////import android.graphics.ColorFilter;
////import android.graphics.DashPathEffect;
////import android.graphics.Paint;
////import android.graphics.PixelFormat;
////import android.graphics.drawable.Drawable;
////import android.util.Log;
////import android.view.animation.AnimationUtils;
////
////import com.young.businessmine.R;
////
/////*
//// * Des
//// * Author
//// * Date 2020-06-21
//// */   class test2 {
////
////     package com.zero.matrixdemo;
////
////import android.animation.Animator;
////import android.animation.Animator.AnimatorListener;
////import android.animation.AnimatorSet;
////import android.animation.TimeInterpolator;
////import android.animation.ValueAnimator;
////import android.animation.ValueAnimator.AnimatorUpdateListener;
////import android.content.Context;
////import android.graphics.Bitmap;
////import android.graphics.BitmapFactory;
////import android.graphics.Canvas;
////import android.graphics.Color;
////import android.graphics.ColorFilter;
////import android.graphics.DashPathEffect;
////import android.graphics.Matrix;
////import android.graphics.Paint;
////import android.graphics.Path;
////import android.graphics.PixelFormat;
////import android.graphics.drawable.Drawable;
////import android.util.Log;
////import android.view.animation.AnimationUtils;
////
////    public class TaskClearDrawable extends Drawable {
////
////        private static final String TAG = "Zero";
////        //anmator state
////        private final int STATE_ORIGIN = 0;//初始状态
////        private final int STATE_ROTATE = STATE_ORIGIN + 1;//外圈旋转
////        private final int STATE_UP = STATE_ROTATE + 1;//上移
////        private final int STATE_DOWN = STATE_UP + 1;//下移
////        private final int STATE_FINISH = STATE_DOWN + 1;//结束
////
////        String getState(final int state) {
////            String result = "STATE_ORIGIN";
////            switch (state) {
////                case STATE_ORIGIN:
////                    result = "STATE_ORIGIN";
////                    break;
////                case STATE_ROTATE:
////                    result = "STATE_ROTATE";
////                    break;
////                case STATE_UP:
////                    result = "STATE_UP";
////                    break;
////                case STATE_DOWN:
////                    result = "STATE_DOWN";
////                    break;
////                case STATE_FINISH:
////                    result = "STATE_FINISH";
////                    break;
////                default:
////                    break;
////            }
////            return result;
////        }
////
////        //animator duration time
////        private final long DURATION_ROTATE = 1250;//外圈旋转时长
////        private final long DURATION_CLEANNING = 250;//× 缩小至 0的时长
////        private final long DURATION_POINT_UP = 250;// 点 往上移动的时长
////        private final long DURATION_POINT_DOWN = 350;// 点 往下移动的时长
////        private final long DURATION_FINISH = 200;// 短边缩放的时长
////        private final long DURATION_CLEANNING_DELAY = 1000;// cleanning 时长
////        private final long DURATION_ORIGIN_DELAY = 3000;// 返回初始状态的时长
////
////        private final float PI_DEGREE = (float) (180.0f / Math.PI);//180度/π是1弧度对应多少度,这里表示一弧度的大小(57.30)
////        private final float DRAWABLE_WIDTH = 180.0f;//drawable_width 宽度
////        private final float ROTATE_DEGREE_TOTAL = -1080.0f;//总共旋转的角度 即旋转3圈 6π
////
////        private final float PAINT_WIDTH = 4.0f;//画×的笔的宽度
////        private final float PAINT_WIDTH_OTHER = 1.0f;//画其他的笔的宽度
////        private final float CROSS_LENGTH = 62.0f;//×的长度
////        private final float CORSS_DEGREE = 45.0f / PI_DEGREE;//π/4 三角函数计算用 sin(π/4) = cos(π/4) = 0.707105
////        private final float UP_DISTANCE = 24.0f;//往上移动的距离
////        private final float DOWN_DISTANCE = 20.0f;//往下移动的距离
////        private final float FORK_LEFT_LEN = 33.0f;//左短边长度
////        private final float FORK_LEFT_DEGREE = 40.0f / PI_DEGREE;//左短边弧度
////        private final float FORK_RIGHT_LEN = 50.0f;//右长边长度
////        private final float FORK_RIGHT_DEGREE = 50.0f / PI_DEGREE;//右长边弧度
////        private final float CIRCLE_RADIUS = 3.0f;//圆点半径
////
////
////        private int mWidth, mHeight;
////        private int mAnimState = STATE_ORIGIN;//状态
////        private float mCleanningScale, mRotateDegreeScale;    //cleanning 缩放，旋转缩放
////        private float mScale = 0.0f;
////        private float mPaintWidth;//画笔宽度
////        private float mPaintWidthOther;
////        private float mViewScale;
////        private float mCenterX, mCenterY;
////        private float mCrossLen,oldCrossLen;
////        private float mPointRadius;
////        private float mForkLeftLen, mForkRightLen;
////        private float mPointUpLen, mPointDownLen;
////
////        private Paint mPaint;
////        private Paint mLinePaint;
////        private Bitmap mBgBitmap;
////        private Bitmap mCircleBitmap;
////        private TimeInterpolator fast_out_slow_in;
////        private TimeInterpolator fast_out_linear_in;
////        private AnimatorSet mAnimatorSet;
////        private Matrix mRotateMatrix = new Matrix();
////
////        public TaskClearDrawable(Context context, int width, int height) {
////            super();
////            init(context, width, height);
////        }
////
////        private void init(Context context, int width, int height) {
////            mWidth = width;
////            mHeight = height;
////            mPaint = new Paint();
////            mLinePaint = new Paint();
////
////            Bitmap tempCircleBitmap = BitmapFactory.
////                    decodeResource(context.getResources(), R.drawable.circle);
////            Bitmap tempBgBitmap =
////                    BitmapFactory.decodeResource(context.getResources(), R.drawable.bg);
////
////            mCircleBitmap =
////                    Bitmap.createScaledBitmap(tempCircleBitmap, mWidth, mHeight, true);
////            mBgBitmap =
////                    Bitmap.createScaledBitmap(tempBgBitmap, mWidth, mHeight, true);
////            mViewScale = mWidth / DRAWABLE_WIDTH;
////            Log.i(TAG, "init: mViewScale= " + mViewScale);
////
////            if (mCircleBitmap != tempCircleBitmap) {
////                tempCircleBitmap.recycle();
////            }
////
////            if (mBgBitmap != tempBgBitmap) {
////                tempBgBitmap.recycle();
////            }
////
////            mCenterX = mWidth / 2.0f;
////            mCenterY = mHeight / 2.0f;
////            mPaintWidth = PAINT_WIDTH * mViewScale;
////            mPaintWidthOther = PAINT_WIDTH_OTHER * mViewScale;
////            mCrossLen = CROSS_LENGTH * mViewScale;
////            mPointRadius = CIRCLE_RADIUS * mViewScale;
////            mForkLeftLen = FORK_LEFT_LEN * mViewScale;
////            mForkRightLen = FORK_RIGHT_LEN * mViewScale;
////            mPointUpLen = UP_DISTANCE * mViewScale;
////            mPointDownLen = DOWN_DISTANCE * mViewScale;
////
////            mCleanningScale = 1.0f;
////            mRotateDegreeScale = 0.0f;
////
////            fast_out_slow_in = AnimationUtils.loadInterpolator(
////                    context, android.R.interpolator.fast_out_slow_in);
////            fast_out_linear_in = AnimationUtils.loadInterpolator(
////                    context, android.R.interpolator.fast_out_linear_in);
////        }
////
////        @Override
////        public void draw(Canvas canvas) {
////
////            float x1,y1,x2,y2,x3,y3,x4,y4;
////            float length;//叉的长度
////            float sin_45 = (float) Math.sin(CORSS_DEGREE);
////            float cos_40 = (float) Math.cos(FORK_LEFT_DEGREE); // x = r * cos_40
////            float sin_40 = (float) Math.sin(FORK_LEFT_DEGREE); // y = r * sin_40
////
////            float cos_50 = (float) Math.cos(FORK_RIGHT_DEGREE); // x = r * cos_50
////            float sin_50 = (float) Math.sin(FORK_RIGHT_DEGREE); // y = r * sin_50
////
////            mPaint.setAntiAlias(true);
////            mPaint.setColor(0xffffffff);//argb
////            mPaint.setStyle(Paint.Style.STROKE);
////            mPaint.setStrokeWidth(mPaintWidth);
////            mPaint.setStrokeCap(Paint.Cap.ROUND);
////            //绘制背景
////            canvas.drawBitmap(mBgBitmap,0,0,mPaint);
////
////            //画辅助线 这不是项目真实需要的部分
////            mLinePaint.setAntiAlias(true);
////            mLinePaint.setColor(Color.BLUE);//argb
////            mLinePaint.setStyle(Paint.Style.STROKE);
////            mLinePaint.setStrokeWidth(4);
////            mLinePaint.setStrokeCap(Paint.Cap.ROUND);
////
////            //画虚线
////            mLinePaint.setPathEffect(new DashPathEffect(new float[]{20,10},0));
////            canvas.drawLine(0,mCenterY,mWidth,mCenterY,mLinePaint);
////            canvas.drawLine(mCenterX,0,mCenterX,mHeight,mLinePaint);
////            //根据五种不同的状态来绘制
////
////            switch (mAnimState){
////                case STATE_ORIGIN://绘制mCircleBitmap 绘制叉
////                    length = mCrossLen * sin_45/ 2.0f;
////                    x1 = mCenterX - length;
////                    y1 = mCenterY - length;
////                    x2 = mCenterX + length;
////                    y2 = mCenterY + length;
////
////                    x3 = mCenterX + length;
////                    y3 = mCenterY - length;
////                    x4 = mCenterX - length;
////                    y4 = mCenterY + length;
////                    drawPath(canvas,mPaint,x1,y1,x2,y2,x3,y3,x4,y4);//画叉
////                    canvas.drawBitmap(mCircleBitmap,0,0, null);//画圆圈
////                    break;
////                case STATE_ROTATE://旋转 matrix mCircleBitmap 绘制叉 drawPath 两个点成线
////                    float degree = ROTATE_DEGREE_TOTAL * mRotateDegreeScale;
////                    mRotateMatrix.setRotate(degree,mCenterX,mCenterY);
////                    canvas.drawBitmap(mCircleBitmap,mRotateMatrix,null);//画圆圈
////
////                    length = mCleanningScale*mCrossLen * sin_45/ 2.0f;
////                    x1 = mCenterX - length;
////                    y1 = mCenterY - length;
////                    x2 = mCenterX + length;
////                    y2 = mCenterY + length;
////
////                    x3 = mCenterX + length;
////                    y3 = mCenterY - length;
////                    x4 = mCenterX - length;
////                    y4 = mCenterY + length;
////                    drawPath(canvas,mPaint,x1,y1,x2,y2,x3,y3,x4,y4);//画叉
////                    break;
////                case STATE_UP://根据mCenterX, mCenterY - mPointUPLen * mScale 绘制圆点
////                    mPaint.setStyle(Paint.Style.FILL);
////                    mPaint.setStrokeWidth(mPaintWidthOther);
////                    float upLen = mPointUpLen * mScale;
////                    canvas.drawCircle(mCenterX,mCenterY - upLen,mPointRadius,mPaint);
////                    canvas.drawBitmap(mCircleBitmap,0,0, null);//画圆圈
////                    break;
////                case STATE_DOWN:// //根据mCenterX, mCenterY + mPointDownLen * mScale 绘制圆点
////                    mPaint.setStyle(Paint.Style.FILL);
////                    mPaint.setStrokeWidth(mPaintWidthOther);
////                    float downPosition = (mPointDownLen + mPointUpLen) * mScale;
////                    canvas.drawCircle(mCenterX,mCenterY - mPointUpLen + downPosition,mPointRadius,mPaint);
////                    canvas.drawBitmap(mCircleBitmap,0,0, null);//画圆圈
////                    break;
////                case STATE_FINISH://画勾勾 mCircleBitmap
////                    mPaint.setStyle(Paint.Style.STROKE);
////                    mPaint.setStrokeWidth(mPaintWidth);
////
//////                已知角度θ 半径r
//////                A（x,y）  中心点( cx,cy)
//////                x = r * cosθ
//////                y = r * sinθ
//////
//////                x1 = cx - r * cos40
//////                y1 =  cy  + mPointDownLen - r * sin40
////                    x1 = mCenterX - Math.abs(mScale * mForkLeftLen * cos_40);
////                    y1 = mCenterY + mPointDownLen - Math.abs(mScale * mForkLeftLen * sin_40);
////                    x2 = mCenterX;
////                    y2 = mCenterY + mPointDownLen;
////                    x3 = mCenterX;
////                    y3 = mCenterY + mPointDownLen;
////                    //               x4 = cx - r * cos50
//////                y4 =  cy  + mPointDownLen - r * sin50
////                    x4 = mCenterX + Math.abs(mScale * mForkRightLen * cos_50);
////                    y4 = mCenterY + mPointDownLen - Math.abs(mScale * mForkRightLen * sin_50);
////                    drawPath(canvas,mPaint,x1,y1,x2,y2,x3,y3,x4,y4);
////                    canvas.drawBitmap(mCircleBitmap,0,0, null);//画圆圈
////                    break;
////
////                default:break;
////            }
////
////        }
////
////
////        @Override
////        public int getOpacity() {
////            return PixelFormat.OPAQUE;
////        }
////
////        @Override
////        public void setAlpha(int alpha) {
////            mPaint.setAlpha(alpha);
////            invalidateSelf();
////        }
////
////        @Override
////        public void setColorFilter(ColorFilter cf) {
////            mPaint.setColorFilter(cf);
////            invalidateSelf();
////        }
////
////        private void drawPath(Canvas canvas, Paint paint,
////                              float x1, float y1, float x2, float y2,
////                              float x3, float y3, float x4, float y4) {
////            Path path = new Path();
////            path.moveTo(x1, y1);
////            path.lineTo(x2, y2);
////            path.moveTo(x3, y3);
////            path.lineTo(x4, y4);
////
////            canvas.drawPath(path, paint);
////        }
////
////
////
////        public boolean isRunning() {
////            if (null != mAnimatorSet) {
////                return mAnimatorSet.isRunning();
////            } else {
////                return false;
////            }
////        }
////    }
////
////}
//
//package com.zero.matrixdemo;
//
//import android.R.integer;
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Paint.Style;
//import android.graphics.Path;
//import android.graphics.Path.Direction;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.text.Layout;
//import android.text.StaticLayout;
//import android.text.TextPaint;
//import android.util.AttributeSet;
//import android.util.DisplayMetrics;
//import android.view.View;
//
//public class TextDrawView extends View {
//
//    private final int mRadius = Utils.dp2px(100);
//    private final int mCy = Utils.dp2px(550);
//    private float mDensity;
//    private Paint mPaint;
//
//    private String text = "澳大利亚曾质疑过日本科研捕鲸的真实性。2010年，澳大利亚政府曾向海牙国际法院提起诉讼，控告日本在南冰洋的“科研”捕鲸活动实则是商业捕鲸。2014年，国际法院对此作出终审裁决，认定日本“出于科研目的”的捕鲸理由不成立，其捕鲸行为违背了《国际捕鲸管制公约》。日本表示尊重国际法院的裁决，并有所收敛了一段时间，但捕鲸活动仍未终止。2018年9月，在IWC的巴西峰会上，日本重提恢复商业捕鲸的诉求，但又一次遭到委员会的否决。这被视为日本最终退出该组织的直接原因被“科研”捕杀的鲸鱼，是如何被送上餐桌的？以科研名义被捕杀的鲸鱼，最后被输送到日本国内，满足人们的口腹之欲。负责执行这一系列动作的是一个名为日本鲸类研究所的机构，其上属机构是日本水产厅。日本鲸类研究所对鲸鱼肉有一个有趣的称呼：科研调查的副产物。他们称，根据《国际捕鲸规则公约》第8条的规定，调查后的鲸鱼体应被尽可能充分地利用。因而在鲸鱼被捕捞到渔船上并完成了对其体型、皮脂、胃内容物等款项的检测后，鲸体即会被拆解，用于鲸肉消费品的生产。当渔船抵达日本后，一块块的鲸肉会被分送给日本各级消费市场，或是以远低于市场价的价格出售给各地政府、供应于日本小学生的午餐中。";
//
//    float[] curWidth = new float[1];
//    private int mScreenHeight;
//
//    public TextDrawView(Context context) {
//        super(context);
//        init(context);
//
//    }
//
//    public TextDrawView(Context context, AttributeSet set) {
//        super(context, set);
//        init(context);
//
//    }
//    private void init(Context context){
//
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        displayMetrics = context.getResources().getDisplayMetrics();
//        mDensity = displayMetrics.density;
//        mPaint = new Paint();
//
//        mScreenHeight = Utils.getScreenHeight(context);
//    }
//
//
//
//    @Override
//    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        if(heightMode == MeasureSpec.UNSPECIFIED){//为什么这么写
//            setMeasuredDimension(widthSize,mScreenHeight *2);
//        }
//
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        // TODO Auto-generated method stub
//        super.onDraw(canvas);
//
//        mPaint.setColor(Color.RED); // 设置画笔颜色
//
//        mPaint.setStrokeWidth(5);// 设置画笔宽度
//        mPaint.setAntiAlias(true);
//        // 指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
//        mPaint.setTextSize(80);// 设置文字大小
//        mPaint.setStyle(Style.FILL);// 绘图样式，设置为填充
//
//        float[] pos = new float[] { 80, 100, 80, 200, 80, 300, 80, 400,
//                25 * mDensity, 30 * mDensity,
//                25 * mDensity, 60 * mDensity,
//                25 * mDensity, 90 * mDensity,
//                25 * mDensity, 120 * mDensity,};
//        canvas.drawPosText("画图示例", pos, mPaint);// 两个构造函数
//
//        Path lineTextPath = new Path();
//        lineTextPath.moveTo(65 * mDensity, 5 * mDensity);
//        lineTextPath.lineTo(65 * mDensity, 200 * mDensity);
//        canvas.drawPath(lineTextPath, mPaint);
//        canvas.drawTextOnPath("画图示例string1", lineTextPath, 0, 11, mPaint);
//
//        canvas.save();
//        canvas.translate(100 * mDensity, 5 * mDensity);
//        canvas.rotate(90);
//        canvas.drawText("画图示例string2", 0, 11, 0, 0, mPaint);
//        canvas.restore();
//
//        canvas.save();
//        mPaint.setShadowLayer(10, 15, 15, Color.GREEN);// 设置阴影
//        canvas.drawText("画图示例string3", 0, 11, 140 * mDensity, 35 *mDensity, mPaint);// 对文字有效
//        canvas.drawCircle(200 * mDensity, 150 * mDensity, 40 * mDensity, mPaint);// 阴影对图形无效
//        canvas.restore();
//
//        for (int i = 0; i < 6; i++) {
//            mPaint.setTextScaleX(0.4f + 0.3f * i);
//            canvas.drawText("画", 0, 1,
//                    5* mDensity + 50 * mDensity * i, 250 * mDensity, mPaint);
//        }
//
//        //沿着任意路径
//        Path bSplinePath = new Path();
//        bSplinePath.moveTo(5 * mDensity, 320 * mDensity);
//        bSplinePath.cubicTo(80 * mDensity, 260 * mDensity,
//                200 * mDensity, 480 * mDensity,
//                350 * mDensity,350 * mDensity);
//        mPaint.setStyle(Style.STROKE);
//        // 先画出这两个路径
//        canvas.drawPath(bSplinePath, mPaint);
//        // 依据路径写出文字
//        String text = "风萧萧兮易水寒，壮士一去兮不复返";
//        mPaint.setColor(Color.GRAY);
//        mPaint.setTextScaleX(1.0f);
//        mPaint.setTextSize(20 * mDensity);
//        canvas.drawTextOnPath(text, bSplinePath, 0, 15, mPaint);
//
//
//        mPaint.reset();
//        canvas.drawLine(0,Utils.dp2px(420),getMeasuredWidth(),Utils.dp2px(420),mPaint);
//
//        //文字测量
//        //绘制一个圆
//        mPaint.setStyle(Style.STROKE);
//        mPaint.setColor(Color.GRAY);
//        mPaint.setStrokeWidth(Utils.dp2px(15));
//        canvas.drawCircle(Utils.dp2px(110),mCy,mRadius,mPaint);
//
//
//        mPaint.setStyle(Style.STROKE);
//        mPaint.setColor(Color.GRAY);
//        mPaint.setStrokeWidth(Utils.dp2px(15));
//        canvas.drawCircle(Utils.dp2px(320),mCy,mRadius,mPaint);
//
//        //画圆弧
//        mPaint.setColor(Color.GREEN);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        RectF rectArc = new RectF(Utils.dp2px(10),mCy - mRadius,Utils.dp2px(210),mCy + mRadius);
//        canvas.drawArc(rectArc,-90,225,false,mPaint);
//
//
//        Paint paintLine = new Paint();//这是一个反面教材，容易GC
//        paintLine.setStyle(Style.STROKE);
//        canvas.drawLine(0,mCy,getWidth(),mCy,paintLine);
//        canvas.drawLine(Utils.dp2px(110),mCy - mRadius,Utils.dp2px(110),mCy + mRadius,paintLine);
//
//        //开始绘制文字
//        mPaint.setStyle(Style.FILL);
//        mPaint.setTextAlign(Paint.Align.CENTER);
//        mPaint.setTextSize(Utils.dp2px(50));
//        //1.
//        Rect rect = new Rect();
//        mPaint.getTextBounds("fgab",0,4,rect);
//        float offsety = (rect.top + rect.bottom)/2;
//        canvas.drawText("fgab",Utils.dp2px(110),mCy - offsety,mPaint);
//
//        Rect rect1 = new Rect();
////        mPaint.getTextBounds("aaaa",0,4,rect1);
//
//        //2
//        Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
//        mPaint.getFontMetrics(fontMetrics);
//        float offsety2 = (fontMetrics.ascent + fontMetrics.descent)/2;
//        float offsety1 = (rect1.top + rect1.bottom)/2;
//        canvas.drawText("aaaa",Utils.dp2px(320),mCy - offsety2,mPaint);
//
//        mPaint.reset();
//        canvas.drawLine(0,Utils.dp2px(680),getMeasuredWidth(),Utils.dp2px(680),mPaint);
//
//        //文字绘制2
//        mPaint.setStyle(Style.FILL);
//        mPaint.setTextAlign(Paint.Align.LEFT);
//        mPaint.setTextSize(Utils.dp2px(150));
//        Rect rect3 = new Rect();
//        mPaint.getTextBounds("aaaa",0,4,rect3);
//        canvas.drawText("aaaa",0 - rect3.left,Utils.dp2px(800),mPaint);
//
//        mPaint.setTextSize(Utils.dp2px(15));
//        canvas.drawText("aaaa",0,Utils.dp2px(800) + mPaint.getFontSpacing(),mPaint);
//
//
//
//    }
//
//}
//
