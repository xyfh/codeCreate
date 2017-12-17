package  com.lkp.pojo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
 * 课程表
 * @author lkp 
 * mail 1253364701@qq.com
 * create:2017-11-06 18:08
 * update:
 */
@Entity
@Table(name = "t_course")
public class Course extends Pojo {
	
	@PageList(name="主键")@AddInterface(name="主键")
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String id;
	
	@PageList(name="名称")@AddInterface(name="名称")
	@Column(name = "name", length = 20)
	private String name;
	
	/**
	 * @JoinColumn表示我方在对方中的外键名称，我方Course在对方外键的名称就是 cid，
	 * @inverseJoinColumns表示对方在我方的外键名称，对方是Teacher，在我方外键的名称就是 tid
	 */
	@ManyToMany(targetEntity = Teacher.class,fetch=FetchType.EAGER)		//ManyToMany指定多对多的关联关系
    @JoinTable(name="t_teacher_course",joinColumns={@JoinColumn(name="cid")},inverseJoinColumns={@JoinColumn(name= "tid")})
	private Set<Teacher>teachers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	
}
