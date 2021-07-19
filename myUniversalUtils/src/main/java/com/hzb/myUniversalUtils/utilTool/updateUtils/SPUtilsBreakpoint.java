package com.hzb.myUniversalUtils.utilTool.updateUtils;

/**
 * FileName: SPUtils
 * Author: houzhengbang
 * Date: 2020/11/2 4:13 PM
 * Description:
 */

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.hzb.myUniversalUtils.baseApplication.BaseApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/08/02
 *     desc  : utils about shared preference
 * </pre>
 */
public class SPUtilsBreakpoint {

    public static Map<String, SPUtilsBreakpoint> SP_UTILS_MAP = new HashMap<>();

    private SharedPreferences sp;

    /**
     * Return the single {@link SPUtilsBreakpoint} instance
     *
     * @param spName The name of sp.
     * @return the single {@link SPUtilsBreakpoint} instance
     */
    public static SPUtilsBreakpoint getInstance(String spName) {
        return getInstance(spName, Context.MODE_PRIVATE);
    }

    /**
     * Return the single {@link SPUtilsBreakpoint} instance
     *
     * @param spName The name of sp.
     * @param mode   Operating mode.
     * @return the single {@link SPUtilsBreakpoint} instance
     */
    public static SPUtilsBreakpoint getInstance(String spName, final int mode) {
        if (isSpace(spName)) spName = "spUtils";
        SPUtilsBreakpoint spUtils = SP_UTILS_MAP.get(spName);
        if (spUtils == null) {
            synchronized (SPUtilsBreakpoint.class) {
                spUtils = SP_UTILS_MAP.get(spName);
                if (spUtils == null) {
                    spUtils = new SPUtilsBreakpoint(spName, mode);
                    SP_UTILS_MAP.put(spName, spUtils);
                }
            }
        }
        return spUtils;
    }

    private SPUtilsBreakpoint(final String spName, final int mode) {
        sp = BaseApplication.getInstance().getSharedPreferences(spName, mode);
    }


    /**
     * Put the long value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    public void put(@NonNull final String key, final long value) {
        put(key, value, false);
    }

    /**
     * Put the long value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     */
    public void put(@NonNull final String key, final long value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putLong(key, value).commit();
        } else {
            sp.edit().putLong(key, value).apply();
        }
    }

    /**
     * Return the long value in sp.
     *
     * @param key The key of sp.
     * @return the long value if sp exists or {@code -1} otherwise
     */
    public long getLong(@NonNull final String key) {
        return getLong(key, -1L);
    }

    /**
     * Return the long value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the long value if sp exists or {@code defaultValue} otherwise
     */
    public long getLong(@NonNull final String key, final long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    /**
     * Remove all preferences in sp.
     */
    public void clear() {
        clear(false);
    }

    /**
     * Remove all preferences in sp.
     *
     * @param isCommit True to use {@link SharedPreferences.Editor#commit()},
     *                 false to use {@link SharedPreferences.Editor#apply()}
     */
    public void clear(final boolean isCommit) {
        if (isCommit) {
            sp.edit().clear().commit();
        } else {
            sp.edit().clear().apply();
        }
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}