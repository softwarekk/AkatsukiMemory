package com.young.commomlib.viewmodel

import androidx.lifecycle.ViewModel
import com.young.baselib.CustomProjectLiveData

/*
 * Des common 级别的数据驱动
 * Author Young
 * Date 2020-05-18
 */class CommonShareVM : ViewModel {
    constructor() : super(
    ){
        user_level.value=1
    }
    private var user_level=CustomProjectLiveData<Int>()
    private fun  getUserLevel() : CustomProjectLiveData<Int>{
        return user_level;
    }
}