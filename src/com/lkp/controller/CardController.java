package  com.lkp.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lkp.service.CardService;

import com.lkp.pojo.Card;

/**
 * 代码自动生成类
 * @author lkp 
 * mail 1253364701@qq.com
 * create:2017-11-05 17:50
 * update:
 */
@RequestMapping(value="/controller/card")
@Controller
public class CardController{

	
	Logger logger  =  Logger.getLogger(this.getClass());

	@Autowired private CardService cardService;
	
	/**
	 * 列表页
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String cardList(){
		
		return "controller/card/card-list";
	}

	/**
	 * 查询页
	 * @return
	 */
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public Card query(String id){
		Card card = cardService.get(id);
		System.out.println("card:"+card.getBh());
		System.out.println("person:"+card.getPerson().getUname());
		return card;
	}
	
	public void delete(Card card){
		cardService.delete(card);
	}
	
	
}
