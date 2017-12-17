package  com.lkp.controller;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lkp.service.CourseService;

import com.lkp.pojo.Course;
import com.lkp.pojo.Teacher;

/**
 * 代码自动生成类
 * @author lkp 
 * mail 1253364701@qq.com
 * create:2017-11-07 10:12
 * update:
 */
@RequestMapping(value="/controller/course")
@Controller
public class CourseController {

	
	Logger logger  =  Logger.getLogger(this.getClass());

	@Autowired private CourseService courseService;
	
	/**
	 * 列表页
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String courseList(){
		
		return "controller/course/course-list";
	}

	/**
	 * 查询页
	 * @return
	 */
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public String query(String id){
		Course course = courseService.get(id);
		System.out.println("course:"+course.getName());
		Set<Teacher> teachers = course.getTeachers();
		for(Teacher t:teachers){
			System.out.println("teacher:"+t.getName());
		}
		return "controller/course/course-query";
	}
	
}
