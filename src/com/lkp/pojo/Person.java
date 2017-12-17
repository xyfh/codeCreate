package com.lkp.pojo;

import java.io.Serializable;  
import java.util.HashSet;  
import java.util.Set;  
import javax.persistence.CascadeType;  
import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;  
import javax.persistence.Id;  
import javax.persistence.JoinColumn;  
import javax.persistence.OneToMany;  
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;  
import org.hibernate.annotations.Fetch;  
import org.hibernate.annotations.FetchMode;  
import org.hibernate.annotations.GenericGenerator;  
/**
 * @date 2017-11-4下午8:54:08
 * @author lkp
 * @mail 1253364701@qq.com
 */
@SuppressWarnings("serial")  
@Entity  
@Table(name = "person")  
public class Person extends Pojo{  
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String id;  
	
	@Column(name="uname")  
	private String uname;  
	
	@OneToOne(mappedBy="person")
	@Fetch(FetchMode.SELECT)
	private Card card;
	
	//默认懒加载
	@OneToMany(targetEntity=Address.class,cascade=CascadeType.ALL)
	@JoinColumn(name="personId",updatable=false)
	private Set<Address> sets = new HashSet<Address>();
	
	public Set<Address> getSets() {
		return sets;
	}  
	public void setSets(Set<Address> sets) {  
		this.sets = sets;  
	}  
	public Person() {  
		super();  
	}  
	public String getId() {  
		return id;  
	}  
	public void setId(String id) {  
		this.id = id;  
	}  
	public String getUname() {  
		return uname;  
	}  
	public void setUname(String uname) {  
		this.uname = uname;  
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}  
	
	
}