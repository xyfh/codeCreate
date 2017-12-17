package  com.lkp.controller;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lkp.service.TeacherService;

import com.lkp.pojo.Course;
import com.lkp.pojo.Teacher;

/**
 * 代码自动生成类
 * @author lkp 
 * mail 1253364701@qq.com
 * create:2017-11-07 10:22
 * update:
 */
@RequestMapping(value="/controller/teacher")
@Controller
public class TeacherController {

	
	Logger logger  =  Logger.getLogger(this.getClass());

	@Autowired private TeacherService teacherService;
	
	/**
	 * 列表页
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String teacherList(){
		
		return "controller/teacher/teacher-list";
	}

	/**
	 * 查询页
	 * @return
	 */
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public String query(String id){
		Teacher teacher = teacherService.get(id);
		System.out.println("teacher:"+teacher.getName());
		Set<Course> course = teacher.getCourses();
		for(Course c:course){
			System.out.println("courses:"+c.getName());
		}
		return "controller/teacher/teacher-query";
	}
	
}
