package com.share.candyhh.mytestdemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jack.mc.cyg.cygtools.activity.CygStartActivity;
import com.jack.mc.cyg.cygtools.http.callback.CygSubscriberApi;
import com.jack.mc.cyg.cygtools.inputmethod.CygView;
import com.jack.mc.cyg.cygtools.util.CygLog;
import com.share.appbaseui.base.BaseActivity;
import com.share.candyhh.mytestdemo.R;
import com.share.candyhh.mytestdemo.ui.adapter.RecyclerViewAdapter;
import com.share.candyhh.mytestdemo.model.ListViewModel;
import com.share.candyhh.mytestdemo.model.entity.ListViewBean;
import com.share.jack.cygwidget.recyclerview.RecyclerViewWithEV;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jack on 17/2/28
 */

public class ListTestActivity extends BaseActivity {

    public static void start(Context context) {
        CygStartActivity.start(context, ListTestActivity.class);
    }

    @BindView(R.id.alt_recyclerview)
    RecyclerViewWithEV altRecyclerview;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        adapter = new RecyclerViewAdapter(this, null);
        altRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        altRecyclerview.setAdapter(adapter);
        altRecyclerview.setEmptyView(CygView.inflateLayout(this, R.layout.view_empty));

    }

    private void getData() {
        ListViewModel.getInstance().mapExecute("4", "30", new CygSubscriberApi<List<ListViewBean>>(ListTestActivity.this) {
            @Override
            protected void onBaseNext(List<ListViewBean> data) {
                adapter.setDataList(data);
                CygLog.debug("data.size=" + data.size());
            }
        });
    }

    @OnClick({R.id.alt_btn_add, R.id.alt_btn_remove})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alt_btn_add:
                getData();
                break;
            case R.id.alt_btn_remove:
                removeData();
                break;
        }
    }

    private void removeData() {
        adapter.removeAllDataList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ListViewModel.getInstance().onUnSubscribe();
    }
}