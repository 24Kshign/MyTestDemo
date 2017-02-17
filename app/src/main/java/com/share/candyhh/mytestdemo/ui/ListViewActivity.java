package com.share.candyhh.mytestdemo.ui;

import android.os.Bundle;
import android.os.Handler;

import com.jack.mc.cyg.cygtools.http.callback.CygSubscriberApi;
import com.jack.mc.cyg.cygtools.util.CygLog;
import com.jack.mc.cyg.cygtools.util.CygToast;
import com.share.appbaseui.base.BaseActivity;
import com.share.candyhh.mytestdemo.R;
import com.share.candyhh.mytestdemo.adapter.ListViewAdapter;
import com.share.candyhh.mytestdemo.model.entity.ListViewBean;
import com.share.candyhh.mytestdemo.model.ListViewModel;
import com.share.jack.cygwidget.listview.PtrListViewUIComponent;
import com.share.jack.cygwidget.loadmore.OnScrollToBottomLoadMoreListener;
import com.share.jack.cygwidget.refersh.OnPullToRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewActivity extends BaseActivity {

    @BindView(R.id.am_listview_uicomponent)
    PtrListViewUIComponent ptrListViewUIComponent;

    private ListViewAdapter mAdapter;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mAdapter = new ListViewAdapter(this);
        ptrListViewUIComponent.setAdapter(mAdapter);

        ptrListViewUIComponent.setOnPullToRefreshListener(new OnPullToRefreshListener() {
            @Override
            public void onPullToRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ListViewModel.getInstance().execute(new CygSubscriberApi<List<ListViewBean>>(ListViewActivity.this) {
                            @Override
                            protected void onBaseNext(List<ListViewBean> data) {
                                mAdapter.setDataList(data);
                                CygLog.debug("data.size=" + data.size());
                                ptrListViewUIComponent.refreshComplete();
                                if (!ptrListViewUIComponent.isLoadMoreEnable()) {
                                    ptrListViewUIComponent.setLoadMoreEnable(true);
                                }
                            }
                        });
                    }
                }, 1000);
            }
        });

        ptrListViewUIComponent.setOnScrollToBottomLoadMoreListener(new OnScrollToBottomLoadMoreListener() {
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
        ptrListViewUIComponent.delayRefresh(1000);
    }
}
