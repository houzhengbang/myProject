package com.hzb.myUniversalUtils.utilTool;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.Map;


public class SharedPreferenceUtils {

    private static final String FILE_SHARE = "auto_share"; //共享



    private static final String FILE_USER = "user";
    private static final String KEY_AUTO_IS_LOGIN = "islogin";
    private static final String KEY_AUTO_IS_FIRST = "isFirst";


    private SharedPreferenceUtils() {
    }

    public static void setFirstOpen(Context context, boolean isFirst) {
        putSync(context, FILE_USER, KEY_AUTO_IS_FIRST, isFirst);
    }

    public static boolean getFirstOpen(Context context) {
        return (boolean) get(context, FILE_USER, KEY_AUTO_IS_FIRST, true);
    }

    public static void setIsLogin(Context context, boolean isLogin) {
        putSync(context, FILE_USER, KEY_AUTO_IS_LOGIN, isLogin);
    }

    public static boolean getIsLogin(Context context) {
        return (boolean) get(context, FILE_USER, KEY_AUTO_IS_LOGIN, false);
    }

    // # record end


    /**
     * 异步 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param fileName
     * @param key
     * @param object
     */
    public static void put(Context context, String fileName, String key, Object object) {
        put(context, false, fileName, key, object);
    }

    /**
     * 异步 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    public static void put(Context context, String key, Object object) {
        put(context, false, FILE_SHARE, key, object);
    }

    /**
     * 同步 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    public static void putSync(Context context, String key, Object object) {
        put(context, true, FILE_SHARE, key, object);
    }

    /**
     * 同步 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    public static void putSync(Context context, String fileName, String key, Object object) {
        put(context, true, fileName, key, object);
    }

    /**
     * 可控制 同步 或者 异步保存
     *
     * @param context
     * @param isSyn    是否同步操作 （保存操作 有同步 和 异步）
     * @param fileName
     * @param key
     * @param object
     */
    public static void put(Context context, boolean isSyn, String fileName, String key, Object object) {
        if (object == null) {
            return;
        }

        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        if (isSyn) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(Context context, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(FILE_SHARE, Context.MODE_MULTI_PROCESS);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(Context context, String fileName, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_MULTI_PROCESS);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_SHARE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param fileName
     * @param key
     */
    public static void remove(Context context, String fileName, String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_SHARE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 清除指定文件数据数据
     *
     * @param context
     */
    public static void clear(Context context, String fileName) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_SHARE, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 查询某个key在某个文件里是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String file_name, String key) {
        SharedPreferences sp = context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_SHARE, Context.MODE_PRIVATE);
        return sp.getAll();
    }

}
