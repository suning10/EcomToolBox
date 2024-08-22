package com.ecom.common.context;

public class BaseContext {

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(int id) {
        threadLocal.set(id);
    }

    public static int getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }
}
