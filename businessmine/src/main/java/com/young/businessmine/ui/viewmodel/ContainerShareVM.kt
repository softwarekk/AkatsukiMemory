package com.young.businessmine.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.young.baselib.CustomProjectLiveData
import com.young.baselib.viewmodel.BaseUIViewMode

/*
 * Des
 * Author Young
 * Date 2020-05-18
 */class ContainerShareVM : ViewModel{
    var startHide=CustomProjectLiveData<Boolean>()//启动页 只要是首页之前的逻辑处理结束就隐藏
    var audioLists=CustomProjectLiveData<Array<String?>>()//音频播放的音乐列表数据


    constructor() : super(){
        startHide.value=false
        audioLists.value= arrayOf()
    }
}