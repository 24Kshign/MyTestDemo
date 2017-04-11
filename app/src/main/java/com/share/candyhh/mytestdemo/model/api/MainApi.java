package com.share.candyhh.mytestdemo.model.api;

import com.jack.mc.cyg.cygtools.http.BaseResponse;
import com.share.candyhh.mytestdemo.model.entity.ListViewBean;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 *
 */

public interface MainApi {

    @POST("/api/teacher?type=4&num=30")
    Observable<BaseResponse<List<ListViewBean>>> getMainInfo();

    @FormUrlEncoded
    @POST("/api/teacher")
    Observable<BaseResponse<List<ListViewBean>>> getMainInfo(@FieldMap Map<String,Object> map);
}