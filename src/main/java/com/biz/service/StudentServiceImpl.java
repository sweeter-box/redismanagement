package com.biz.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
		UUID uuid = UUID.randomUUID();
		String uuID = uuid.toString().split("-")[0];
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		sf.format(now);
		String id = "S" + sf.format(now) + uuID;
		Map<String, String> map = new HashMap<>();
		map.put("name", student.getName());
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String birthday = date.format(student.getBirthday());
		map.put("birthday", birthday);
		map.put("description", student.getDescription());
		map.put("avgscore", student.getAvgscore() + "");
		if (dao.insertInfo(id, map))
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
		return dao.getAllStudent();
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
