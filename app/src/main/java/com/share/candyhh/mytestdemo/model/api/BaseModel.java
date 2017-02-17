package com.share.candyhh.mytestdemo.model.api;


import com.jack.mc.cyg.cygtools.http.NewBaseApi;

/**
 * Created by Jack on 2016/12/1.
 */
public class BaseModel extends NewBaseApi {

    protected MainApi mServletApi;

    public BaseModel() {
        super();
        mServletApi = mRetrofit.create(MainApi.class);
    }
}
