package com.biz.service;

import java.util.List;
import java.util.Map;

import com.biz.model.Student;

/**   
* @version 1.0   
* @author TianMengJun
* @since JDK 1.8.0_20
* Create at:   2018年2月6日 上午9:38:25   
* Description:  
*
*@param     
*/

public interface StudentService {
	boolean insertStudent(Student student);
	boolean deleteStudent(String id);
	 String getOneStudent(String id);
	 List<Map<String,String>> getAllStudent();
	 boolean updateStudent(Student student);
	

}
