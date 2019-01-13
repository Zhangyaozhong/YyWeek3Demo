package com.bwie.android.yyweek3demo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SearchView extends LinearLayout implements View.OnClickListener {

    private ImageView iv_qr_code;
    private EditText ed_search;
    private Button search_btn;

    public SearchView(Context context) {
            this(context, null);
        }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
            this(context, attrs, 0);
        }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.search_layout, this, true);
        iv_qr_code = view.findViewById(R.id.iv_qr_code);
        ed_search = view.findViewById(R.id.ed_search);
        search_btn = view.findViewById(R.id.search_btn);
        iv_qr_code.setOnClickListener(this);
        search_btn.setOnClickListener(this);
    }

    //    定义接口属性
    private SearchCallback searchCallback;
//    定义set方法供外部调用   调用者调用

    public void setSearchCallback(SearchCallback searchCallback) {
        this.searchCallback = searchCallback;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_qr_code:
                if (searchCallback != null) {
                    searchCallback.qrOnClick();
                }
                break;
            case R.id.search_btn:
                String keywords = ed_search.getText().toString();
                if (TextUtils.isEmpty(keywords)) {
                    if (searchCallback != null) {
                        searchCallback.judgeSearchContent();
                    }
                    return;
                }
                if (searchCallback != null) {
                    searchCallback.search(keywords);
                }

                break;

        }
    }

    public interface SearchCallback {
        //判断输入框是否为空
        void judgeSearchContent();

        //二维码按钮回调方法
        void qrOnClick();

        //搜索回调方法
        void search(String keywords);
    }
}
