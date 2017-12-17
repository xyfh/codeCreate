package  com.lkp.pojo;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import com.lkp.code.AddInterface;
import com.lkp.code.PageList;
import com.lkp.pojo.Pojo;
/**
 * 代码自动生成类
 * @author lkp 
 * mail 1253364701@qq.com
 * create:2017-11-05 11:08
 * update:
 */
@Entity
@Table(name = "address")
public class Address extends Pojo {
	
	@PageList(name="主键")@AddInterface(name="主键")
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;
	
	@PageList(name="家庭地址")@AddInterface(name="家庭地址")
	@Column(name = "address", length = 20)
	private String address;
	
	//默认非懒加载
	@PageList(name="所属人")@AddInterface(name="所属人")
	@ManyToOne(targetEntity = Person.class,cascade=CascadeType.ALL)
	@JoinColumn(name="personId",updatable=false)
	@Fetch(FetchMode.SELECT)
	private Person person;
	
	@PageList(name="年龄")@AddInterface(name="年龄")
	@Column(name = "age", length = 11)
	private Integer age;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	

}
