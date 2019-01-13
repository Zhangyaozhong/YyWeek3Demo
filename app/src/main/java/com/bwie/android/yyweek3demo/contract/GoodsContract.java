package com.bwie.android.yyweek3demo.contract;

import com.bwie.android.yyweek3demo.bean.HomeData;
import com.bwie.android.yyweek3demo.model.GoodsModel;

import java.util.HashMap;

public interface GoodsContract {
    abstract class GoodsPresrnter {
        public abstract void getData(HashMap<String, String> params);
    }

    interface IGoodsModel {
        void obtainData(HashMap<String, String> params, GoodsModel.GoogsCallback googsCallback);
    }

    interface IGoodsView {
        void success(HomeData.DataBean dataBean);

        void failure(String msg);
    }
}
