/*
 * Copyright (c) 2017-2018,Cardinal Operations and/or its affiliates. All rights reserved.
 * CARDINAL OPERATIONS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.cs.demo.config;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程及线程池（Task）共享数据信息
 */
public class ThreadLocalFactory {

    protected static ConcurrentHashMap<Class<?>, ThreadLocal<Object>> threadLocalMap = new ConcurrentHashMap<>();

    public static synchronized ThreadLocal<Object> getThreadLocal(Class<?> tClass) {
        ThreadLocal<Object> threadLocal = threadLocalMap.get(tClass);
        if (threadLocal == null) {
            threadLocal = new ThreadLocal<>();
            threadLocalMap.put(tClass, threadLocal);
        }
        return threadLocal;
    }

    public static <T> T getThreadLocalData(Class<T> tClass) {
        return (T) getThreadLocal(tClass).get();
    }
}
