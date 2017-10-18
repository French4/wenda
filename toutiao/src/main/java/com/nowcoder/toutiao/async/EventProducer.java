package com.nowcoder.toutiao.async;

import com.alibaba.fastjson.JSONObject;
import com.nowcoder.toutiao.util.JedissAdapter;
import com.nowcoder.toutiao.util.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2017/10/10.
 * 这是生产者，传入的参数是eventModel
 */
@Service
public class EventProducer {
    @Autowired
    JedissAdapter jedissAdapter;

    public boolean fireEvent(EventModel eventModel){
        try{
            String json = JSONObject.toJSONString(eventModel);
            String key = KeyUtils.getBizEventQueue();
            jedissAdapter.lpush(key, json);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
