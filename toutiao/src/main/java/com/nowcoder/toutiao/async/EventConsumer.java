package com.nowcoder.toutiao.async;


import com.alibaba.fastjson.JSON;
import com.nowcoder.toutiao.util.JedissAdapter;
import com.nowcoder.toutiao.util.KeyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/10/10.
 */
@Service
public class EventConsumer implements InitializingBean, ApplicationContextAware{
    private static final Logger log = LoggerFactory.getLogger(EventConsumer.class);
    private Map<EventType, List<EventHandler>> config = new HashMap<>();  //一个事件要被很多EventHandler处理
    private ApplicationContext applicationContext;

    @Autowired
    JedissAdapter jedissAdapter;
    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);
        if(beans != null){
            for(Map.Entry<String, EventHandler> entry : beans.entrySet()){
                List<EventType> eventTypes = entry.getValue().getSupportEventTypes();  //得到一个

                for(EventType type : eventTypes){
                   if(!config.containsKey(type)){
                       config.put(type, new ArrayList<EventHandler>());
                   }
                   config.get(type).add(entry.getValue());
                }
            }
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    String key = KeyUtils.getBizEventQueue();
                    List<String> events = jedissAdapter.brpop(0, key);
                    for(String message : events){
                        if(message.equals(key)){
                            continue;
                        }

                        EventModel eventModel = JSON.parseObject(message, EventModel.class);
                        if(!config.containsKey(eventModel.getType())){
                            log.error("不能识别的事件");
                            continue;
                        }

                        for(EventHandler handler : config.get(eventModel.getType())){
                            handler.doHandle(eventModel);
                        }
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
