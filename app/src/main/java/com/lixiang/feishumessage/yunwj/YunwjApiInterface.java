package com.lixiang.feishumessage.yunwj;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface YunwjApiInterface {

        @GET("60miao/qq.php")
        Call<TodayNewsRes> getTodayNews();
        static class TodayNewsRes{
               public int zt;
               public String tp;
               public String lx;
               public String lj;
               public List<List<String>> wb;
        }


        @GET("mo-yu/php.php")
        Call<MoyuRes> getMoyu();
        static class MoyuRes{
                public int zt;
                public String nr;
                public String wb;
                public String lx;
        }

        @GET("ls/ls.php")
        Call<TodayHistoryRes> getTodayHistory();
        static class TodayHistoryRes{
                public int zt;
                public String nr;
                public String wb;
                public String lx;
        }
}
