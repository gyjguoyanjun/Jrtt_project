package com.gyj.jrtt_project.application;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;
import cn.smssdk.SMSSDK;


/**
 * @class describe
 * @anthor 郭彦君
 * @time 2017/4/10
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);


        x.Ext.init(this);
        x.Ext.setDebug(false);
        UMShareAPI.get(this);
        Config.DEBUG = true;
        SMSSDK.initSDK(this, "1cf72a14a34ed", "6891b8254a2fc86bb0becbe07ff72987");


    }
    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
