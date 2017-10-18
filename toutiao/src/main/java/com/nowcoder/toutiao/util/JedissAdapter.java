package com.nowcoder.toutiao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;

import java.util.List;

/**
 * Created by lenovo on 2017/10/6.
 */
@Service
public class JedissAdapter  implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(JedissAdapter.class);
    private JedisPool pool;

    @Override
    public void afterPropertiesSet() throws Exception {
        try{
            pool = new JedisPool("redis://localhost:6379/10");
        }catch (Exception e){
            logger.error("连接失败"+e.getMessage());
        }

    }

    /**
     * sadd key value方法
     */
    public void sadd(String key, String value){
        Jedis jedis = pool.getResource();
        try {
            jedis = pool.getResource();
            jedis.sadd(key,value);
        }catch (Exception e){
            logger.error("redis添加失败"+e.getMessage());
        }finally {
            if(jedis != null)
            jedis.close();
        }
    }

    /**
     * srem key value
     */
    public void srem(String key, String value){
        Jedis jedis = pool.getResource();
        try {
            jedis = pool.getResource();
            jedis.srem(key,value);
        }catch (Exception e){
            logger.error("redis删除发生异常"+e.getMessage());
        }finally {
            if(jedis != null)
            jedis.close();
        }
    }

    /**
     * scard key
     */
    public long scard(String key){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.scard(key);
        }catch (Exception e){
            logger.error("redis统计发生异常"+e.getMessage());
        }finally {
            if(jedis != null)
            jedis.close();
        }
        return 0;
    }

    /**
     *sismember key value
     */
    public boolean sismember(String key, String value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.sismember(key, value);
        }catch (Exception e){
            logger.error("redis判断是否存在发生异常"+e.getMessage());
        }finally {
            if(jedis != null)
                jedis.close();
        }
        return false;
    }

    public void lpush(String key, String json) {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.lpush(key, json);
        }catch (Exception e){
            logger.error("lpush失败"+e.getMessage());
        }
    }

    public List<String> brpop(int i, String key) {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return  jedis.brpop(0,key);
        }catch (Exception e){
            logger.error("lpush失败"+e.getMessage());
        }
        return null;
    }
}
