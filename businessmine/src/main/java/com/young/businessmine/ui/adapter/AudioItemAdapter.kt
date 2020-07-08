package com.young.businessmine.ui.adapter

import android.content.Context
import com.young.businessmine.R
import com.young.businessmine.databinding.AudioItemLayoutBinding
import com.young.commomlib.base.BaseBindingAdapter

class AudioItemAdapter :BaseBindingAdapter<AudioItemLayoutBinding,List<String>> {
    constructor(context: Context) : super(context, R.layout.audio_item_layout)
    override fun bindView(viewHolder: CommonViewHolder?, position: Int) {
    }
}