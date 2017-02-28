package com.share.candyhh.mytestdemo.model.api;


import com.jack.mc.cyg.cygtools.http.BaseRetrofit;

/**
 *
 */
public class BaseModel extends BaseRetrofit {

    protected MainApi mServletApi;

    public BaseModel() {
        super();
        mServletApi = mRetrofit.create(MainApi.class);
    }

    public void onUnSubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
            mCompositeSubscription.unsubscribe();
    }
}
