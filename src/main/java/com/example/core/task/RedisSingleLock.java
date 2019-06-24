package com.example.core.task;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * @Author wangwei
 * @Date 2019/4/2 10:31
 * -描述- 单机redis实现分布式锁的正确姿势
 */
public class RedisSingleLock {
    /** 实现分布式锁的三种方案
     *      1.数据库乐观锁
     *      2.redis分布式锁
     *      3.zookeeper分布式锁
     * 确保分布式锁的可靠性，那么锁的实现要同时满足下面四个条件:
     *      1.互斥行，任意时刻只有一个客户端能持有锁
     *      2.不会发生死锁，即时客户端在持有锁的期间崩溃而没有主动释放锁，也能保证后序客户端能加锁
     *      3.容错性，大部分的redis节点正常运行，客户端就可以加锁和解锁
     *      4.解铃还须系铃人，加锁和解锁必须是同一个客户端,一个客户端不能解锁其他客户端加的锁
     * @param jedis - Redis客户端
     * @param lockKey - 锁
     * @param lockValue - 唯一标识，锁(key)所对应的value
     * @param expireTime - 过期时间
     * @return -- 获取锁是否成功
     */
    public static boolean tryGetDistrubtedLock(Jedis jedis, String lockKey, String lockValue, Integer expireTime){
        /*
            5个参数详解 --
            lockKey -- 这个是锁，因为key唯一，所以用key来充当锁
            lockValue -- 唯一的，用来客户端解锁的时候充当依据
            nxxx -- SET_IF_NOT_EXITS -- key存在不做任何操作，key不存在进行set操作
            expx -- SET_WITH_EXPIRE_TIME -- 给该key加一个过期时间
            time -- 过期时间，与expx相呼应
         */
        String result = jedis.set(lockKey,lockValue,SetParams.setParams().nx().ex(expireTime));
        if("OK".equals(result)){
            return true;
        }
        return false;
    }

    /**
     *  解锁代码 - 正确姿势
     *  Lua -- 脚本，交给Redis服务端去执行
     *  在eval命令执行Lua代码的时候，Lua代码将被当成一个命令去执行，并且直到eval命令执行完成，Redis才会执行其他命令
     *
     */
    private static final Long success = 1L;
    private static boolean releaseDistrubtedLock(Jedis jedis,String lockKey,String lockValue){
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey),Collections.singletonList(lockValue));
        if(success.equals(result)){
            return true;
        }
        return false;
    }

    /**
     * 解锁 -- 错误姿势
     */
    public static void wrongReleaseLock2(Jedis jedis, String lockKey, String lockValue) {
        // 判断加锁与解锁是不是同一个客户端
        if (lockValue.equals(jedis.get(lockKey))) {
            // 若在此时，这把锁突然不是这个客户端的，则会误解锁，这种情况是非常有可能的
            jedis.del(lockKey);
        }
    }
}
