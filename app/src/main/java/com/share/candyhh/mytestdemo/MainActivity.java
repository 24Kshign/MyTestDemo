package com.share.candyhh.mytestdemo;

import android.os.Bundle;
import android.os.Handler;

import com.jack.mc.cyg.cygtools.http.callback.CygSubscriberApi;
import com.jack.mc.cyg.cygtools.util.CygLog;
import com.jack.mc.cyg.cygtools.util.CygToast;
import com.share.appbaseui.BaseActivity;
import com.share.candyhh.mytestdemo.adapter.MainAdapter;
import com.share.candyhh.mytestdemo.bean.MainBean;
import com.share.candyhh.mytestdemo.model.MainService;
import com.share.jack.cygwidget.listview.PtrListViewUIComponent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.am_listview_uicomponent)
    PtrListViewUIComponent ptrListViewUIComponent;

    private MainAdapter mAdapter;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAutoHideSoftInput(true);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mAdapter = new MainAdapter(this);
        ptrListViewUIComponent.setAdapter(mAdapter);

        ptrListViewUIComponent.setOnPullToRefreshListener(new PtrListViewUIComponent.OnPullToRefreshListener() {
            @Override
            public void onPullToRefresh() {
                MainService.getInstance().execute(new CygSubscriberApi<List<MainBean>>(MainActivity.this) {
                    @Override
                    protected void onBaseNext(List<MainBean> data) {
                        mAdapter.setDataList(data);
                        CygLog.debug("data.size=" + data.size());
                        ptrListViewUIComponent.refreshComplete();
                        if (!ptrListViewUIComponent.isLoadMoreEnable()) {
                            ptrListViewUIComponent.setLoadMoreEnable(true);
                        }
                    }
                });
            }
        });

        ptrListViewUIComponent.setOnScrollToBottomLoadMoreListener(new PtrListViewUIComponent.OnScrollToBottomLoadMoreListener() {
            @Override
            public void onScrollToBottomLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CygToast.showToast("暂无更多数据");
                        ptrListViewUIComponent.loadMoreComplete(true);
                    }
                }, 1000);
            }
        });
        //设置自动更新
//        ptrListViewUIComponent.delayRefresh(150);
    }
}
