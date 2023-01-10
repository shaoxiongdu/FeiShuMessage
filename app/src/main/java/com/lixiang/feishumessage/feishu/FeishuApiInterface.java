package com.lixiang.feishumessage.feishu;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FeishuApiInterface {

    @POST("hook/{hook}")
    @Headers("Content-Type: application/json")
    Call<Res> sendMsg(@Body Req req, @Path("hook") String hook);

    class Res {

    }

    class Req{
        String msg_type;
        Content content;

        public Req(String msg_type,Content content) {
            this.msg_type = msg_type;
            this.content = content;
        }
    }
    class Content {
        String text;

        public Content(String text) {
            this.text = text;
        }
    }
}
