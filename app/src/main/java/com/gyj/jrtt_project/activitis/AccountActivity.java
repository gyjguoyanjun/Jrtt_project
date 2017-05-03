package com.gyj.jrtt_project.activitis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gyj.jrtt_project.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class AccountActivity extends AppCompatActivity {

    private ImageView iv_back_include_head_login;
    private Button acc_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
        initData();
    }

    private void initData() {
        iv_back_include_head_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        acc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("style_flag", MODE_PRIVATE);
                boolean login_flag = preferences.getBoolean("login_flag", true);
                SharedPreferences.Editor edit = preferences.edit();
                if (login_flag) {
                    edit.putBoolean("login_flag", false);
                    edit.commit();
                    Intent intent = new Intent(AccountActivity.this, HomePagerActivity.class);
                    startActivity(intent);
                    finish();
                    setExit();
                }

            }
        });
    }

    private void initView() {

        iv_back_include_head_login = (ImageView) findViewById(R.id.iv_back_include_head_login);
        acc_button = (Button) findViewById(R.id.acc_button);
    }

    private void setExit() {
        UMShareAPI.get(AccountActivity.this).deleteOauth(this, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Toast.makeText(AccountActivity.this, "注销成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });

    }
}