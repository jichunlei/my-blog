package com.jicl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis键操作工具类
 *
 * @author : xianzilei
 * @date : 2020/1/16 15:52
 */
@Component
public final class RedisKeyUtil {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    /**
     * 功能描述: 删除key
     *
     * @param key 1
     * @return void
     * @author xianzilei
     * @date 2020/1/16 15:59
     **/
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 功能描述: 批量删除key
     *
     * @param keys 1
     * @return void
     * @author xianzilei
     * @date 2020/1/16 15:59
     **/
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 功能描述: 序列化key
     *
     * @param key 1
     * @return byte[]
     * @author xianzilei
     * @date 2020/1/16 16:01
     **/
    public byte[] dump(String key) {
        return redisTemplate.dump(key);
    }

    /**
     * 功能描述: 是否存在key
     *
     * @param key 1
     * @return java.lang.Boolean
     * @author xianzilei
     * @date 2020/1/16 16:01
     **/
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 功能描述: 设置key的过期时间
     *
     * @param key 1
     * @param timeout 2
     * @param unit 3
     * @return java.lang.Boolean
     * @author xianzilei
     * @date 2020/1/16 16:02
     **/
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 功能描述: 设置key的过期时间
     *
     * @param key 1
     * @param date 2
     * @return java.lang.Boolean
     * @author xianzilei
     * @date 2020/1/16 16:02
     **/
    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    /**
     * 功能描述: 查找匹配的key
     *
     * @param pattern 1
     * @return java.util.Set<java.lang.String>
     * @author xianzilei
     * @date 2020/1/16 16:03
     **/
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 功能描述: 将当前数据库的 key 移动到给定的数据库 db 当中
     *
     * @param key 1
     * @param dbIndex 2
     * @return java.lang.Boolean
     * @author xianzilei
     * @date 2020/1/16 16:03
     **/
    public Boolean move(String key, int dbIndex) {
        return redisTemplate.move(key, dbIndex);
    }

    /**
     * 功能描述: 移除 key 的过期时间，key 将持久保持
     *
     * @param key 1
     * @return java.lang.Boolean
     * @author xianzilei
     * @date 2020/1/16 16:03
     **/
    public Boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    /**
     * 功能描述: 返回 key 的剩余的过期时间
     *
     * @param key 1
     * @param unit 2
     * @return java.lang.Long
     * @author xianzilei
     * @date 2020/1/16 16:03
     **/
    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    /**
     * 功能描述: 返回 key 的剩余的过期时间
     *
     * @param key 1
     * @return java.lang.Long
     * @author xianzilei
     * @date 2020/1/16 16:03
     **/
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 功能描述: 从当前数据库中随机返回一个 key
     *
     * @return java.lang.String
     * @author xianzilei
     * @date 2020/1/16 16:04
     **/
    public String randomKey() {
        return redisTemplate.randomKey();
    }

    /**
     * 功能描述: 修改 key 的名称
     *
     * @param oldKey 1
     * @param newKey 2
     * @return void
     * @author xianzilei
     * @date 2020/1/16 16:04
     **/
    public void rename(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * 功能描述: 仅当 newkey 不存在时，将 oldKey 改名为 newkey
     *
     * @param oldKey 1
     * @param newKey 2
     * @return java.lang.Boolean
     * @author xianzilei
     * @date 2020/1/16 16:04
     **/
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 功能描述: 返回 key 所储存的值的类型
     *
     * @param key 1
     * @return org.springframework.data.redis.connection.DataType
     * @author xianzilei
     * @date 2020/1/16 16:04
     **/
    public DataType type(String key) {
        return redisTemplate.type(key);
    }
}
