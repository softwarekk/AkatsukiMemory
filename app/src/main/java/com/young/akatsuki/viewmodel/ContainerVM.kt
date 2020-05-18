package com.young.akatsuki.viewmodel

import com.young.baselib.CustomProjectLiveData
import com.young.baselib.viewmodel.BaseViewModel

/*
 * Des
 * Author Young
 * Date 2020-05-18
 */class ContainerVM : BaseViewModel{
    var test=CustomProjectLiveData<String>()
    constructor() : super(){
        test.value="111"
    }
    public fun getTestValue() : CustomProjectLiveData<String>{
        return test
    }
}