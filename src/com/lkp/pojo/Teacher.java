package  com.lkp.pojo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import com.lkp.pojo.Pojo;
import com.lkp.code.PageList;
import com.lkp.code.AddInterface;
/**
 * 代码自动生成类
 * @author lkp 
 * mail 1253364701@qq.com
 * create:2017-11-06 18:08
 * update:
 */
@Entity
@Table(name = "t_teacher")
public class Teacher extends Pojo {
	
	@PageList(name="主键")@AddInterface(name="主键")
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String id;
	
	@PageList(name="名称")@AddInterface(name="名称")
	@Column(name = "name", length = 20)
	private String name;
	
	@ManyToMany(targetEntity = Course.class,mappedBy="teachers",fetch=FetchType.EAGER)//表示由Course那一方维护
	@Fetch(FetchMode.SELECT)
	private Set<Course>courses;

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

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
}
