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
    var listener:IBindingItemClick<AudioItemUIBean>
    constructor(context: Context,clickCallback:IBindingItemClick<AudioItemUIBean>) : super(context, R.layout.audio_item_layout){
        listener=clickCallback
    }
    override fun bindView(viewHolder: CommonViewHolder, position: Int) {
        viewHolder?.bindView?.run {
            data=getDataList()[position]
            dataIndex=position
            click=listener
            audioNameTv.isFocusable=true
        }
    }
}