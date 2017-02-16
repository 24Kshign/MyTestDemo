package com.share.appbaseui.titlebar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.share.appbaseui.R;


/**
 *
 */

public class TitleBarUIComponent extends RelativeLayout {

    public TitleBarUIComponent(Context context) {
        super(context);
    }

    public TitleBarUIComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleBarUIComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private TextView mTvTitleBarLeft;
    private TextView mTvTitleBarTitle;
    private TextView mTvTitleBarRight;
    private TextView mTvMessage;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTvTitleBarLeft = (TextView) findViewById(R.id.lt_tv_titlebar_left);
        mTvTitleBarTitle = (TextView) findViewById(R.id.lt_tv_titlebar_title);
        mTvTitleBarRight = (TextView) findViewById(R.id.lt_tv_titlebar_right);
        mTvMessage = (TextView) findViewById(R.id.lt_tv_message);
    }

    public void initLeft(String text, OnClickListener listener) {
        mTvTitleBarLeft.setText(text);
        mTvTitleBarLeft.setOnClickListener(listener);
    }

    public void setLeftTextContent(String text) {
        mTvTitleBarLeft.setText(text);
    }

    public void initLeft(int drawableRes, OnClickListener listener) {
        mTvTitleBarLeft.setCompoundDrawablesWithIntrinsicBounds(drawableRes, 0, 0, 0);
        mTvTitleBarLeft.setOnClickListener(listener);
    }

    public void initLeft(String text, int drawableRes, OnClickListener listener) {
        mTvTitleBarLeft.setText(text);
        mTvTitleBarLeft.setCompoundDrawablesWithIntrinsicBounds(drawableRes, 0, 0, 0);
        mTvTitleBarLeft.setOnClickListener(listener);
    }

    public void initTitle(String text) {
        mTvTitleBarTitle.setText(text);
    }

    public void initTitle(int drawableRes) {
        mTvTitleBarTitle.setBackgroundResource(drawableRes);
    }

    public void initRight(String text, OnClickListener listener) {
        mTvTitleBarRight.setText(text);
        mTvTitleBarRight.setOnClickListener(listener);
    }

    public void initRight(int drawableRes, OnClickListener listener) {
        mTvTitleBarRight.setCompoundDrawablesWithIntrinsicBounds(drawableRes, 0, 0, 0);
        mTvTitleBarRight.setOnClickListener(listener);
    }

    public void initRight(String text, int drawableRes, OnClickListener listener) {
        mTvTitleBarRight.setText(text);
        mTvTitleBarRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableRes, 0);
        mTvTitleBarRight.setOnClickListener(listener);
    }

    public void initMessageBtn(int drawableRes, OnClickListener listener) {
        mTvMessage.setCompoundDrawablesWithIntrinsicBounds(drawableRes, 0, 0, 0);
        mTvMessage.setOnClickListener(listener);
    }

    public void setMessageBtnRes(int drawableRes) {
        mTvMessage.setCompoundDrawablesWithIntrinsicBounds(drawableRes, 0, 0, 0);
    }
}
