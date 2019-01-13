package com.bwie.android.yyweek3demo.model;

import com.bwie.android.yyweek3demo.Api;
import com.bwie.android.yyweek3demo.contract.GoodsContract;
import com.bwie.android.yyweek3demo.net.OkhttpCallback;
import com.bwie.android.yyweek3demo.net.OkhttpUtil;

import java.util.HashMap;

public class GoodsModel implements GoodsContract.IGoodsModel {
    @Override
    public void obtainData(HashMap<String, String> params, final GoogsCallback googsCallback) {
        OkhttpUtil.getInstance().doPost(Api.GOODS_URL, params, new OkhttpCallback() {
            @Override
            public void success(String result) {
                if (googsCallback != null) {
                    googsCallback.success(result);
                }
            }

            @Override
            public void failure(String msg) {
                if (googsCallback != null) {
                    googsCallback.error(msg);
                }
            }
        });
    }

    public interface GoogsCallback {
        void success(String result);

        void error(String msg);
    }

    private GoogsCallback googsCallback;

    public void setGoogsCallback(GoogsCallback googsCallback) {
        this.googsCallback = googsCallback;
    }
}
