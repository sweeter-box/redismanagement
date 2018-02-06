package com.biz.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * @version 1.0
 * @author TianMengJun
 * @since JDK 1.8.0_20 Create at: 2018年2月5日 下午3:42:01 Description:
 *
 * @param
 */

public class Dao {

	/*
	 * 将Id存在sortedset
	 */
	static Jedis JEDIS = DB.getJedis();
	static final String STUDENT_ID_TABLE="studentid";
	/**
	 * 批量删除key-value
	 * @param id
	 * @return
	 */
	public boolean del(String id) {
		if (JEDIS.zrem(STUDENT_ID_TABLE, id) == JEDIS.del(id))
			return true;
		return false;
	}

	
	/**
	 * 插入一条数据
	 * @param id
	 * @param infoMap
	 * @return
	 */
	public boolean insertInfo(String id, Map<String, String> infoMap) {
		int score = Integer.parseInt(infoMap.get("avgscore"));
		// 设置事务必须同时成功或失败
		if (JEDIS.hmset(id, infoMap).equals("OK")&&JEDIS.zadd(STUDENT_ID_TABLE, score, id) >0)
			return true;
		return false;
	}

	
	/**
	 * 修改数据
	 * @param id
	 * @param infoMap
	 * @return
	 */
	public boolean updateInfo(String id, Map<String, String> infoMap) {
		
		if (JEDIS.hmset(id, infoMap).equals("OK"))
			return true;
		return false;
	}

	/**
	 * 查询一个Id的所有字段   查询一行数据
	 * @param String id
	 * @return Map<String, String>
	 */
	public Map<String, String> getOneStudent(String id) {
		Map<String, String> infoMap = JEDIS.hgetAll(id);
		return infoMap;
	}
	/**
	 * 
	 * @return
	 */
	public List<Map<String,String>> getAllStudent() {
		List<Map<String,String>> list = new LinkedList<>();
		Set<String> idSet = JEDIS.zrangeByScore(STUDENT_ID_TABLE, 0, 150);
		for (String id : idSet) {
			Map<String, String> map = JEDIS.hgetAll(id);
			map.put("id",id);
			list.add(map);
		}
		Collections.sort(list, new Comparator<Map<String,String>>() {  
			@Override
			public int compare(Map<String, String> o1, Map<String, String> o2) {
				if(Integer.parseInt(o1.get("avgscore"))>Integer.parseInt(o2.get("avgscore")))
					return -1;
				return 1;
			}  
		}); 
		System.out.println(list);
		return list;
	}

	/**
	 * 查询一个Id的某些字段
	 * @param String id, String[] field
	 * @return Map<String, String>
	 */
	public List<String> getMultInfo(String id, String[] field) {
		List<String> list = JEDIS.hmget(id, field);
		return list;
	}

	/**
	 * 
	 * @param id
	 * @param field
	 * @return String property 单个字段值
	 */
	public String getOneInfo(String id, String field) {
		String property = JEDIS.hget(id, field);
		return property;
	}

	
	public Long getIdCount() {
		Long idcount = JEDIS.zcount(STUDENT_ID_TABLE, 1, 1);
		return idcount;
	}
	// hset 修改给定字段
	// HSETNX 增加不存在的字段
	// HVALS 返回所有的字段值 不包含value
	// HEXISTS 字段是否存在

}