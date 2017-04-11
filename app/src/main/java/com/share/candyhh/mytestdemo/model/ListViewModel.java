package com.share.candyhh.mytestdemo.model;

import com.jack.mc.cyg.cygtools.http.HttpResultFunc;
import com.share.candyhh.mytestdemo.model.api.BaseModel;
import com.share.candyhh.mytestdemo.model.entity.ListViewBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by jack on 17/2/14
 */

public class ListViewModel extends BaseModel {

    public static ListViewModel getInstance() {
        return getPresent(ListViewModel.class);
    }

    public void mapExecute(String type, String num, Subscriber<List<ListViewBean>> subscriber) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("num", num);
        Observable observable = mServletApi.getMainInfo(map).map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    public void execute(Subscriber<List<ListViewBean>> subscriber) {
        Observable observable = mServletApi.getMainInfo().map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }
}