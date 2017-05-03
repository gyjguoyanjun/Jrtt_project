package com.gyj.jrtt_project.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.gyj.jrtt_project.R;
import com.gyj.jrtt_project.activitis.LoginActivity;
import com.gyj.jrtt_project.activitis.MainActivity;

import utils.UiUtils;

/**
 * data:2017/4/13
 * author:郭彦君(Administrator)
 * function:
 */
public class MenuLeftFragment extends Fragment {
    private View view;
    private CheckBox menu_more;
    private RadioButton night_style;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.slidingmenu, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        menu_more = (CheckBox) view.findViewById(R.id.menu_more);
        night_style = (RadioButton) view.findViewById(R.id.night_style);
        menu_more.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Toast.makeText(getActivity(), "sss", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        night_style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.switchAppTheme(getActivity());
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.reload();
            }
        });

    }


}
