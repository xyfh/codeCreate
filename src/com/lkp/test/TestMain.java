package com.lkp.test;


import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lkp.controller.AddressAction;
import com.lkp.controller.CardController;
import com.lkp.controller.PersonController;
import com.lkp.pojo.Person;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-config.xml");
		PersonController peosonController = (PersonController) ctx.getBean("personController");
		AddressAction addressAction = (AddressAction) ctx.getBean("addressAction");
		CardController cardController = (CardController)ctx.getBean("cardController");
//		Person person = new Person();
//		person.setName("hello111");
//		person.setMount(4.5);
//		person.setDate(new Date());
//		peoson.addPerson(person);
		
		
//		addressAction.query("eee");
//		Person person = peosonController.queryPerson("a");
//		peosonController.deletePerson(person);//级联删除成功（onetoone无法级联删除）
		cardController.delete(cardController.query("eee"));
		
	}
	
}
