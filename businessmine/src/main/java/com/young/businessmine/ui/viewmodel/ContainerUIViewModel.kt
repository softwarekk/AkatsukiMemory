package com.young.businessmine.ui.viewmodel

import com.young.baselib.CustomProjectLiveData
import com.young.baselib.viewmodel.BaseUIViewMode

/*
 * Des
 * Author Young
 * Date 2020-06-21
 */class ContainerUIViewModel : BaseUIViewMode {
    var graphChange=CustomProjectLiveData<Boolean>()//启动页相关逻辑执行完成 需要执行隐藏的UI逻辑
    var audioLists=CustomProjectLiveData<Array<String?>>()//音频播放的音乐列表UI数据


    constructor() : super(){
        graphChange.value=false
        audioLists.value= arrayOf()
    }
}