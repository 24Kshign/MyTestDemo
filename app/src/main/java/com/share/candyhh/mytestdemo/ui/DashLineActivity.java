package com.share.candyhh.mytestdemo.ui;

import android.content.Context;
import android.os.Bundle;

import com.jack.mc.cyg.cygtools.activity.CygStartActivity;
import com.share.appbaseui.base.BaseActivity;
import com.share.candyhh.mytestdemo.R;

/**
 * Created by jack on 17/3/25
 */

public class DashLineActivity extends BaseActivity {

    public static void start(Context context) {
        CygStartActivity.start(context, DashLineActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_line);
    }
}
