package com.share.candyhh.mytestdemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jack.mc.cyg.cygptr.recyclerview.RecyclerAdapterWithHF;
import com.jack.mc.cyg.cygtools.activity.CygStartActivity;
import com.jack.mc.cyg.cygtools.http.callback.CygSubscriberApi;
import com.jack.mc.cyg.cygtools.util.CygLog;
import com.jack.mc.cyg.cygtools.util.CygToast;
import com.share.appbaseui.base.BaseActivity;
import com.share.candyhh.mytestdemo.R;
import com.share.candyhh.mytestdemo.ui.adapter.RecyclerViewAdapter;
import com.share.candyhh.mytestdemo.model.ListViewModel;
import com.share.candyhh.mytestdemo.model.entity.ListViewBean;
import com.share.jack.cygwidget.loadmore.OnScrollToBottomLoadMoreListener;
import com.share.jack.cygwidget.recyclerview.PtrRecyclerViewUIComponent;
import com.share.jack.cygwidget.refersh.OnPullToRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */

public class RecyclerViewActivity extends BaseActivity {

    public static void start(Context context) {
        CygStartActivity.start(context, RecyclerViewActivity.class);
    }

    @BindView(R.id.ar_recyclerview_uicomponent)
    PtrRecyclerViewUIComponent ptrRecyclerViewUIComponent;
    @BindView(R.id.ar_empty_view)
    View emptyView;
    private RecyclerViewAdapter adapter;
    private RecyclerAdapterWithHF mAdapter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        handler = new Handler();
        adapter = new RecyclerViewAdapter(this);
        mAdapter = new RecyclerAdapterWithHF(adapter);
        ptrRecyclerViewUIComponent.setLayoutManager(new LinearLayoutManager(this));
        ptrRecyclerViewUIComponent.setAdapter(mAdapter);
        ptrRecyclerViewUIComponent.setEmptyView(emptyView);
        ptrRecyclerViewUIComponent.setOnPullToRefreshListener(new OnPullToRefreshListener() {
            @Override
            public void onPullToRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ListViewModel.getInstance().execute(new CygSubscriberApi<List<ListViewBean>>(RecyclerViewActivity.this) {
                            @Override
                            protected void onBaseNext(List<ListViewBean> data) {
                                adapter.setDataList(data);
                                mAdapter.notifyDataSetChanged();
                                CygLog.debug("data.size=" + data.size());
                                ptrRecyclerViewUIComponent.refreshComplete();
                                if (!ptrRecyclerViewUIComponent.isLoadMoreEnable()) {
                                    ptrRecyclerViewUIComponent.setLoadMoreEnable(true);
                                }
                            }

                            @Override
                            protected void onBaseError(Throwable t) {
                                super.onBaseError(t);
                                ptrRecyclerViewUIComponent.refreshComplete();
                            }
                        });
                    }
                }, 1000);
            }
        });


        ptrRecyclerViewUIComponent.setOnScrollToBottomLoadMoreListener(new OnScrollToBottomLoadMoreListener() {
            @Override
            public void onScrollToBottomLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CygToast.showToast("暂无更多数据");
                        ptrRecyclerViewUIComponent.loadMoreComplete(true);
                    }
                }, 1000);
            }
        });
        ptrRecyclerViewUIComponent.delayRefresh(200);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ListViewModel.getInstance().onUnSubscribe();
    }
}
