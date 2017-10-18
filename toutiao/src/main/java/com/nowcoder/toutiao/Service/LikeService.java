package com.nowcoder.toutiao.Service;

import com.nowcoder.toutiao.util.JedissAdapter;
import com.nowcoder.toutiao.util.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2017/10/6.
 */
@Service
public class LikeService {
    @Autowired
    JedissAdapter jedissAdapter;

    /**
     * 添加喜欢步骤：
     * 1.将问题的id和Like结合，作为key使用
     * 2.调用sadd key value方法，添加一个喜欢的id
     * 3.得到不喜欢的key， 删除一个key对应的id
     * 4.调用scard方法，得到一个总数
     * @param entityType
     * @param entityId
     * @param userId
     */
    public  long like(int entityType, int entityId, int userId){
        String key = KeyUtils.getLikeKey(entityType,entityId);
        jedissAdapter.sadd(key, String.valueOf(userId));

        String disLikeKey = KeyUtils.getDisLikeKey(entityId, entityType);
        jedissAdapter.srem(disLikeKey,String.valueOf(userId));

        return jedissAdapter.scard(key);
    }

    /**
     * 不喜欢步骤：和喜欢的一样
     */
    public  long dislike(int entityType, int entityId, int userId){
        String dislikeKey = KeyUtils.getLikeKey(entityType,entityId);
        jedissAdapter.sadd(dislikeKey, String.valueOf(userId));

        String likeKey = KeyUtils.getDisLikeKey(entityType,entityId);
        jedissAdapter.srem(likeKey,String.valueOf(userId));

        return jedissAdapter.scard(likeKey);
    }

    public long getLikeCount(int entityType,int entityId){
        String likeKey = KeyUtils.getLikeKey(entityType, entityId);
        return  jedissAdapter.scard(likeKey);
    }

    /**
     * 得到status，0表示不关注，1表示赞同， -1表示不赞同
     */
    public int getStatus(int entityType, int entityId, int userId){
        String likeKey = KeyUtils.getLikeKey(entityType,entityId);
        if(jedissAdapter.sismember(likeKey, String.valueOf(userId))){
            return 1;
        }

        String disLikeKey = KeyUtils.getDisLikeKey(entityType,entityId);
        return  jedissAdapter.sismember(disLikeKey, String.valueOf(userId)) ? -1 : 0;
    }
}
