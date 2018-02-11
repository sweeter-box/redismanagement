package com.biz.dao;

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
	static final String STUDENT_ID_TABLE = "studentid";
	//设置ID初值
	static final String STUDENT_START_ID = "180001";
	//存放最后一插入的ID，实现ID自增
	static final String STUDENT_END_ID_KEY = "tempID";

	/**
	 * 删除key-value
	 * 
	 * @param id
	 * @return
	 */
	public boolean del(String id) {
		if (JEDIS.del(id) == JEDIS.zrem(STUDENT_ID_TABLE, id))
			return true;
		return false;
	}

	/**
	 * 插入一条数据
	 * 
	 * @param infoMap
	 * @return
	 */
	public boolean insertInfo(Map<String, String> infoMap) {
		int score = Integer.parseInt(infoMap.get("avgscore"));
		//查出自增ID
		Long tempID = JEDIS.incr(STUDENT_END_ID_KEY);
		String id;
		if (tempID == 1L) {
			id =STUDENT_START_ID;
		} else {
			id = tempID.toString();
		}
		if (JEDIS.zadd(STUDENT_ID_TABLE, score, id) > 0 && JEDIS.hmset(id, infoMap).equals("OK")) {
			JEDIS.set(STUDENT_END_ID_KEY, id);
			return true;
		}
		return false;
	}

	/**
	 * 修改数据
	 * 
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
	 * 查询一个Id的所有字段 查询一行数据
	 * 
	 * @param String
	 *            id
	 * @return Map<String, String>
	 */
	public Map<String, String> getOneStudent(String id) {
		Map<String, String> infoMap = JEDIS.hgetAll(id);
		return infoMap;
	}

	/**
	 * 查出所有的student信息
	 * @return  List<Map<String, String>> 
	 */
	public List<Map<String, String>> getAllStudent() {
		List<Map<String, String>> list = new LinkedList<>();
		Set<String> idSet = JEDIS.zrangeByScore(STUDENT_ID_TABLE, 0, 150);
		for (String id : idSet) {
			Map<String, String> map = JEDIS.hgetAll(id);
			map.put("id", id);
			list.add(map);
		}
		return list;
	}

	/**
	 * 查询一个Id的某些字段
	 * 
	 * @param String
	 *            id, String[] field
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
		Long idcount = JEDIS.zcount(STUDENT_ID_TABLE, 0, 150);
		return idcount;
	}
	// hset 修改给定字段
	// HSETNX 增加不存在的字段
	// HVALS 返回所有的字段值 不包含value
	// HEXISTS 字段是否存在

}