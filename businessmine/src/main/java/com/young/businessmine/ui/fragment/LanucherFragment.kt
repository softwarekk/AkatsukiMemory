package com.young.businessmine.ui.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.res.AssetFileDescriptor
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.BounceInterpolator
import com.young.businessmine.BR
import com.young.businessmine.R
import com.young.businessmine.base.BusinessMineBaseFragment
import com.young.businessmine.databinding.FragmentLancherLayoutBinding
import com.young.businessmine.ui.viewmodel.LanucherVM
import com.young.commomlib.base.CommonFragment
import com.young.supportlib.AudioPlayer
import com.young.supportlib.bloom.Bloom
import com.young.supportlib.bloom.effector.BloomEffector
import com.young.supportlib.bloom.listener.BloomListener


/*
 * Des
 * Author Young
 * Date 2020-05-24
 */
class LanucherFragment : BusinessMineBaseFragment<FragmentLancherLayoutBinding, LanucherVM>(),BloomListener{


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed(Runnable {
            Bloom.with(activity)
                .setParticleRadius(12f)
                .setEffector(
                    BloomEffector.Builder()
                        .setDuration(1000)
                        .setSkewRange(0.05f, 0.15f)
                        .setAnchor((getBinding()?.lanuchLogoImg?.width / 2).toFloat(), (getBinding()?.lanuchLogoImg?.height / 2).toFloat())
                        .build()
                )
                .setBloomListener(this)
                .boom(getBinding().lanuchLogoImg)
        },500)
    }
    override val bindingVariable: Int
        get() =BR.lanucher_vm
    override val layoutId: Int
        get() = R.layout.fragment_lancher_layout

    override fun getUIViewModel(): LanucherVM {
        return getFragmentViewModelProvider(this)?.get(LanucherVM::class.java)!!
    }

    override fun onBegin() {
        getBinding()?.lanuchLogoImg.visibility= View.INVISIBLE
    }

    override fun onEnd() {
        getBinding()?.lanuchLogoImg.setScaleX(0f)
        getBinding()?.lanuchLogoImg.setScaleY(0f)
        getBinding()?.lanuchLogoImg.setClickable(false)
        getBinding()?.lanuchLogoImg.setVisibility(View.GONE)

        getBinding()?.lanuchLogoImg.animate()
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    getBinding()?.lanuchLogoImg.setClickable(true)
                    mContainerVM.startHide.value=true
                }
            })
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(1000)
            .setInterpolator(BounceInterpolator())
            .start()
    }

}