package managment;

import java.util.Date;
import java.util.Random;
import com.biz.model.Student;
import com.biz.service.StudentService;
import com.biz.service.StudentServiceImpl;

/**   
* @version 1.0   
* @author TianMengJun
* @since JDK 1.8.0_20
* Create at:   2018年2月6日 下午12:36:25   
* Description:  
*
*@param     
*/

public class StudentTest {

	
	public static void main(String[] args) {	
		StudentService service =new StudentServiceImpl();
		Date date = new Date();
		Random r = new Random(150);
		for(int i=1;i<10;i++) {
		r.nextInt(150);
	
		Student s = new Student("1000", "Student"+i,date ,r.nextInt(150), "测");		
		System.out.println(service.insertStudent(s));
		}
	}
}
