package com.biz.dao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**   
* @version 1.0   
* @author TianMengJun
* @since JDK 1.8.0_20
* Create at:   2018年2月5日 下午3:46:25   
* Description:  
*
*@param     
*/

public class DB {
	  
	     //Redis服务器IP  
	     private static final String HOST = "120.79.59.72";  
	      //Redis的端口号  
	     private static final int PORT = 6379;
	     private static final String PASSWORD= "****";
	     
	     //timeout 连接空闲多少秒关闭，默认值是0 代表不关闭
	     private static final int TIMEOUT = 0; 
	     //可用连接实例的最大数目，默认值为8；  
	     //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。  
	     
	     private static final int MAX_ACTIVE = 1024;  
	     //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。  
	     private static final int MAX_IDLE = 200;  
	     //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；  
	     private static final int MAX_WAIT = 10000;  
	           
	     //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
	     private static final  boolean TEST_ON_BORROW = true;  
	     private static JedisPool jedisPool = null;  
	   
	      static {  
	          try {  
	               JedisPoolConfig config = new JedisPoolConfig();  
	               config.setMaxTotal(MAX_ACTIVE);  
	               config.setMaxIdle(MAX_IDLE);  
	               config.setMaxWaitMillis(MAX_WAIT);  
	               config.setTestOnBorrow(TEST_ON_BORROW);  
	               jedisPool = new JedisPool(config, HOST, PORT);
	               jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT, PASSWORD);
	          } catch (Exception e) {  
	              
	          }  
	      }  
	        
	      /** 
	       * 获取Jedis实例 
	       * @return Jedis
	       */  
	      public synchronized static Jedis getJedis() {  
	          try {  
	              if (jedisPool != null) {  
	                  Jedis resource = jedisPool.getResource();  
	                  return resource;  
	              } else {  
	                  return null;  
	              }  
	          } catch (Exception e) {  
	              e.printStackTrace();  
	              return null;  
	          }  
	      }  
	             
	      /** 
	       * 释放jedis资源 
	       * @param jedis 
	       */  
	       public static void closeResource(final Jedis jedis) {  
	           if (jedis != null) {
	        	   jedis.close();
	        	   jedisPool.close();
	           }  
	       }  
	 
}
