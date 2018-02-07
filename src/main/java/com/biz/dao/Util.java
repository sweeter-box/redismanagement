package com.biz.dao;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**   
* @version 1.0   
* @author TianMengJun
* @since JDK 1.8.0_20
* Create at:   2018年2月5日 下午6:02:36   
* Description:  
*
*@param     
*/

public class Util {
	/** 
     * 通过key删除（字节） 
     * @param key 
     */  
    public void del(byte [] key){  
        Jedis jedis = DB.getJedis();  
        jedis.del(key);  
    }  
    /** 
     * 通过key删除 
     * @param key 
     */  
    public void del(String key){  
        Jedis jedis = DB.getJedis();  
        jedis.del(key);  
    }  
  
    /** 
     * 添加key value 并且设置存活时间(byte) 
     * @param key 
     * @param value 
     * @param liveTime 
     */  
    public void set(byte [] key,byte [] value,int liveTime){  
        Jedis jedis = DB.getJedis();  
        jedis.set(key, value);  
        jedis.expire(key, liveTime);  
    }  
    /** 
     * 添加key value 并且设置存活时间 
     * @param key 
     * @param value 
     * @param liveTime 
     */  
    public void set(String key,String value,int liveTime){  
        Jedis jedis = DB.getJedis();  
        jedis.set(key, value);  
        jedis.expire(key, liveTime);  
    }  
    /** 
     * 添加key value 
     * @param key 
     * @param value 
     */  
    public void set(String key,String value){  
        Jedis jedis = DB.getJedis();  
        jedis.set(key, value);  
    }  
    /**添加key value (字节)(序列化) 
     * @param key 
     * @param value 
     */  
    public void set(byte [] key,byte [] value){  
        Jedis jedis = DB.getJedis();  
        jedis.set(key, value);  
    }  
    /** 
     * 获取redis value (String) 
     * @param key 
     * @return 
     */  
    public String get(String key){  
        Jedis jedis = DB.getJedis();  
         String value = jedis.get(key);  
        return value;  
    }  
    /** 
     * 获取redis value (byte [] )(反序列化) 
     * @param key 
     * @return 
     */  
    public byte[] get(byte [] key){  
        Jedis jedis = DB.getJedis();  
        byte[] value = jedis.get(key);  
        return value;  
    }  
  
    /** 
     * 通过正则匹配keys 
     * @param pattern 
     * @return 
     */  
    public Set<String> keys(String pattern){  
        Jedis jedis = DB.getJedis();  
        Set<String> value = jedis.keys(pattern);  
        return value;  
    }  
  
    /** 
     * 检查key是否已经存在 
     * @param key 
     * @return 
     */  
    public boolean exists(String key){  
        Jedis jedis = DB.getJedis();  
        boolean value = jedis.exists(key);  
        return value;  
    }  
      
    /*******************redis list操作************************/  
    /** 
     * 往list中添加元素 
     * @param key 
     * @param value 
     */  
    public void lpush(String key,String value){  
        Jedis jedis = DB.getJedis();  
        jedis.lpush(key, value);  
    }  
      
    public void rpush(String key,String value){  
        Jedis jedis = DB.getJedis();  
        jedis.rpush(key, value);  
    }  
      
    /** 
     * 数组长度 
     * @param key 
     * @return 
     */  
    public Long llen(String key){  
        Jedis jedis = DB.getJedis();  
        Long len = jedis.llen(key);  
        return len;  
    }  
      
    /** 
     * 获取下标为index的value 
     * @param key 
     * @param index 
     * @return 
     */  
    public String lindex(String key,Long index){  
        Jedis jedis = DB.getJedis();  
        String str = jedis.lindex(key, index);  
        return str;  
    }  
      
    public String lpop(String key){  
        Jedis jedis = DB.getJedis();  
        String str = jedis.lpop(key);  
        return str;  
    }  
      
    public List<String> lrange(String key,long start,long end){  
        Jedis jedis = DB.getJedis();  
        List<String> str = jedis.lrange(key, start, end);  
        return str;  
    }
    
}
