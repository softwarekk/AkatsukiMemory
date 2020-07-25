package com.young.supportlib.player

import com.young.baselib.CustomProjectLiveData

data class AudioListBean (var position : Int ,var audioDatas :List<AudioListDataBean>){
    fun playingBean():AudioListDataBean{//当前播放的数据
        return audioDatas[position]
    }
    //上一首 下一首数据
    fun nextBean():AudioListDataBean{//下一首数据
        return audioDatas[position]
    }
    fun isLast():Boolean{
        return position+1==audioDatas.size
    }
}

data class AudioListDataBean(val audioName:String,val url:String)