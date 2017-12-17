package com.lkp.code;
/**
 * 包路径
 * @author hehe
 *
 */
public class JavaPath {
	
	//项目路径
	public static String project_path = System.getProperty("user.dir");		//D:\java\Workspaces\MyEclipse 10\icenterlkp
	
	//start-------------------------生成代码包路径--------------------------------
	
	public static String pojo_path = "com.lkp.pojo";		//pojo存放路径

	public static String dao_path = "com.lkp.dao";					//dao层路径

	public static String daoimpl_path = "com.lkp.dao.impl";	//dao层实现类路径
	
	public static String service_path = "com.lkp.service";			//service层类路径
	
	public static String serviceimpl_path = "com.lkp.service.impl";//service层类路径
	
	public static String controller_path = "com.lkp.controller";			//controller层路径
	
	public static String jsp_path = "\\WebRoot\\pages\\system";				//jsp页面存放路径
	
	public static String js_path = "\\WebRoot\\skins\\default\\js\\system";				//js存放路径
	
	//end-------------------------生成代码包路径--------------------------------
	
	//start-------------------------模板路径--------------------------------
	public static String pojovm_path = "\\resource\\template\\pojo.vm";
	public static String daovm_path = "\\resource\\template\\IDAO.vm";
	public static String daoImplvm_path = "\\resource\\template\\DAOImp.vm";
	public static String servicevm_path = "\\resource\\template\\IService.vm";;
	public static String serviceImplvm_path = "\\resource\\template\\ServiceImp.vm";;
	
	public static String controllervm_path = "\\resource\\template\\Controller.vm";;
	public static String listvm_path = "\\resource\\template\\listJsp.vm";
	public static String addvm_path = "\\resource\\template\\addJsp.vm";
	public static String updatevm_path = "\\resource\\template\\updateJsp.vm";
	public static String queryvm_path = "\\resource\\template\\queryJsp.vm";
	public static String jsvm_path = "\\resource\\template\\js.vm";
	
}
