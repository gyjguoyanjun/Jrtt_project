package utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/12  19:10
 */

public class HttpUtils {
    private volatile static HttpUtils instance;

    /**
     * 单利模式
     *
     * @return
     */
    public static HttpUtils getInstance() {
        if (instance == null) {
            synchronized (HttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }




    public void httpXUtilsPOST(String username, String password, String password_confirm, String emai, final MyHttpCallback callback) {

        RequestParams params = new RequestParams(URLUtils.LOGIN_URL + "username" + username + "&password" + password + "&password_confirm" + password_confirm + "&client=android&emai" + emai);
        params.addQueryStringParameter("username", username);
        params.addQueryStringParameter("password", password);
        params.addQueryStringParameter("password_confirm", password_confirm);
        params.addQueryStringParameter("emai", emai);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onError(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                callback.onFinished();
            }
        });

    }

    /**
     * 接口回调
     */
    public interface MyHttpCallback {
        void onSuccess(String result);

        void onError(String errorMsg);

        void onFinished();
    }

}
