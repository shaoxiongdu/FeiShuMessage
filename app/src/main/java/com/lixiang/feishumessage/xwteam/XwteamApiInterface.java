package com.lixiang.feishumessage.xwteam;

import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface XwteamApiInterface {

    @GET("soup/simp")
    Call<Res> getDogLickingQuotes(@Query("key") String key);

    class Res {
        int code;
        String msg;
        String data;
    }

    @GET("weibo/hot")
    Call<WeiboHotRes> getWeiboHot(@Query("key") String key);
    class WeiboHotRes{
        int code;
        String msg;
        Data data;

        public class Data {
           public String TOP_1;
           public String TOP_2;
           public String TOP_3;
           public String TOP_4;
           public String TOP_5;
           public String TOP_6;
           public String TOP_7;
           public String TOP_8;
           public String TOP_9;
           public String TOP_10;

            @Override
            public String toString() {
                return "微博热搜榜{" +
                        "TOP_1='" + TOP_1 + '\'' +
                        ", TOP_2='" + TOP_2 + '\'' +
                        ", TOP_3='" + TOP_3 + '\'' +
                        ", TOP_4='" + TOP_4 + '\'' +
                        ", TOP_5='" + TOP_5 + '\'' +
                        ", TOP_6='" + TOP_6 + '\'' +
                        ", TOP_7='" + TOP_7 + '\'' +
                        ", TOP_8='" + TOP_8 + '\'' +
                        ", TOP_9='" + TOP_9 + '\'' +
                        ", TOP_10='" + TOP_10 + '\'' +
                        '}';
            }
        }
    }

}
