package com.gyj.jrtt_project.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.gyj.jrtt_project.R;
import com.gyj.jrtt_project.beans.JsonBean;

import java.util.ArrayList;

import utils.MyXUtils3;


/**
 * @class describe
 * @anthor 郭彦君
 * @time 2017/4/10
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private ArrayList<JsonBean.ResultBean.DataBean> list;

    public MyRecyclerAdapter(ArrayList<JsonBean.ResultBean.DataBean> list) {
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.MyViewHolder holder, int position) {
        holder.textView1.setText(list.get(position).getTitle());
        holder.textView2.setText(list.get(position).getAuthor_name());
        MyXUtils3.imageXUtils(holder.imageView, list.get(position).getThumbnail_pic_s(), false);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
            textView1 = (TextView) itemView.findViewById(R.id.item_text1);
            textView2 = (TextView) itemView.findViewById(R.id.item_text2);

        }
    }
}
