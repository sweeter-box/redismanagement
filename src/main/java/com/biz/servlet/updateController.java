package com.biz.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.model.Student;
import com.biz.service.StudentService;
import com.biz.service.StudentServiceImpl;

/**   
* @version 1.0   
* @author TianMengJun
* @since JDK 1.8.0_20
* Create at:   2018年2月6日 下午5:21:18   
* Description:  
*
*@param     
*/
@WebServlet("/updateController")
public class updateController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      request.setCharacterEncoding("utf-8");
		StudentService service =new StudentServiceImpl();
		String  id = request.getParameter("id");
	    String name =request.getParameter("name");
	    int avgscore =Integer.parseInt(request.getParameter("avgscore"));
	    System.out.println(avgscore);
	    String description =request.getParameter("description");
	    Date birthday = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			birthday = sdf.parse(request.getParameter("birthday"));
			System.out.println(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
	    Student student = new Student(id,name,birthday,avgscore,description);				
	    
	    service.updateStudent(student);
	
	}

}
