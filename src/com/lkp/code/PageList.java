package com.lkp.code;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * 
 * 自定义list页面显示列注解 [字段加入此注解，自动代码生成list页面字段列表时，加入此字段]
 * <p>Date: 2016-8-6</p>
 * @author lkp
 * @version 1.0
 */
@Target(ElementType.FIELD)//这个标注应用于属性
@Retention(RetentionPolicy.RUNTIME)//标注会一直保留到运行时
@Documented//将此注解包含在javadoc中
public @interface PageList {
	String field() default "" ;	//列名
	String name() default "" ;	//显示列名
	String width() default "";	//
	String[] status() default {};	//是否状态字段，{"0:生效","1:失效"}
	
}
