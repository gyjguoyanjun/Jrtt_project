package utils;

import java.util.ArrayList;

/**
 * Created by tianjieyu on 2017/4/10.
 */

public class URLUtils {
    public static final String URLS = "http://result.eolinker.com/X39jyd7dd8d690677289995f22d9d77fafd75839e51385e?uri=";
    public static final String TITLEURLS = "http://result.eolinker.com/X39jyd7dd8d690677289995f22d9d77fafd75839e51385e?uri=title";
    public static final String LOGIN_URL = "http://192.168.23.226/mobile/index.php?act=login&op=register&";

    public static ArrayList<String> getStringURL() {
        ArrayList<String> list = new ArrayList<>();
        list.add("tt");
        list.add("gj");
        list.add("gn");
        list.add("ss");
        list.add("cj");
        list.add("kj");
        list.add("js");
        list.add("ty");
        list.add("yl");
        list.add("shehui");
        return list;
    }
}
