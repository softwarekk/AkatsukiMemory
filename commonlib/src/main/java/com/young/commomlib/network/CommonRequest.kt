package com.beile.commonlib.network

import com.young.commomlib.network.BaseRequestApi

/*
 * Des  common 网络请求业务体
 * Author Young
 * Date 2020-07-06
 */class CommonRequest : BaseRequestApi {
    @Volatile
    private var instance: CommonRequest? = null
    private var reuqestApiInterface: ICommonApi = retrofit.create(ICommonApi::class.java)

    constructor() : super("http://www.baidu.com" + "/") {//baseurl 截取第一个/之前的 结尾必须为/
    }
    fun getInstance(): CommonRequest? {
        if (instance == null) {
            synchronized(CommonRequest::class.java) {
                if (instance == null) {
                    instance = CommonRequest()
                }
            }
        }
        return instance
    }
}