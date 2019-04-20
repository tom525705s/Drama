package com.seanweng.drama.interfaces;

import com.seanweng.drama.dataBean.Dramas;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DramaAPIInterface {
    @GET("v2/5a97c59c30000047005c1ed2")
    Call<Dramas> getDramas();
}
