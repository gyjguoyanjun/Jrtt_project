package utils;


import com.gyj.jrtt_project.beans.ChildInfo;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

/**
 * data:2017/4/11
 * author:郭彦君(Administrator)
 * function:
 */
public class DbUtils {


    public static void dbAdd(List list, ChildInfo childInfo) throws DbException {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName("myapp.db");
        DbManager db = x.getDb(daoConfig);
        list.add(childInfo);
        db.saveBindingId(list);
    }


}
