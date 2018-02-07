package com.biz.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.dao.Pageutil;
import com.biz.service.StudentService;
import com.biz.service.StudentServiceImpl;

/**   
* @version 1.0   
* @author TianMengJun
* @since JDK 1.8.0_20
* Create at:   2018年2月6日 下午1:42:50   
* Description:  
*
*@param     
*/
@WebServlet("/index")
public class IndexController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6883218298210708757L;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentService service = new StudentServiceImpl();
		List<Map<String, String>> list =service.getAllStudent();
		int pagenum;
		String temp =request.getParameter("pagenum");
		if(temp==null){
			pagenum=1;
		}else{
			pagenum=Integer.parseInt(temp);					
		}
		
		Pageutil<Map<String, String>> pu=new Pageutil<>();		
		List<Map<String, String>>  studentList=pu.ListSplit(list,pagenum);
		int pagesum=pu.getPagesum();
		request.setAttribute("total", pu.getTotal());
		request.getSession().setAttribute("pagesum", pagesum);
		request.getSession().setAttribute("pagenum", pagenum);
		request.setAttribute("studentList", studentList);
		request.getRequestDispatcher("info.jsp?pagesum="+pagesum).forward(request, response);
	}
	

}
