package com.nowcoder.toutiao.util;

/**
 * Created by lenovo on 2017/10/6.
 */
public class KeyUtils {
    private static String  SPIT = ":";
    private static String  DIS_LIKE_KEY = "disLike";
    private static String  LIKE_KEY = "Like";
    private static String BIZ_EVENTQUEUE = "EVENT_QUEUE";

    public static String getLikeKey(int entityType,int entityId){
        String s =  LIKE_KEY + SPIT + String.valueOf(entityType) + SPIT + String.valueOf(entityId);
        return s;
    }

    public static String getDisLikeKey(int entityType,int entityId){
        return DIS_LIKE_KEY + SPIT + String.valueOf(entityType) + SPIT + String.valueOf(entityId);
    }

    public static String getBizEventQueue(){
        return BIZ_EVENTQUEUE;
    }
}
