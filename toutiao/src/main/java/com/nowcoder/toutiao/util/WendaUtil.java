package com.nowcoder.toutiao.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Created by lenovo on 2017/8/31.
 */
public class WendaUtil {
    private static final Logger logger = LoggerFactory.getLogger(WendaUtil.class);

    public static int ANOYMOUS_USERID = 3;

    //添加失败时候使用
    public static String getJSONString(int code, String msg){
        //使用fastJson存储数据，进行转换
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        return jsonObject.toJSONString();
    }

    //添加成功使用
    public static String getJSONString(int code){
        //使用fastJson存储数据，进行转换
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        return jsonObject.toJSONString();
    }
}
