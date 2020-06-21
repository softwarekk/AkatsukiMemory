package com.young.businessmine.ui.viewmodel

import com.young.baselib.CustomProjectLiveData
import com.young.baselib.viewmodel.BaseUIViewMode

/*
 * Des
 * Author Young
 * Date 2020-06-21
 */class ContainerUIViewModel : BaseUIViewMode {
    var graphChange=CustomProjectLiveData<Boolean>()


    constructor() : super(){
        graphChange.value=false
    }
}