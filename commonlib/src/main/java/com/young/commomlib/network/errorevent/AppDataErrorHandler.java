package com.young.commomlib.network.errorevent;


/**
 */

import io.reactivex.functions.Function;

/**
 * HandleFuc处理以下网络错误：
 * 1、应用数据的错误会抛RuntimeException；
 */
public class AppDataErrorHandler implements Function {
    @Override
    public Object apply(Object o) throws Exception {
        return o;
    }
//    @Override
//    public BaseResponse apply(BaseResponse response) throws Exception {
//        //response中code码不会0 出现错误
//        if (response instanceof BaseResponse && response.showapiResCode != 0)
//            throw new RuntimeException(response.showapiResCode + "" + (response.showapiResError != null ? response.showapiResError : ""));
//        return response;
//    }
}