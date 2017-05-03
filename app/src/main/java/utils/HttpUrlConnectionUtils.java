package utils;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpUrlConnectionUtils extends Thread {

    private Handler handler;
    private String sUrl;
    private String s;

    public HttpUrlConnectionUtils(Handler handler, String sUrl) {
        this.handler = handler;
        this.sUrl = sUrl;
    }

    private String httpGet(String sUrl) {

        HttpURLConnection connection = null;
        try {
            URL url = new URL(sUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            String json = StreamUtils.jsonToString(inputStream);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return null;
    }

    @Override
    public void run() {
        super.run();
        try {
            s = httpGet(sUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Message message = Message.obtain();
        message.what = 1;
        message.obj = s;
        handler.sendMessage(message);

    }
}