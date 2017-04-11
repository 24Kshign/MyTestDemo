package com.share.candyhh.mytestdemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jack.mc.cyg.cygtools.activity.CygStartActivity;
import com.share.appbaseui.base.BaseActivity;
import com.share.candyhh.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jack on 17/4/7
 */

public class TestActivity extends BaseActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    public static void start(Context context) {
        CygStartActivity.start(context, TestActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        text.setText("大傻逼");
                        swipe.setRefreshing(false);
                        image.setImageResource(R.drawable.ic_launcher);
                    }
                }, 1000);
            }
        });
    }
}
