package com.lixiang.feishumessage.xwteam;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class XwteamApi {

    private static final String KEY = "88D3YXDg8kKtf7O1bAcqADB4VT";

    private static XwteamApiInterface xwteamApi;

    private static Retrofit retrofit;

    private String res;

    public static void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.xwteam.cn/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        xwteamApi = retrofit.create(XwteamApiInterface.class);
    }

    public static void getDogLickingQuotes(Consumer<String> success) {

        new Thread(() -> {
            xwteamApi.getDogLickingQuotes(KEY).enqueue(new Callback<XwteamApiInterface.Res>() {
                @Override
                public void onResponse(Call<XwteamApiInterface.Res> call, Response<XwteamApiInterface.Res> response) {
                    success.accept(response.body().data.toString());
                }

                @Override
                public void onFailure(Call<XwteamApiInterface.Res> call, Throwable t) {
                }
            });
        }).start();
    }

    public static void getWeiboHot(Consumer<XwteamApiInterface.WeiboHotRes.Data> success) {

        new Thread(() -> {
            xwteamApi.getWeiboHot(KEY).enqueue(new Callback<XwteamApiInterface.WeiboHotRes>() {
                @Override
                public void onResponse(Call<XwteamApiInterface.WeiboHotRes> call, Response<XwteamApiInterface.WeiboHotRes> response) {
                    success.accept(response.body().data);
                }

                @Override
                public void onFailure(Call<XwteamApiInterface.WeiboHotRes> call, Throwable t) {

                }
            });
        }).start();
    }



}
