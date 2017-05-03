package com.gyj.jrtt_project.activitis;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyj.jrtt_project.R;
import com.gyj.jrtt_project.adapters.MyIndicatorAdapter;
import com.gyj.jrtt_project.adapters.MyViewPagerAdapter;
import com.gyj.jrtt_project.beans.TitleBean;
import com.gyj.jrtt_project.fragments.FragmentModel;
import com.gyj.jrtt_project.sqlLite.MySQLlite;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utils.HttpUrlConnectionUtils;
import utils.MyXUtils3;
import utils.Night_styleutils;
import utils.URLUtils;
import utils.UiUtils;


public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private int theme = 0;
    private boolean flag = true;
    private long firstTime = 0;
    private LinearLayout main_tt_layout;
    private SharedPreferences.Editor edit;
    private ImageView main_login_image;
    private MagicIndicator magic_indicator;
    private ViewPager viewPager;
    private CommonNavigator mCommonNavigator;
    private ImageView image_more;
    private List<String> mUserList = new ArrayList<>();
    private List<String> mOtherList = new ArrayList<>();
    private boolean b = true;
    private ArrayList<String> sList;
    private SlidingMenu slidingMenu;
    private SQLiteDatabase db;
    private String screen_name;
    private String iconurl;
    private LinearLayout tx_1;
    private LinearLayout tx_2;
    private ImageView tx2_image;
    private TextView tx2_text;
    private CheckBox menu_more;
    private RadioButton setting_button;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json = (String) msg.obj;
            Gson gson = new Gson();
            TitleBean titleBean = gson.fromJson(json, TitleBean.class);
            List<TitleBean.ResultBean.DateBean> date = titleBean.getResult().getDate();
            ArrayList<String> titles = new ArrayList<>();
            MySQLlite mySQLlite = new MySQLlite(MainActivity.this);
            db = mySQLlite.getReadableDatabase();

            boolean flag = preferences.getBoolean("flag", MainActivity.this.flag);
            if (flag) {
                for (int i = 0; i < date.size(); i++) {
                    ContentValues values = new ContentValues();
                    if (i % 2 == 0) {
                        values.put("name", date.get(i).getTitle());
                        values.put("url", date.get(i).getUri());
                        values.put("style", "1");
                        db.insert("channel", null, values);
                    }
                    if (i % 2 == 1) {
                        values.put("name", date.get(i).getTitle());
                        values.put("url", date.get(i).getUri());
                        values.put("style", "0");
                        db.insert("channel", null, values);
                    }

                    titles.add(date.get(i).getTitle());
                }
                edit.putBoolean("flag", false);
                edit.commit();
            }

            sList = new ArrayList<>();
            ArrayList<String> strings = new ArrayList<>();
            Cursor cursor = db.query("channel", new String[]{"name", "url"}, "style=?", new String[]{"1"}, null, null, null);
            while (cursor.moveToNext()) {
                String string = cursor.getString(cursor.getColumnIndex("name"));
                //String url = cursor.getString(1);
                String url = cursor.getString(cursor.getColumnIndex("url"));
                strings.add(url);
                sList.add(string);
            }
            MyIndicatorAdapter adapter = new MyIndicatorAdapter(sList, viewPager);
            mCommonNavigator.setAdapter(adapter);
            magic_indicator.setNavigator(mCommonNavigator);
            ViewPagerHelper.bind(magic_indicator, viewPager);

            ArrayList<Fragment> list = new ArrayList<>();
            for (int i = 0; i < strings.size(); i++) {
                FragmentModel fragmentModel = FragmentModel.newInstance(strings.get(i));
                list.add(fragmentModel);
            }
            viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), list));
        }
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        //切换主题必须放在onCreate()之前
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        ceHua();
        preferences = getSharedPreferences("style_flag", MODE_PRIVATE);
        edit = preferences.edit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean lofin_flag = preferences.getBoolean("login_flag", false);
        if (lofin_flag) {
            final String iconurl = preferences.getString("iconurl", "");
            Log.d("sssssssssssssss", iconurl);
            final String screen_name = preferences.getString("screen_name", "");
            //MyXUtils3.imageXUtils(main_login_image, iconurl, true);
            MyXUtils3.imageXUtils(tx2_image, iconurl, true);
            tx2_text.setText(screen_name);
            menu_more.setVisibility(View.VISIBLE);
            tx_1.setVisibility(View.VISIBLE);
            tx_2.setVisibility(View.GONE);
            tx_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, HomePagerActivity.class);
                    intent.putExtra("iconurl", iconurl);
                    intent.putExtra("screen_name", screen_name);
                    startActivity(intent);
                }
            });
        }
    }

    public void initData() {
        new HttpUrlConnectionUtils(handler, URLUtils.TITLEURLS).start();


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    slidingMenu.setTouchModeAbove(
                            SlidingMenu.TOUCHMODE_FULLSCREEN);
                } else {
                    // 当在其他位置的时候，设置不可以拖拽出来(SlidingMenu.TOUCHMODE_NONE)，或只有在边缘位置才可以拖拽出来TOUCHMODE_MARGIN
                    slidingMenu.setTouchModeAbove(
                            SlidingMenu.TOUCHMODE_NONE);
                }
                if (position == sList.size()) {

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //magic_indicator.setBackgroundColor(Color.parseColor("#ffffff"));
        if (theme == 1) {
            magic_indicator.setBackgroundColor(Color.parseColor("#ffffff"));
            main_tt_layout.setSelected(true);

        }
        if (theme == 2) {
            magic_indicator.setBackgroundColor(Color.parseColor("#000000"));
            // main_tt_layout.setBackgroundColor(Color.parseColor("#000000"));
            main_tt_layout.setSelected(true);
        }
        mCommonNavigator = new CommonNavigator(this);
        mCommonNavigator.setSkimOver(true);


    }

    public void initView() {
        magic_indicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        image_more = (ImageView) findViewById(R.id.image_more);

        main_tt_layout = (LinearLayout) findViewById(R.id.main_tt_layout);

        image_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChannelActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void ceHua() {
        // 设置左侧滑动菜单
        int pixels = this.getResources().getDisplayMetrics().widthPixels;
        slidingMenu = new SlidingMenu(this);
        //从左边滑出
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置住屏幕滑出的宽度
        slidingMenu.setBehindOffset(pixels / 3);
        slidingMenu.attachToActivity(MainActivity.this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.slidingmenu);
        main_login_image = (ImageView) findViewById(R.id.main_login_image);
        tx_1 = (LinearLayout) slidingMenu.findViewById(R.id.tx_1);
        tx_2 = (LinearLayout) slidingMenu.findViewById(R.id.tx_2);
        tx2_image = (ImageView) slidingMenu.findViewById(R.id.tx2_image);
        tx2_text = (TextView) slidingMenu.findViewById(R.id.tx2_text);
        menu_more = (CheckBox) slidingMenu.findViewById(R.id.menu_more);
        setting_button = (RadioButton) slidingMenu.findViewById(R.id.setting_button);

        RadioButton night_style = (RadioButton) slidingMenu.findViewById(R.id.night_style);
        RadioButton Off_line_download_bt = (RadioButton) slidingMenu.findViewById(R.id.Off_line_download_bt);


        night_style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.switchAppTheme(MainActivity.this);
                reload();
            }
        });

        main_login_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingMenu.toggle();
            }
        });
        ImageView menu_qq = (ImageView) slidingMenu.findViewById(R.id.menu_qq);
        menu_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener);
            }
        });
        menu_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        Off_line_download_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Off_line_download.class);
                startActivity(intent);
            }
        });

//设置
        setting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });


    }


    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);//进入动画
        finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        startActivity(intent);
    }

    UMAuthListener authListener = new UMAuthListener() {

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            screen_name = data.get("screen_name");
            iconurl = data.get("iconurl");
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
            Glide.with(MainActivity.this).load(data.get("iconurl")).error(R.mipmap.ic_launcher).into(main_login_image);
            Glide.with(MainActivity.this).load(data.get("iconurl")).error(R.mipmap.ic_launcher).into(tx2_image);
            tx2_text.setText(screen_name);
            menu_more.setVisibility(View.GONE);
            tx_1.setVisibility(View.GONE);
            tx_2.setVisibility(View.VISIBLE);
            tx_2.setFocusable(true);
            edit.putBoolean("login_flag", true);
            edit.putString("screen_name", screen_name);
            edit.putString("iconurl", iconurl);
            edit.commit();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

}
