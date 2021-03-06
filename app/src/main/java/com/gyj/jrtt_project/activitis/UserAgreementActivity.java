package com.gyj.jrtt_project.activitis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.gyj.jrtt_project.R;

import utils.Night_styleutils;


public class UserAgreementActivity extends AppCompatActivity {

    private WebView web_agreement_phone_register;
    private RelativeLayout activity_user_agreement;
    private int theme = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agreement);
        initView();
        initData();
    }

    private void initData() {
        WebSettings settings = web_agreement_phone_register.getSettings();
        settings.setJavaScriptEnabled(true);
        web_agreement_phone_register.loadUrl("http://mp.toutiao.com/agreement/");
    }

    private void initView() {
        web_agreement_phone_register = (WebView) findViewById(R.id.web_agreement_phone_register);
        activity_user_agreement = (RelativeLayout) findViewById(R.id.activity_user_agreement);
    }
}
