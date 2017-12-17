package  com.lkp.pojo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import com.lkp.pojo.Pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lkp.code.AddInterface;
import com.lkp.code.PageList;
/**
 * 代码自动生成类
 * 身份证，onetoone拥有方
 * @author lkp 
 * mail 1253364701@qq.com
 * create:2017-11-05 16:34
 * update:
 */
@Entity
@Table(name = "card")
public class Card extends Pojo {
	
	@PageList(name="主键")@AddInterface(name="主键")
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String id;
	
	@PageList(name="编号")@AddInterface(name="编号")
	@Column(name = "number", length = 20)
	private Integer bh;
	
	@PageList(name="所属人")@AddInterface(name="所属人")
	@OneToOne
	@JoinColumn(name="pid")
	@Fetch(FetchMode.SELECT)
	private Person person;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getBh() {
		return bh;
	}

	public void setBh(Integer bh) {
		this.bh = bh;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
