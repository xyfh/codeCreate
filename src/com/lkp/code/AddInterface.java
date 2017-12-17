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
public @interface AddInterface {
	String field() default "" ;	//列名
	String name() default "" ;	//显示列名
	String maxlength() default "";	//输入最大长度
	String required() default "";	//是否必填
//	String validType() default "";	//验证方法：fn[system.user.checkUser]:用户名已存在！（validType:'fn[system.user.checkUser]',invalidMessage:'用户名已存在！'）
	String type() default "text";	//input类型
	String status() default "";		//是否显示下拉列表，无值不显示，默认选中当前(下拉查询数据字典项，父包名.类名.字典名)
	
	/**
	 * 文本域
	 * <textarea class="field-remark easyui-validatebox" name="remark" data-options="validType:'bytelen[0,100]',invalidMessage:'不能超过100个字节'">${function.remark}</textarea>
	 */
	
}
