package com.gyj.jrtt_project.activitis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyj.jrtt_project.R;

import utils.MyXUtils3;
import utils.Night_styleutils;

public class HomePagerActivity extends AppCompatActivity {
    private int theme = 0;
    private ImageView home_pager_image;
    private TextView home_pager_text;
    private CheckBox home_pager_check;
    private ImageView iv_back_include_head_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pager);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String iconurl = intent.getStringExtra("iconurl");
        String screen_name = intent.getStringExtra("screen_name");
        MyXUtils3.imageXUtils(home_pager_image, iconurl, true);
        home_pager_text.setText(screen_name);
        home_pager_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HomePagerActivity.this, AccountActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        iv_back_include_head_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {

        home_pager_image = (ImageView) findViewById(R.id.home_pager_image);
        home_pager_text = (TextView) findViewById(R.id.home_pager_text);
        home_pager_check = (CheckBox) findViewById(R.id.home_pager_check);
        iv_back_include_head_login = (ImageView) findViewById(R.id.iv_back_include_head_login);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = new Intent(HomePagerActivity.this, MainActivity.class);
        startActivity(intent1);
        finish();
    }
}
