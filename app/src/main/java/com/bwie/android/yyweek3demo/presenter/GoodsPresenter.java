package com.bwie.android.yyweek3demo.presenter;

import com.bwie.android.yyweek3demo.bean.HomeData;
import com.bwie.android.yyweek3demo.contract.GoodsContract;
import com.bwie.android.yyweek3demo.model.GoodsModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class GoodsPresenter extends GoodsContract.GoodsPresrnter {
    private GoodsContract.IGoodsView iGoodsView;
    private GoodsModel goodsModel;

    public GoodsPresenter(GoodsContract.IGoodsView iGoodsView) {
        this.iGoodsView = iGoodsView;
        goodsModel = new GoodsModel();
    }

    @Override
    public void getData(HashMap<String, String> params) {
        if (goodsModel != null) {
            goodsModel.obtainData(params, new GoodsModel.GoogsCallback() {
                @Override
                public void success(String result) {
                    Gson gson = new Gson();
                    HomeData homeData = gson.fromJson(result, HomeData.class);
                    HomeData.DataBean data = homeData.getData();
                    if (iGoodsView != null) {
                        iGoodsView.success(data);
                    }
                }

                @Override
                public void error(String msg) {
                    if (iGoodsView != null) {
                        iGoodsView.failure(msg);
                    }
                }
            });
        }
    }
}
