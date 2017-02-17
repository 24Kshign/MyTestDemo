package com.share.candyhh.mytestdemo.model;

import com.jack.mc.cyg.cygtools.http.HttpResultFunc;
import com.share.candyhh.mytestdemo.model.api.BaseModel;
import com.share.candyhh.mytestdemo.model.entity.ListViewBean;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by jack on 17/2/14
 */

public class ListViewModel extends BaseModel {

    public static ListViewModel getInstance() {
        return getPresent(ListViewModel.class);
    }

    public void execute(Subscriber<List<ListViewBean>> subscriber) {
        Observable observable = mServletApi.getMainInfo()
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }
}