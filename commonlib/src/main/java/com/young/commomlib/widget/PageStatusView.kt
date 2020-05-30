package com.young.commomlib.widget

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.young.baselib.CustomProjectLiveData
import com.young.commomlib.Constants.Companion.PAGE_HIDE
import com.young.commomlib.Constants.Companion.PAGE_LOADING
import com.young.commomlib.R
import com.young.commomlib.databinding.PageStatusViewLayoutBinding

/*
 * Des
 * Author Young
 * Date 2020-05-30
 */class PageStatusView : FrameLayout
{

    private lateinit var binding:PageStatusViewLayoutBinding

    //驱动layout的数据
    private var imgShow=CustomProjectLiveData<Boolean>()
    private var textShow=CustomProjectLiveData<Boolean>()
    private var textContent=CustomProjectLiveData<String>()
    //PageStatusView 初始样式参数
    private var lottieStyle=false
    private var contentColor:Int?=null
    private var contentSize:Int?=null
    private var imgsource:Int?=null
    private var lottieSourceName:String?=null

    private fun init(context:Context,attrs : AttributeSet ,defStyleAttr: Int ){
        val attr =
            context.obtainStyledAttributes(attrs, R.styleable.PageStatusView, defStyleAttr, 0)
        contentColor=attr.getColor(R.styleable.PageStatusView_status_text_color, context.resources.getColor(R.color.design_default_color_background))
        contentSize=attr.getDimensionPixelSize(R.styleable.PageStatusView_status_text_size, 15)
        lottieStyle=attr.getBoolean(R.styleable.PageStatusView_status_img_lottie, false)
        imgsource= attr.getResourceId(R.styleable.PageStatusView_status_img_source,0)
        lottieSourceName=attr.getString(R.styleable.PageStatusView_status_lottie_source)
        initView()
    }

    private fun initView() {
        binding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.page_status_view_layout, this, true)
        binding.imgshow=imgShow
        binding.content=textContent
        binding.contentshow=textShow
        if(lottieStyle){
            binding?.centerImg.animation
            binding?.centerImg.setImageAssetsFolder("lottie");
            binding?.centerImg.setAnimation("lottie/"+lottieSourceName);
        }else{
            binding?.centerImg.setImageResource(imgsource!!)
        }
    }
    fun setPageStatus(status:Int){
        when(status){
            PAGE_LOADING->initLoading()
            PAGE_HIDE->initHide()
        }
    }

    private fun initHide() {
        imgShow.value=false
        textShow.value=false
        this.visibility= View.GONE
        if(lottieStyle){
            binding?.centerImg.cancelAnimation()
            binding?.centerImg.setProgress(0f)
        }
    }

    private fun initLoading(){
        imgShow.value=true
        textShow.value=true
        this.visibility= View.VISIBLE
        textContent.value=" chakula ing"
        if(lottieStyle){
            binding?.centerImg.playAnimation()
        }
    }
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init(context,attrs!!,0)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init(context,attrs!!,0)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes){
        init(context,attrs!!,0)
    }
}