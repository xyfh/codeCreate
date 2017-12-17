package com.lkp.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lkp.pojo.Address;
import com.lkp.pojo.Person;
import com.lkp.service.PersonService;

@SuppressWarnings("hiding")
@Transactional
@Controller
@RequestMapping(value="peoson")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
    public Person queryPerson(String id){
    	Person person = personService.get(id);
    	if(person != null){
    		System.out.println("Person: "+person.getUname());
    		System.out.println("Card--: "+person.getCard().getBh());
    		Set<Address> list = person.getSets();
    		for(Address add:list){
    			System.out.println(add.getAddress());
    			Person per = add.getPerson();
    			System.out.println("Address: "+per.getUname());
    		}
    		
    	}else{
    		System.out.println("没有查询到。");
    	}
    	return person;
    }
      
    public void queryPersons(){
//    	System.out.println(personService.queryPersons(person));
    }
    
    public void updatePerson(Person person){
    	
    	personService.update(person);
    	
    }
    
    public void deletePerson(String id){
    	
    	personService.delete(id);
    	
    }
    
    public void deletePerson(Person person){
    	
    	personService.delete(person);
    	
    }

}
