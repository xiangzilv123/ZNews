package com.zzz.news.util;


import com.orhanobut.logger.Logger;
import com.zzz.news.BuildConfig;

/**
 * Created by codeest on 2016/8/3.
 */
public class ZLog {

    public static boolean isDebug = BuildConfig.DEBUG;
    private static final String TAG = "com.zzz.news";

    public static void e(String tag,Object o) {
        if(isDebug) {
            Logger.e(tag, o);
        }
    }

    public static void e(Object o) {
        ZLog.e(TAG,o);
    }

    public static void w(String tag,Object o) {
        if(isDebug) {
            Logger.w(tag, o);
        }
    }

    public static void w(Object o) {
        ZLog.w(TAG,o);
    }

    public static void d(String msg) {
        if(isDebug) {
            Logger.d(msg);
        }
    }

    public static void i(String msg) {
        if(isDebug) {
            Logger.i(msg);
        }
    }

    public static void j(String json) {
        if (isDebug) {
            Logger.json(json);
        }
    }
}
