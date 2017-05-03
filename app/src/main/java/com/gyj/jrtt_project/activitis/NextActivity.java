package com.gyj.jrtt_project.activitis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.gyj.jrtt_project.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

/**
 * 类用途:详情页
 * 作者:崔涵淞
 * 时间: 2017/4/20 13:21.
 */

public class NextActivity extends AppCompatActivity implements View.OnClickListener {
    private WebView webView;
    private int theme = 0;
    private ImageView next_share_image;
    private TextView tv_back_include_head_login;
    private ImageView iv_back_include_head_login;
    private String mUrl;
    private String mAuthor_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        initView();
        initData();


    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
        iv_back_include_head_login = (ImageView) findViewById(R.id.iv_back_include_head_login);
        iv_back_include_head_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_back_include_head_login = (TextView) findViewById(R.id.tv_back_include_head_login);
        next_share_image = (ImageView) findViewById(R.id.next_share_image);
        next_share_image.setOnClickListener(this);
    }

    private void initData() {
        //脚本
        webView.getSettings().setJavaScriptEnabled(true);
        Intent intent = getIntent();
        mAuthor_name = intent.getStringExtra("author_name");
        tv_back_include_head_login.setText(mAuthor_name);
        mUrl = intent.getStringExtra("url");
        webView.loadUrl(mUrl);
        //缩放
        webView.getSettings().setBuiltInZoomControls(true);
        //可以访问的文件
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //QQ分享
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);

            Toast.makeText(NextActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(NextActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(NextActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_share_image:
                //UMImage image = new UMImage(NextActivity.this,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492578221535&di=c23c379a4ebd3f99c87c43de86c6c4c5&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F0ff41bd5ad6eddc4fc41a6fb3adbb6fd5266330f.jpg");
                UMWeb umWeb = new UMWeb(mUrl);
                umWeb.setTitle(mAuthor_name);
                umWeb.setDescription(mUrl);
                new ShareAction(NextActivity.this)
                        .withText("hello")
                        .withMedia(umWeb)
                        .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN,
                                SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                                SHARE_MEDIA.QZONE)
                        .setCallback(umShareListener).open();
                //UMImage image = new UMImage(this, "http://" + mUrl);//资源文件
                break;
        }
    }

}