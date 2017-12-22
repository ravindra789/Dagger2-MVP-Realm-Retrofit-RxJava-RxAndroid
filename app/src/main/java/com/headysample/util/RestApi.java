package com.headysample.util;

import com.headysample.model.response.AllData;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nndra on 06-Dec-17.
 */

public interface RestApi {

    @GET("/json")
    Observable<AllData> getAllData();


    @GET("/json")
    Call<AllData> getAllTheData();


}
