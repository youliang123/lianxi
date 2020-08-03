package com.fh.common;

public class SystemPlate {

    public final static int REDIS_SHIXIAO_UNTIME =60*3;
    public final static int COOKIE_EXPIRY_TIME =7*24*60*60;
    public final static String SESSION_KEY ="sessionkey:";
    public final static String SHOPPING_KEY ="shoppingkey:";
    public final static String TOKEN_KEY ="tokenkey:";
    public final static int TOKEN_EXPIRE_TIME = 15 * 60;
    public final static int ORDER_STATUS_WAIT = 0;
    public final static int ORDER_STATUS_FINISH = 1;
}
