package com.young.commomlib.network.observer;

import io.reactivex.Observer;

/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
public abstract class BaseObserver<T> implements Observer<T> {
//    SuperBaseModel baseModel;
//    public BaseObserver(SuperBaseModel baseModel) {
//        this.baseModel = baseModel;
//    }
//    @Override
//    public void onError(Throwable e) {
//        Log.e("lvr", e.getMessage());
//        // todo error somthing
//
//        if(e instanceof ExceptionHandle.ResponeThrowable){
//            onError((ExceptionHandle.ResponeThrowable)e);
//        } else {
//            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
//        }
//    }
//
//    @Override
//    public void onSubscribe(Disposable d) {
//        if(baseModel != null){
//            baseModel.addDisposable(d);
//        }
//    }
//
//
//    @Override
//    public void onComplete() {
//    }
//
//
//    public abstract void onError(ExceptionHandle.ResponeThrowable e);

}
