package com.biz.model;
/**   
* @version 1.0   
* @author TianMengJun
* @since JDK 1.8.0_20
* Create at:   2018年2月7日 上午1:34:44   
* Description:  
*
*@param     
*/

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		 if(o1.getAvgscore()>o2.getAvgscore()){
	            return 1;
	        }else{
	            return -1;
	        }
	}

}