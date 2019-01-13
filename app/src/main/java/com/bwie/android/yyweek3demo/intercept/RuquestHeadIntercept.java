package com.bwie.android.yyweek3demo.intercept;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RuquestHeadIntercept implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.addHeader("userid", "");
        builder.addHeader("session", "");
        Response proceed = chain.proceed(request);
        return proceed;
    }
}
