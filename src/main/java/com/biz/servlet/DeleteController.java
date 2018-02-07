package com.biz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.service.StudentService;
import com.biz.service.StudentServiceImpl;

/**   
* @version 1.0   
* @author TianMengJun
* @since JDK 1.8.0_20
* Create at:   2018年2月6日 下午10:35:07   
* Description:  
*
*@param     
*/
@WebServlet("/deleteController")
public class DeleteController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3929506386311964886L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentService service = new StudentServiceImpl();
		int pagenum;
		String temp =request.getParameter("pagenum");
		if(temp==null){
			pagenum=1;
		}else{
			pagenum=Integer.parseInt(temp);					
		}
		int flag =1;
		String id =request.getParameter("id");
		if(id.length()>0 && id !=null && service.deleteStudent(id))
			flag++;
	
		request.setAttribute("result", flag);
		request.getRequestDispatcher("index?pagenum="+pagenum).forward(request, response);
	
	}

}
