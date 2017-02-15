package com.share.candyhh.mytestdemo.model;

import com.jack.mc.cyg.cygtools.http.HttpResultFunc;
import com.share.candyhh.mytestdemo.api.BaseModel;
import com.share.candyhh.mytestdemo.bean.MainBean;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by jack on 17/2/14
 */

public class MainService extends BaseModel {

    public static MainService getInstance() {
        return getPresent(MainService.class);
    }

    public void execute(Subscriber<List<MainBean>> subscriber) {
        Observable observable = mServletApi.getMainInfo()
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }
}