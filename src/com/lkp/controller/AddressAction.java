package  com.lkp.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lkp.service.AddressService;

import com.lkp.pojo.Address;

/**
 * 代码自动生成类
 * @author lkp 
 * mail 1253364701@qq.com
 * create:2017-11-05 11:21
 * update:
 */
@RequestMapping(value="/controller/address")
@Controller
public class AddressAction{

	
	Logger logger  =  Logger.getLogger(this.getClass());

	@Autowired private AddressService addressService;
	
	/**
	 * 列表页
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String addressList(){
		
		return "controller/address/address-list";
	}

	/**
	 * 查询页
	 * @return
	 */
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public String query(String id){
		
		Address address = addressService.get(id);
		System.out.println("address:"+address.getAddress());
		System.out.println("Person:"+address.getPerson().getUname());
		return "controller/address/address-query";
	}
	
}
