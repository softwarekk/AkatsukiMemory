package com.young.commomlib.network.interceptor;


import com.young.baselib.utils.TLog;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 */
public class ResponseInterceptor implements Interceptor {

    public ResponseInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startRequestTime = System.currentTimeMillis();
        String url = request.url().toString();
        Response response = null;
        try {
            response = chain.proceed(request);
        } catch (SocketTimeoutException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        String rawJson = response.body() == null ? "" : response.body().string();
        TLog.log("testResonseinterceptor","1111"+rawJson);
        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), rawJson)).build();
    }
}
