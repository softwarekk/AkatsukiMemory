package com.young.businessmine.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.young.baselib.CustomProjectLiveData
import com.young.businessmine.data.AudioItemUIBean
import com.young.supportlib.player.AudioListDataBean

/*
 * Des businessmine module 数据共享层
 * Author Young
 * Date 2020-05-18
 */class ContainerShareVM : ViewModel{
    //业务数据
    var startHide=CustomProjectLiveData<Boolean>()//启动页 只要是首页之前的逻辑处理结束就隐藏
    var audioLists=CustomProjectLiveData<List<AudioItemUIBean>>()//音频播放的音乐列表数据
    var audioPosition=CustomProjectLiveData<Int>()//当前选中的的audio列表位置

    //记录新态数据
    var oldAudioPosition=0

    constructor() : super(){
        startHide.value=false
    }


}