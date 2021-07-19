package com.hzb.myUniversalUtils.net.Utils.persistentcookiejar.cache;

/**
 * FileName: CookieCache
 * Author: houzhengbang
 * Date: 2020/7/25 10:10 AM
 * Description:
 */
import java.util.Collection;

import okhttp3.Cookie;

/**
 * A CookieCache handles the volatile cookie session storage.
 */
public interface CookieCache extends Iterable<Cookie> {

    /**
     * Add all the new cookies to the session, existing cookies will be overwritten.
     *
     * @param cookies
     */
    void addAll(Collection<Cookie> cookies);

    /**
     * Clear all the cookies from the session.
     */
    void clear();
}