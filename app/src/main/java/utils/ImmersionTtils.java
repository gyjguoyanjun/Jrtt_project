package utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

/**
 * Created by Administrator on 2017/3/15.
 */

/**
 * @class 沉浸式工具类
 * @anthor 郭彦君
 * @time 2017/3/15
 */

public class ImmersionTtils {

    public static void immersion(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            int i = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(i);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }

}
