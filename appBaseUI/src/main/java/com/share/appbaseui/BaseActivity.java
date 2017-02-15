package com.share.appbaseui;

import com.jack.mc.cyg.cygtools.activity.CygBaseActivity;

/**
 * Created by jack on 17/2/14
 */

public class BaseActivity extends CygBaseActivity {


    /**
     * @param resId 布局Id
     * @param type  浸入模式:TYPE_BACKGROUND或者TYPE_LAYOUT
     */
    public void setView(int resId, String type, int statusColor) {
        immersionType = type;
        setView(resId, statusColor);
    }
}