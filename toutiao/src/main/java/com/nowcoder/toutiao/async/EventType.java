package com.nowcoder.toutiao.async;

/**
 * Created by lenovo on 2017/10/10.
 */
public enum EventType {
    LIKE(0),
    COMMETN(1),
    LOGIN(2),
    MAIL(3);

    private int value;
    EventType(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
