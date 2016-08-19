package com.coderstudio.tomliang.fe_webviewtool;

import android.app.Application;

import com.coderstudio.tomliang.fe_webviewtool.dao.DaoMaster;
import com.coderstudio.tomliang.fe_webviewtool.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by lianghangbing on 16/8/2.
 */
public class AppContext extends Application {

    private DaoSession daoSession;
    private static AppContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "tool-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static AppContext getInstance(){
        return instance;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
