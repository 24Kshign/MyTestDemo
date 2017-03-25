package com.share.candyhh.mytestdemo.ui;

import android.content.Context;
import android.os.Bundle;

import com.jack.mc.cyg.cygtools.activity.CygStartActivity;
import com.share.appbaseui.base.BaseActivity;
import com.share.candyhh.mytestdemo.R;
import com.share.jack.cygwidget.verify_code.CustomVerifyCodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jack on 17/3/25
 */

public class VerifyCodeActivity extends BaseActivity {

    public static void start(Context context) {
        CygStartActivity.start(context, VerifyCodeActivity.class);
    }

    @BindView(R.id.avc_verify_code)
    CustomVerifyCodeView avcVerifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.avc_verify_code)
    public void onClick() {
        avcVerifyCode.setTimeCount(60);
        avcVerifyCode.startAnim();
    }
}
