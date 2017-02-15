package com.share.candyhh.mytestdemo.api;

import com.jack.mc.cyg.cygtools.http.BaseResponse;
import com.share.candyhh.mytestdemo.bean.MainBean;

import java.util.List;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jack on 17/2/14
 */

public interface MainApi {

    @POST("/api/teacher?type=4&num=30")
    Observable<BaseResponse<List<MainBean>>> getMainInfo();
}