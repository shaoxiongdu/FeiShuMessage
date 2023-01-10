package com.lixiang.feishumessage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jinrishici.sdk.android.JinrishiciClient;
import com.jinrishici.sdk.android.factory.JinrishiciFactory;
import com.jinrishici.sdk.android.listener.JinrishiciCallback;
import com.jinrishici.sdk.android.model.JinrishiciRuntimeException;
import com.jinrishici.sdk.android.model.PoetySentence;
import com.lixiang.feishumessage.feishu.FeiShuApi;
import com.lixiang.feishumessage.xwteam.XwteamApi;
import com.lixiang.feishumessage.yunwj.YunwjApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JinrishiciFactory.init(this);

        FeiShuApi.init();
        XwteamApi.init();
        YunwjApi.init();

    }

    public void sendMsg(View view) {
        EditText countEditText = findViewById(R.id.count);
        EditText messageEditText = findViewById(R.id.message);

        int count = Integer.parseInt(countEditText.getText().toString());
        for (int i = 0; i < count; i++) {
            FeiShuApi.sendMsg(messageEditText.getText().toString(), FeiShuApi.HOOK_CHICKEN);
        }

        Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
    }

    public void sendLickTheDog(View view) {
        XwteamApi.getDogLickingQuotes((res) -> {
            FeiShuApi.sendMsg(res, FeiShuApi.HOOK_LICK_DOG);
        });
    }

    public void sendGushi(View view) {
        JinrishiciClient client = JinrishiciClient.getInstance();
        client.getOneSentenceBackground(new JinrishiciCallback() {
            @Override
            public void done(PoetySentence poetySentence) {
                FeiShuApi.sendMsg(poetySentence.getData().getContent(),FeiShuApi.HOOK_SHICI);
            }

            @Override
            public void error(JinrishiciRuntimeException e) {
            }
        });
    }

    public void sendWeiboHot(View view) {
        XwteamApi.getWeiboHot(weiboResData -> {
            FeiShuApi.sendMsg("现在是: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年M月d日 HH时mm分ss秒， 最近的微博热搜: ")), FeiShuApi.HOOK_CHICKEN);
            FeiShuApi.sendMsg("top1: " + weiboResData.TOP_1,FeiShuApi.HOOK_CHICKEN);
            FeiShuApi.sendMsg("top2: " + weiboResData.TOP_2,FeiShuApi.HOOK_CHICKEN);
            FeiShuApi.sendMsg("top3: " + weiboResData.TOP_3,FeiShuApi.HOOK_CHICKEN);
            FeiShuApi.sendMsg("top4: " + weiboResData.TOP_4,FeiShuApi.HOOK_CHICKEN);
            FeiShuApi.sendMsg("top5: " + weiboResData.TOP_5,FeiShuApi.HOOK_CHICKEN);
            FeiShuApi.sendMsg("top6: " + weiboResData.TOP_6,FeiShuApi.HOOK_CHICKEN);
            FeiShuApi.sendMsg("top7: " + weiboResData.TOP_7,FeiShuApi.HOOK_CHICKEN);
            FeiShuApi.sendMsg("top8: " + weiboResData.TOP_8,FeiShuApi.HOOK_CHICKEN);
            FeiShuApi.sendMsg("top9: " + weiboResData.TOP_9,FeiShuApi.HOOK_CHICKEN);
            FeiShuApi.sendMsg("top10: " + weiboResData.TOP_10,FeiShuApi.HOOK_CHICKEN);
        });
    }

    public void sendTodayNews(View view) {
        YunwjApi.getTodayNews(res -> {
            FeiShuApi.sendMsg("现在是: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年M月d日 HH时mm分ss秒， 今天的主要新闻: ")), FeiShuApi.HOOK_TODAY_NEWS);
            res.forEach(news -> {
                FeiShuApi.sendMsg(news, FeiShuApi.HOOK_TODAY_NEWS);
            });
        });
    }

    public void sendMoyu(View view) {
        YunwjApi.getMoyu(res -> {
            res.forEach(news -> {
                FeiShuApi.sendMsg(news, FeiShuApi.HOOK_MOYU);
            });
            FeiShuApi.sendMsg("sm sm sm !!!", FeiShuApi.HOOK_MOYU);
        });
    }

    public void sendHistoryToday(View view) {
        YunwjApi.getTodayHistory(res -> {
            LocalDate now = LocalDate.now();
            FeiShuApi.sendMsg("今天是 " + now.getMonthValue() + "月" + now.getDayOfMonth() + "日，历史上今天发生的事情有: ", FeiShuApi.HOOK_HISTORY_TODAY);
            res.forEach(news -> {

                FeiShuApi.sendMsg(news, FeiShuApi.HOOK_HISTORY_TODAY);
            });
        });
    }
    

}