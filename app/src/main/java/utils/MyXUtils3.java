package utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.gyj.jrtt_project.R;
import com.gyj.jrtt_project.adapters.MyRecyclerAdapter;
import com.gyj.jrtt_project.beans.JsonBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by tianjieyu on 2017/4/10.
 */

public class MyXUtils3 {
    public static void httpXUtilsGet(String url, final RecyclerView recycler, final Context context) {
        RequestParams params = new RequestParams(URLUtils.URLS);
        params.addQueryStringParameter("uri", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JsonBean jsonBean = GsonUtils.gsonToBean(result, JsonBean.class);
                ArrayList<JsonBean.ResultBean.DataBean> data = (ArrayList<JsonBean.ResultBean.DataBean>) jsonBean.getResult().getData();
                MyRecyclerAdapter adapter = new MyRecyclerAdapter(data);
                LinearLayoutManager manager = new LinearLayoutManager(context);
                recycler.setLayoutManager(manager);
                recycler.setAdapter(adapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }
        });
    }


    public static void imageXUtils(ImageView imageView, String iconUrl, boolean isCircluar) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCircular(isCircluar)
                .setCrop(true)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .setUseMemCache(true)
                .build();
        x.image().bind(imageView, iconUrl, imageOptions);
    }
}
