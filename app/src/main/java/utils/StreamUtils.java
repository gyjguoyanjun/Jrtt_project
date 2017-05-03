package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/3/15.
 */

/**
* @class json串转成字符串的工具类
* @anthor 郭彦君
* @time 2017/3/15
*/
public class StreamUtils {

    public static String jsonToString(InputStream inputStream) {

        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                arrayOutputStream.write(buffer, 0, len);
            }
            return arrayOutputStream.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(arrayOutputStream!=null){
                try {
                    arrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}
