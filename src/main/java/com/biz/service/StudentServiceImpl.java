package com.biz.service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.JSONObject;

import com.biz.dao.Dao;
import com.biz.model.Student;

/**
 * @version 1.0
 * @author TianMengJun
 * @since JDK 1.8.0_20 Create at: 2018年2月6日 上午9:52:18 Description:
 *
 * @param
 */

public class StudentServiceImpl implements StudentService {
	Dao dao = new Dao();

	@Override
	public boolean insertStudent(Student student) {
		/*UUID uuid = UUID.randomUUID();
		String uuID = uuid.toString().split("-")[0];
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		sf.format(now);
		String id = "S" + sf.format(now) + uuID;*/
		Map<String, String> map = new HashMap<>();
		map.put("name", student.getName());
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String birthday = date.format(student.getBirthday());
		map.put("birthday", birthday);
		map.put("description", student.getDescription());
		map.put("avgscore", student.getAvgscore() + "");
		if (dao.insertInfo(map))
			return true;
		return false;
	}

	@Override
	public boolean deleteStudent(String id) {
		if (dao.del(id))
			return true;
		return false;
	}

	@Override
	public String getOneStudent(String id) {
		Map<String, String> map = dao.getOneStudent(id);
		JSONObject json = new JSONObject(map);
		return json.toString();
	}

	@Override
	public List<Map<String, String>> getAllStudent() {
		List<Map<String, String>> allList =dao.getAllStudent();
		//结果集按分数排序
		Collections.sort(allList, new Comparator<Map<String, String>>() {
			@Override
			public int compare(Map<String, String> o1, Map<String, String> o2) {
				if (Integer.parseInt(o1.get("avgscore")) > Integer.parseInt(o2.get("avgscore")))
					return -1;
				return 1;
			}
		});
		return allList;
	}

	@Override
	public boolean updateStudent(Student student) {
		Map<String, String> map = new HashMap<>();
		String id = student.getId();
		map.put("name", student.getName());
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String birthday = date.format(student.getBirthday());
		map.put("birthday", birthday);
		map.put("description", student.getDescription());
		map.put("avgscore", student.getAvgscore() + "");
		if (dao.updateInfo(id, map))
			return true;
		return false;
	}

}
