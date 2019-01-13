package com.bwie.android.yyweek3demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bwie.android.yyweek3demo.bean.HomeData;
import com.bwie.android.yyweek3demo.contract.GoodsContract;
import com.bwie.android.yyweek3demo.presenter.GoodsPresenter;
import com.recker.flybanner.FlyBanner;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SearchView.SearchCallback, GoodsContract.IGoodsView {

    private static final int REQUEST_CODE = 1;
    private GoodsPresenter goodsPresenter;
    private List<String> list=new ArrayList<>();
    @BindView(R.id.banner)
    FlyBanner banner;
    @BindView(R.id.mSearchView)
    SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    /**
     * 处理数据
     */
    private void initData() {
        goodsPresenter.getData(new HashMap<String, String>());

    }

    private void initView() {

        mSearchView.setSearchCallback(this);
        goodsPresenter = new GoodsPresenter(this);
    }

    @Override
    public void judgeSearchContent() {
        Toast.makeText(this, "关键词不能为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void qrOnClick() {
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void search(String keywords) {
        Toast.makeText(MainActivity.this, keywords, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void success(HomeData.DataBean dataBean) {
        List<HomeData.DataBean.BannerBean> banner1 = dataBean.getBanner();
        for (HomeData.DataBean.BannerBean bannerBean : banner1) {
            String icon = bannerBean.getIcon();
            list.add(icon);
        }
        banner.setImagesUrl(list);
    }

    @Override
    public void failure(String msg) {

    }
}
