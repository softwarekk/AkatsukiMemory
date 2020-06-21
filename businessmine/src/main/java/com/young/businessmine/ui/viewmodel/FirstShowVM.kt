package com.young.businessmine.ui.viewmodel

import com.young.baselib.CustomProjectLiveData
import com.young.baselib.viewmodel.BaseUIViewMode

class FirstShowVM : BaseUIViewMode {


    var isMenuOpen=CustomProjectLiveData<Boolean>()


    constructor() : super(){
        isMenuOpen.value=false
    }
}