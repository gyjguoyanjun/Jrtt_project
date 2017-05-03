package com.gyj.jrtt_project.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyj.jrtt_project.R;
import com.gyj.jrtt_project.beans.JsonBean;

import java.util.List;

import utils.MyXUtils3;

/**
 * data:2017/4/19
 * author:郭彦君(Administrator)
 * function:
 */
public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<JsonBean.ResultBean.DataBean> list;

    public MyBaseAdapter(Context context, List<JsonBean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_layout, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_image);
            holder.textView1 = (TextView) convertView.findViewById(R.id.item_text1);
            holder.textView2 = (TextView) convertView.findViewById(R.id.item_text2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MyXUtils3.imageXUtils(holder.imageView, list.get(position).getThumbnail_pic_s(), false);
        holder.textView1.setText(list.get(position).getTitle());
        holder.textView2.setText(list.get(position).getAuthor_name());

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
    }
}
