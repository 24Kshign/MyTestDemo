package com.share.candyhh.mytestdemo;

import com.jack.mc.cyg.cygtools.app.CygApplication;
import com.jack.mc.cyg.cygtools.app.HttpServletAddress;

/**
 * Created by jack on 17/2/14
 */

public class App extends CygApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpServletAddress.getInstance().setOnlineAddress("http://www.imooc.com/");
    }
}
