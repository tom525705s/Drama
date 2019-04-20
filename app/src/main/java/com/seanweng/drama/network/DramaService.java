package com.seanweng.drama.network;

import com.seanweng.drama.utils.SSLSocketFactoryUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DramaService {
    private static DramaService INSTANCE = null;
    private Retrofit retrofit;

    private DramaService() {
        // 測製時遇到憑證問題, 故先強制通過
        OkHttpClient okhttpclient = new OkHttpClient.Builder()
                .sslSocketFactory(SSLSocketFactoryUtils.createSSLSocketFactory(), SSLSocketFactoryUtils.createTrustAllManager())
                .hostnameVerifier(new SSLSocketFactoryUtils.TrustAllHostnameVerifier())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.mocky.io")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpclient)
                .build();
    }

    public static DramaService getDramaClient() {
        if (INSTANCE == null) {
            synchronized (DramaService.class) {
                INSTANCE = new DramaService();
            }
        }
        return INSTANCE;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
