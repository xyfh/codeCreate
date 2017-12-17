package  com.lkp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lkp.dao.AddressDao;
import com.lkp.pojo.Address;

/**
 * 代码自动生成类
 * @author lkp 
 * mail 1253364701@qq.com
 * create:2017-11-05 11:21
 * update:
 */
@Repository
public class AddressDaoImpl extends BaseDaoImpl<Address> implements AddressDao {
	
	public List<Address> getAddress(String personName){
		Criteria criteria = getCriteria();
	    criteria.createCriteria("person").add(Restrictions.eq("uname", personName));
	    criteria.setFirstResult(0);
	    criteria.setMaxResults(15);
	    criteria.addOrder(Order.desc("seqNum"));
	    List<Address> adress = criteria.list();
	    for(Address adre:adress){
	    	System.out.println("addres:"+adre.getAddress());
	    }
	    return null;
	}
	
}
