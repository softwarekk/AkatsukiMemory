package com.young.businessmine.ui.viewmodel

import com.young.baselib.CustomProjectLiveData
import com.young.baselib.viewmodel.BaseUIViewMode

class FirstShowVM : BaseUIViewMode {


    var isMenuOpen=CustomProjectLiveData<Boolean>()

    //卷首三行
    var lineOneText=CustomProjectLiveData<String>()
    var lineOneAni=CustomProjectLiveData<Int>()
    var lineTwoText=CustomProjectLiveData<String>()
    var lineTwoAni=CustomProjectLiveData<Int>()
    var lineThreeText=CustomProjectLiveData<String>()
    var lineThreeAni=CustomProjectLiveData<Int>()

    //卷首subtitle
    var subTitle=CustomProjectLiveData<String>()
    var subAniTime=CustomProjectLiveData<Int>()
    var isShowSub=CustomProjectLiveData<Boolean>()

    //卷首显示
    var isShowBegin=CustomProjectLiveData<Boolean>()



    constructor() : super(){
        isShowSub.value=false
        isShowBegin.value=true
        isMenuOpen.value=false
        lineOneText.value="那堪曲终人散,那堪流光过隙"
        lineTwoText.value="那堪回首一起流泪戳手指"
        lineThreeText.value="那堪岁月静好莫失莫散莫离"
        subTitle.value="----致年轻过的火迷们"
    }
}