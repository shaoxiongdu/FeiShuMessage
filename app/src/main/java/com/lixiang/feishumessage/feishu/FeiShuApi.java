package com.lixiang.feishumessage.feishu;

import android.util.Log;

import com.lixiang.feishumessage.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeiShuApi {
    public static final String HOOK_CHICKEN = "6bff281d-92d0-4994-8d5d-fcb6ff45c68f";
    public static final String HOOK_LICK_DOG = "090c2f54-8a55-4498-ba3d-fb4bd737ec06";
    public static final String HOOK_SHICI = "8247d032-76c3-4ab5-b7be-714fc60dd58c";
    public static final String HOOK_TODAY_NEWS = "7c550fb2-d051-47aa-8883-d6553e8d74e2";
    public static final String HOOK_MOYU = "8916fdf9-fc25-4050-ad26-536ec0255189";
    public static final String HOOK_HISTORY_TODAY = "8a87664a-546b-40da-bfff-df9212f1d72a";

    private static FeishuApiInterface feiShuApi;
    private static FeishuApiInterface.Req req;
    private static Retrofit retrofit;

    public static void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://open.feishu.cn/open-apis/bot/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        feiShuApi = retrofit.create(FeishuApiInterface.class);
        req = new FeishuApiInterface.Req("text",new FeishuApiInterface.Content(""));
    }

    public static void sendMsg(String msg, String hook) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("FeiShuApi", "try send: " + msg);
        req.content.text = msg;
        feiShuApi.sendMsg(req,hook).enqueue(new Callback<FeishuApiInterface.Res>() {
            @Override
            public void onResponse(Call<FeishuApiInterface.Res> call, Response<FeishuApiInterface.Res> response) {
                Log.i("FeiShuApi", "send success: " + msg);
            }

            @Override
            public void onFailure(Call<FeishuApiInterface.Res> call, Throwable t) {

            }
        });
    }
}

