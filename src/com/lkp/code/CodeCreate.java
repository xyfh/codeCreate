package com.lkp.code;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.lkp.pojo.Address;
import com.lkp.pojo.Card;
import com.lkp.pojo.Course;
import com.lkp.pojo.Teacher;


/**
 * 代码自动生成工具类
 * @author lkp
 * 2016-08-03
 */
public class CodeCreate {
	
	
	public static String dao_path = "";
	
	static VelocityContext context=null;

	/**
	 * 代码自动生成工具类
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		
		List<Class> list = new ArrayList<Class>();
//		list.add(FileTest.class);
		list.add(Teacher.class);
		
		for(Object pojo:list){
			Object obj = ((Class) pojo).newInstance();
			dao(obj);
			daoImpl(obj);
			service(obj);
			serviceImpl(obj);
			controller(obj);
//			js(obj);
//			pageList(obj);
//			pageAdd(obj);
//			pageUpdate(obj);
		}
		
	}

	
	

	//生成controller代码
	public static void controller(Object obj){
		try {
			String model=obj.getClass().getSimpleName();
			String model_path=obj.getClass().getName();
			String filePath = JavaPath.project_path +"\\src\\" + JavaPath.controller_path.replace(".", "\\")+"\\"+model+"Controller.java";
//			System.out.println("filePath:"+filePath);
			
			context=new  VelocityContext();
			context.put("model", model);								//实体类名称
			context.put("columns", getControllerColumns(obj));			//添加数据字段属性{field,name,width}
			context.put("model_lowercase", (model.subSequence(0, 1)+"").toLowerCase()+model.substring(1));	//实体类名称,首字母小写
			context.put("package", JavaPath.controller_path);			//添加包名
			context.put("implements_package", JavaPath.service_path);	//添加接口包名
			context.put("request", JavaPath.controller_path.substring(JavaPath.controller_path.lastIndexOf(".")+1));
			context.put("model_path", model_path);						//实体类包路径
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			context.put("nowDate", dateFormat.format(new Date()));		//添加日期
			
			generateFile(context,Velocity.getTemplate(JavaPath.controllervm_path, "UTF-8"),filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void dao(Object obj){
		try {
			String model=obj.getClass().getSimpleName();
			String model_path=obj.getClass().getName();
			String filePath = JavaPath.project_path +"\\src\\" + JavaPath.dao_path.replace(".", "\\")+"\\"+model+"Dao.java";
//			System.out.println("filePath:"+filePath);
			
			context=new  VelocityContext();
			context.put("model", model);							//实体类名称
			context.put("package", JavaPath.dao_path);				//添加包名
			context.put("model_path", model_path);					//实体类包路径
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			context.put("nowDate", dateFormat.format(new Date()));	//添加日期
			generateFile(context,Velocity.getTemplate(JavaPath.daovm_path, "UTF-8"),filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void daoImpl(Object obj){
		try {
			String model=obj.getClass().getSimpleName();
			String model_path=obj.getClass().getName();
			String filePath = JavaPath.project_path +"\\src\\" + JavaPath.daoimpl_path.replace(".", "\\")+"\\"+model+"DaoImpl.java";
//			System.out.println("filePath:"+filePath);
			
			context=new  VelocityContext();
			context.put("model", model);								//实体类名称
			context.put("package", JavaPath.daoimpl_path);				//添加包名
			context.put("implements_package", JavaPath.dao_path);		//添加接口包名
			context.put("model_path", model_path);						//实体类包路径
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			context.put("nowDate", dateFormat.format(new Date()));	//添加日期
			generateFile(context,Velocity.getTemplate(JavaPath.daoImplvm_path, "UTF-8"),filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void service(Object obj){
		try {
			String model=obj.getClass().getSimpleName();
			String model_path=obj.getClass().getName();
			String filePath = JavaPath.project_path +"\\src\\" + JavaPath.service_path.replace(".", "\\")+"\\"+model+"Service.java";
//			System.out.println("filePath:"+filePath);
			
			context=new  VelocityContext();
			context.put("model", model);							//实体类名称
			context.put("package", JavaPath.service_path);			//添加包名
			context.put("model_path", model_path);					//实体类包路径
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			context.put("nowDate", dateFormat.format(new Date()));	//添加日期
			
			generateFile(context,Velocity.getTemplate(JavaPath.servicevm_path, "UTF-8"),filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void serviceImpl(Object obj){
		try {
			String model=obj.getClass().getSimpleName();
			String model_path=obj.getClass().getName();
			String filePath = JavaPath.project_path +"\\src\\" + JavaPath.serviceimpl_path.replace(".", "\\")+"\\"+model+"ServiceImpl.java";
//			System.out.println("filePath:"+filePath);
			
			context=new  VelocityContext();
			context.put("model", model);								//实体类名称
			context.put("package", JavaPath.serviceimpl_path);			//添加包名
			context.put("implements_package", JavaPath.service_path);	//添加接口包名
			context.put("model_path", model_path);						//实体类包路径
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			context.put("nowDate", dateFormat.format(new Date()));	//添加日期
			
			generateFile(context,Velocity.getTemplate(JavaPath.serviceImplvm_path, "UTF-8"),filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//生成页面代码
	public static void pageList(Object obj){
		try {
			String model=obj.getClass().getSimpleName();
			String model_lowercase =  (model.subSequence(0, 1)+"").toLowerCase()+model.substring(1);//实体类名称,首字母小写
			String filePath = JavaPath.project_path + JavaPath.jsp_path+"\\"+model_lowercase;
//			System.out.println("filePath:"+filePath);
			
			context=new  VelocityContext();
			context.put("columns", getListColumns(obj));							//添加数据字段属性{field,name,width}
			context.put("model", model);								//实体类名称
			context.put("model_lowercase", model_lowercase);			//实体类名称,首字母小写
			context.put("request", JavaPath.controller_path.substring(JavaPath.controller_path.lastIndexOf(".")+1));
			File file = new File(filePath);
			if(!file.exists()){
				file.mkdir();
				System.out.println("创建目录："+filePath);
			}
			filePath += "\\"+model_lowercase+"-list.jsp";
			generateFile(context,Velocity.getTemplate(JavaPath.listvm_path, "UTF-8"),filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//生成页面代码
	public static void pageAdd(Object obj){
		try {
			String model=obj.getClass().getSimpleName();
			String model_lowercase =  (model.subSequence(0, 1)+"").toLowerCase()+model.substring(1);//实体类名称,首字母小写
			String filePath = JavaPath.project_path + JavaPath.jsp_path+"\\"+model_lowercase;
//				System.out.println("filePath:"+filePath);
			
			context=new  VelocityContext();
			context.put("columns", getAddColumns(obj));							//添加数据字段属性{field,name,width}
			context.put("model", model);								//实体类名称
			context.put("model_lowercase", model_lowercase);			//实体类名称,首字母小写
			context.put("request", JavaPath.controller_path.substring(JavaPath.controller_path.lastIndexOf(".")+1));
			File file = new File(filePath);
			if(!file.exists()){
				file.mkdir();
				System.out.println("创建目录："+filePath);
			}
			filePath += "\\"+model_lowercase+"-add.jsp";
			generateFile(context,Velocity.getTemplate(JavaPath.addvm_path, "UTF-8"),filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//生成页面代码
	public static void pageUpdate(Object obj){
		try {
			String model=obj.getClass().getSimpleName();
			String model_lowercase =  (model.subSequence(0, 1)+"").toLowerCase()+model.substring(1);//实体类名称,首字母小写
			String filePath = JavaPath.project_path + JavaPath.jsp_path+"\\"+model_lowercase;
//					System.out.println("filePath:"+filePath);
			
			context=new  VelocityContext();
			context.put("columns", getUpdateColumns(obj));							//添加数据字段属性{field,name,width}
			context.put("model", model);								//实体类名称
			context.put("model_lowercase", model_lowercase);			//实体类名称,首字母小写
			context.put("request", JavaPath.controller_path.substring(JavaPath.controller_path.lastIndexOf(".")+1));
			File file = new File(filePath);
			if(!file.exists()){
				file.mkdir();
				System.out.println("创建目录："+filePath);
			}
			filePath += "\\"+model_lowercase+"-update.jsp";
			generateFile(context,Velocity.getTemplate(JavaPath.updatevm_path, "UTF-8"),filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//生成页面代码
	public static void js(Object obj){
		try {
			String model=obj.getClass().getSimpleName();
			String model_lowercase =  (model.subSequence(0, 1)+"").toLowerCase()+model.substring(1);//实体类名称,首字母小写
			String filePath = JavaPath.project_path + JavaPath.js_path+"\\"+model_lowercase+".js";;
//				System.out.println("filePath:"+filePath);
			context=new  VelocityContext();
			context.put("model", model);								//实体类名称
			context.put("model_lowercase", model_lowercase);			//实体类名称,首字母小写
			context.put("request", JavaPath.controller_path.substring(JavaPath.controller_path.lastIndexOf(".")+1));
			generateFile(context,Velocity.getTemplate(JavaPath.jsvm_path, "UTF-8"),filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成代码文件
	 * @param context	变量map
	 * @param tpl		模板对象
	 * @param filePath	文件路径
	 */
	public static void generateFile(VelocityContext context, Template tpl, String filePath){
		try{
			File file = new File(filePath);
			if(!file.exists()){
				file.createNewFile();
				System.out.println("创建文件："+filePath);
			}else{
				System.out.println("修改文件："+filePath);
			}
			PrintWriter pw = new PrintWriter(file);
			tpl.merge(context, pw);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回实体对象属性列表{field:英文名,name:中文名,width:宽度,formatter:}
	 * PageListNo此注解排除，PageList此注解添加属性
	 * @param obj
	 * @return
	 */
	public static List<Map<String, String>> getListColumns(Object obj){
		List<Map<String, String>>  list=new ArrayList<Map<String, String>>(0);
		Field[] modelFields= obj.getClass().getDeclaredFields();
		String type=null;
	 	for (Field field : modelFields) {
			field.setAccessible(true);
		    if(!field.isAnnotationPresent(PageList.class)){
		    	continue;
		    }
		    type = field.getType().getSimpleName();	
//		    System.out.println(field.getName()+">>>"+type);
			if (type.equals("Set") || type.equals("List") || type.equals("Map")) {
				continue;
			}
			Map<String, String> column = new HashMap<String, String>();
			column.put("width", "");	//设置默认，防止Velocity if语句报错
			column.put("formatter", "");//设置默认，防止Velocity if语句报错
			if(field.isAnnotationPresent(PageList.class)){		//设置注解配置参数
			  PageList pageList= field.getAnnotation(PageList.class);
			  column.put("field", field.getName());
			  column.put("name", pageList.name());
			  column.put("width", pageList.width());
			  
			  String[] status = pageList.status();//
			  if(status != null && status.length > 0){
				  StringBuffer formatter = new StringBuffer("function(value,row,index) {");
				  for(String sta:status){	//status={"1:生效","2:失效","3:过期"}
					  formatter.append("if(value=="+sta.split(":")[0]+") return '"+sta.split(":")[1]+"';");
				  }
				  formatter.append("return '';}");
				  column.put("formatter", formatter.toString());
			  }
			}else{
    		  column.put("name", field.getName());
			  column.put("field", field.getName());
			}
			list.add(column);
		}
		return list;
	}
	
	/**
	 * 返回实体对象属性列表{field:英文名,name:中文名,width:宽度,formatter:}
	 * PageListNo此注解排除，PageList此注解添加属性
	 * @param obj
	 * @return
	 */
	public static List<Map<String, String>> getAddColumns(Object obj){
		List<Map<String, String>>  list=new ArrayList<Map<String, String>>(0);
		Field[] modelFields= obj.getClass().getDeclaredFields();
		String type=null;
	 	for (Field field : modelFields) {
			field.setAccessible(true);
		    if(!field.isAnnotationPresent(AddInterface.class)){
		    	continue;
		    }
		    type = field.getType().getSimpleName();	
//		    System.out.println(field.getName()+">>>"+type);
			if (type.equals("Set") || type.equals("List") || type.equals("Map")) {
				continue;
			}
			Map<String, String> column = new HashMap<String, String>();
			column.put("maxlength", "");	//设置默认，防止Velocity if语句报错
			column.put("type", "text");		//设置默认，防止Velocity if语句报错
			column.put("required", "");		//设置默认，防止Velocity if语句报错
			column.put("status", "");		//设置默认，防止Velocity if语句报错
			
			if(field.isAnnotationPresent(AddInterface.class)){		//设置注解配置参数
				
			  AddInterface addInterface= field.getAnnotation(AddInterface.class);
			  column.put("field", field.getName());
			  column.put("name", addInterface.name());
			  column.put("maxlength", addInterface.maxlength());
			  column.put("type", addInterface.type());
			  column.put("required", addInterface.required());
			  
			  String status = addInterface.status();
			  if(status != null && !status.equals("")){		//是否下拉显示
				  String model=obj.getClass().getSimpleName();
				  String model_lowercase = (model.subSequence(0, 1)+"").toLowerCase()+model.substring(1);	//实体类名称,首字母小写
				  String request = JavaPath.controller_path.substring(JavaPath.controller_path.lastIndexOf(".")+1);
				  StringBuffer formatter = new StringBuffer("<i:data code=\""+request+"."+model_lowercase+"."+field.getName()+"\" name=\""+field.getName()+"\" cssClass=\"field\" selectedValue=\""+status+"\"/>");
				  column.put("status", formatter.toString());
			  }
			}else{
    		  column.put("name", field.getName());
			  column.put("field", field.getName());
			}
			list.add(column);
		}
		return list;
	}
	
	/**
	 * 返回实体对象属性列表{field:英文名,name:中文名,width:宽度,status:<i:data ...>,value:"column.field"}[${param.$column.field}模板报错]
	 * PageListNo此注解排除，PageList此注解添加属性
	 * @param obj
	 * @return
	 */
	public static List<Map<String, String>> getUpdateColumns(Object obj){
		List<Map<String, String>>  list=new ArrayList<Map<String, String>>(0);
		Field[] modelFields= obj.getClass().getDeclaredFields();
		String type=null;
	 	for (Field field : modelFields) {
			field.setAccessible(true);
		    if(!field.isAnnotationPresent(AddInterface.class)){
		    	continue;
		    }
		    type = field.getType().getSimpleName();	
//		    System.out.println(field.getName()+">>>"+type);
			if (type.equals("Set") || type.equals("List") || type.equals("Map")) {
				continue;
			}
			Map<String, String> column = new HashMap<String, String>();
			column.put("maxlength", "");	//设置默认，防止Velocity if语句报错
			column.put("type", "text");		//设置默认，防止Velocity if语句报错
			column.put("required", "");		//设置默认，防止Velocity if语句报错
			column.put("status", "");		//设置默认，防止Velocity if语句报错
			column.put("value", "${params."+field.getName()+"}");//设置默认值
			
			if(field.isAnnotationPresent(AddInterface.class)){		//设置注解配置参数
				
			  AddInterface addInterface= field.getAnnotation(AddInterface.class);
			  column.put("field", field.getName());
			  column.put("name", addInterface.name());
			  column.put("maxlength", addInterface.maxlength());
			  column.put("type", addInterface.type());
			  column.put("required", addInterface.required());
			  
			  String status = addInterface.status();
			  if(status != null && !status.equals("")){		//是否下拉显示
				  String model=obj.getClass().getSimpleName();
				  String model_lowercase = (model.subSequence(0, 1)+"").toLowerCase()+model.substring(1);	//实体类名称,首字母小写
				  String request = JavaPath.controller_path.substring(JavaPath.controller_path.lastIndexOf(".")+1);
				  StringBuffer formatter = new StringBuffer("<i:data code=\""+request+"."+model_lowercase+"."+field.getName()+"\" name=\""+field.getName()+"\" cssClass=\"field\" selectedValue=\"${params."+field.getName()+"}\"/>");
				  column.put("status", formatter.toString());
			  }
			}else{
    		  column.put("name", field.getName());
			  column.put("field", field.getName());
			}
			list.add(column);
		}
		return list;
	}
	
	/**
	 * 返回实体对象属性列表{field:英文名,update:pojo.setName(param.getName())}
	 * PageListNo此注解排除，PageList此注解添加属性
	 * @param obj
	 * @return
	 */
	public static List<Map<String, String>> getControllerColumns(Object obj){
		List<Map<String, String>>  list=new ArrayList<Map<String, String>>(0);
		Field[] modelFields= obj.getClass().getDeclaredFields();
		String type=null;
	 	for (Field field : modelFields) {
			field.setAccessible(true);
		    if(!field.isAnnotationPresent(AddInterface.class)){
		    	continue;
		    }
		    type = field.getType().getSimpleName();	
//		    System.out.println(field.getName()+">>>"+type);
			if (type.equals("Set") || type.equals("List") || type.equals("Map")) {
				continue;
			}
			Map<String, String> column = new HashMap<String, String>();
			
			column.put("field", field.getName());
			
			String name = field.getName();
			name = (name.subSequence(0, 1)+"").toUpperCase()+name.substring(1);
			column.put("update","pojo.set"+name+"(param.get"+name+"())");	//pojo.setUsername(param.getUsername());
			
			list.add(column);
		}
		return list;
	}
	
	/**
	 * 递归向上创建文件夹
	 * @param file
	 */
	public static void createFolder(File file){
		if (!file.getParentFile().exists()) {  
			createFolder(file.getParentFile());
			file.getParentFile().mkdir();
        }
	}
	
}
