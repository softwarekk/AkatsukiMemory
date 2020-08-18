package com.young.businessmine.ui.adapter

import android.content.Context
import com.young.baselib.utils.TLog
import com.young.businessmine.R
import com.young.businessmine.data.AudioItemUIBean
import com.young.supportlib.player.AudioListDataBean
import com.young.businessmine.databinding.AudioItemLayoutBinding
import com.young.businessmine.utils.callback.IBindingItemClick
import com.young.commomlib.base.BaseBindingAdapter

class AudioItemAdapter :BaseBindingAdapter<AudioItemLayoutBinding, AudioItemUIBean> {
    private var listener:IBindingItemClick<AudioItemUIBean>
    private var playingIndex=0
    constructor(context: Context,clickCallback:IBindingItemClick<AudioItemUIBean>) : super(context, R.layout.audio_item_layout){
        listener=clickCallback
    }
    fun  setPlayingIndex(position:Int){
        playingIndex=position
    }
    override fun bindView(viewHolder: CommonViewHolder, position: Int) {
        viewHolder?.bindView?.run {
            data=getDataList()[position]
            isPlaying=playingIndex==position
            dataIndex=position
            click=listener
            audioNameTv.isFocusable=true
        }
    }
}