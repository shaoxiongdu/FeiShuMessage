package com.lixiang.feishumessage.yunwj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YunwjApi {

    private static YunwjApiInterface yunwjApi;

    public static final void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://bjb.yunwj.top/php/") //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();

        yunwjApi = retrofit.create(YunwjApiInterface.class);
    }

    public static void getTodayNews(Consumer<List<String>> success) {

        yunwjApi.getTodayNews().enqueue(new Callback<YunwjApiInterface.TodayNewsRes>() {
            @Override
            public void onResponse(Call<YunwjApiInterface.TodayNewsRes> call, Response<YunwjApiInterface.TodayNewsRes> response) {
                ArrayList<String> res = new ArrayList<>();
                response.body().wb.forEach(res::addAll);
                success.accept(res);
            }

            @Override
            public void onFailure(Call<YunwjApiInterface.TodayNewsRes> call, Throwable t) {
                System.out.println(t);
            }
        });

    }

    public static void getMoyu(Consumer<List<String>> success) {

        yunwjApi.getMoyu().enqueue(new Callback<YunwjApiInterface.MoyuRes>() {
            @Override
            public void onResponse(Call<YunwjApiInterface.MoyuRes> call, Response<YunwjApiInterface.MoyuRes> response) {
                String wb = response.body().wb;
                String[] split = wb.split("【换行】");
                success.accept(Arrays.asList(split));
            }

            @Override
            public void onFailure(Call<YunwjApiInterface.MoyuRes> call, Throwable t) {

            }
        });

    }

    public static void getTodayHistory(Consumer<List<String>> success) {

        yunwjApi.getTodayHistory().enqueue(new Callback<YunwjApiInterface.TodayHistoryRes>() {
            @Override
            public void onResponse(Call<YunwjApiInterface.TodayHistoryRes> call, Response<YunwjApiInterface.TodayHistoryRes> response) {
                String wb = response.body().wb;
                String[] split = wb.split("【换行】");
                success.accept(Arrays.asList(split));
            }

            @Override
            public void onFailure(Call<YunwjApiInterface.TodayHistoryRes> call, Throwable t) {

            }
        });

    }



}
