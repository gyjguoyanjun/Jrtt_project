package com.gyj.jrtt_project.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


/**
 * @class describe
 * @anthor 郭彦君
 * @time 2017/4/10
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> s;
    private ArrayList<Fragment> list;

    public MyViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
