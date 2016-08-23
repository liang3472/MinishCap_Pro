package com.coderstudio.tomliang.minishcap;

import android.app.Application;

import com.coderstudio.tomliang.minishcap.dao.DaoMaster;
import com.coderstudio.tomliang.minishcap.dao.DaoSession;

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
