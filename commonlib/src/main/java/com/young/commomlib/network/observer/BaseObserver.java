package com.young.commomlib.network.observer;

import android.util.Log;

import com.young.commomlib.network.errorevent.ExceptionHandle;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 */
public abstract class BaseObserver<T> implements Observer<T> {
    public BaseObserver() {
    }
    @Override
    public void onError(Throwable e) {
        Log.e("lvr", e.getMessage());
        // todo error somthing

        if(e instanceof ExceptionHandle.ResponeThrowable){
            onError((ExceptionHandle.ResponeThrowable)e);
        } else {
            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
    }


    @Override
    public void onComplete() {
    }


    public abstract void onError(ExceptionHandle.ResponeThrowable e);

}
