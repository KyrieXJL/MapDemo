package com.lenovo.smarttraffic;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Amoly
 * @date 2019/4/11.
 * description：
 */
public class InitApp extends MultiDexApplication {

    private static Handler mainHandler;
    private static Context AppContext;
    private Set<Activity> allActivities;

    private static InitApp instance = null; //此处提示内存泄漏

    public static synchronized InitApp getInstance() {
        synchronized (InitApp.class) {
            if (instance == null) {
                instance = new InitApp();
            }
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        AppContext = this;
        instance = this;
        mainHandler = new Handler();

    }

    public static Context getContext(){
        return AppContext;
    }
    public static Handler getHandler(){
        return mainHandler;
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}
