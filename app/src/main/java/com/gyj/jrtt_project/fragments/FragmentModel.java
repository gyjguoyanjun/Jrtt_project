package com.gyj.jrtt_project.fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyj.jrtt_project.R;
import com.gyj.jrtt_project.activitis.MainActivity;
import com.gyj.jrtt_project.activitis.NextActivity;
import com.gyj.jrtt_project.adapters.MyBaseAdapter;
import com.gyj.jrtt_project.beans.JsonBean;
import com.gyj.jrtt_project.sqlLite.MySQLlite;
import com.zaaach.citypicker.CityPickerActivity;

import java.util.List;

import utils.HttpUrlConnectionUtils;
import utils.URLUtils;
import xlistview.bawei.com.xlistviewlibrary.XListView;

import static android.app.Activity.RESULT_OK;


/**
 * @class describe
 * @anthor 郭彦君
 * @time 2017/4/10
 */

public class FragmentModel extends Fragment {
    private View contentView;
    private View parent;
    private PopupWindow popupWindow;
    private String url;
    private RecyclerView recycleView;
    private int theme = R.style.AppTheme;
    private XListView xListView;
    private static final int REQUEST_CODE_PICK_CITY = 233;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json = (String) msg.obj;
            final Gson gson = new Gson();
            JsonBean jsonBean = gson.fromJson(json, JsonBean.class);
            final List<JsonBean.ResultBean.DataBean> data = jsonBean.getResult().getData();
            MyBaseAdapter myBaseAdapter = new MyBaseAdapter(getActivity(), data);
            xListView.setAdapter(myBaseAdapter);
            xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), NextActivity.class);
                    intent.putExtra("author_name", data.get(position - 1).getAuthor_name());
                    intent.putExtra("url", data.get(position - 1).getUrl());
                    getActivity().startActivity(intent);

                }
            });
            parent = View.inflate(getActivity(), R.layout.activity_main, null);


            xListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    int width = view.getWidth();
                    int height = view.getHeight();
                    contentView = View.inflate(getActivity(), R.layout.pop_layout, null);
                    popupWindow = new PopupWindow(contentView, width, height);
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.setFocusable(true);
                    int popupWidth = popupWindow.getWidth();
                    int popupHeight = popupWindow.getHeight();
                    popupWindow.showAsDropDown(view, (width - popupWidth) / 2, 0);
                    contentView.findViewById(R.id.x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });


                    return true;
                }
            });
        }
    };
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragmentslayout, null);
        Bundle bundle = getArguments();
        url = URLUtils.URLS + bundle.getString("url");
        xListView = (XListView) view.findViewById(R.id.xList);
        LinearLayout choose_city_layout = (LinearLayout) view.findViewById(R.id.choose_city_layout);
        if (bundle.getString("url").equals("bd")) {
            choose_city_layout.setVisibility(View.VISIBLE);
        }

        choose_city_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);

            }
        });


        initData();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private void initData() {
        new HttpUrlConnectionUtils(handler, url).start();

    }


    public static FragmentModel newInstance(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        FragmentModel fragmentModel = new FragmentModel();
        fragmentModel.setArguments(bundle);
        return fragmentModel;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                Toast.makeText(getActivity(), city, Toast.LENGTH_SHORT).show();
                MySQLlite helper = new MySQLlite(getActivity());
                SQLiteDatabase database = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name",city);
                database.update("channel",values,"url=?",new String[]{"bd"});
                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();

            }
        }
    }
}
