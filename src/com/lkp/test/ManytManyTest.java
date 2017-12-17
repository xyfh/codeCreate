package com.lkp.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lkp.controller.CourseController;
import com.lkp.controller.TeacherController;

/**
 * @date 2017-11-7上午10:26:33
 * @author lkp
 * @mail 1253364701@qq.com
 */
public class ManytManyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-config.xml");
		TeacherController teacherController = (TeacherController) ctx.getBean("teacherController");
		CourseController courseController = (CourseController) ctx.getBean("courseController");
//		teacherController.query("teacher1");
		courseController.query("1");
	}

}
