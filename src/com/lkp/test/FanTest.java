package com.lkp.test;

import java.lang.reflect.ParameterizedType;

/**
 * 
 * @date 2017-10-28下午9:15:37
 * @author lkp
 * @mail 1253364701@qq.com
 */
public class FanTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A1 a = new A1<String>();
	}

}
class A1<T>{
	private Class<T> entityClass;
	public A1(){
		System.out.println(((ParameterizedType) getClass().getGenericSuperclass()));
		try {
			entityClass =(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			System.out.println(entityClass);
			System.out.println("eeeeee");
		} catch(Exception e) {}
	}
	
}
