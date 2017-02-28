package com.share.candyhh.mytestdemo.ui;

import android.os.Bundle;
import android.view.View;

import com.share.appbaseui.base.BaseActivity;
import com.share.candyhh.mytestdemo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.am_btn_listview, R.id.am_btn_recyclerview, R.id.am_btn_select_pic
            , R.id.am_btn_recyclerview_with_empty})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.am_btn_listview:
                ListViewActivity.start(this);
                break;
            case R.id.am_btn_recyclerview:
                RecyclerViewActivity.start(this);
                break;
            case R.id.am_btn_recyclerview_with_empty:
                ListTestActivity.start(this);
                break;
            case R.id.am_btn_select_pic:
                SelectPicActivity.start(this);
                break;
        }
    }
}