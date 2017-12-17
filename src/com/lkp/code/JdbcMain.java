package com.lkp.code;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.CharUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;


public class JdbcMain {
	
	static VelocityContext context=null;
	
	static boolean isMysql = true;
    //URL指向要访问的数据库名mydata
//    static String url = "jdbc:mysql://localhost:3306/mytest2";
	 static String url = "jdbc:mysql://192.168.3.200:3307/xzdzf";
    //驱动程序名
    static String driver = "com.mysql.jdbc.Driver";
    //MySQL配置时的用户名
    static String user = "root";
    //MySQL配置时的密码
    static String password = "database";
    
//    static boolean isMysql = false;
//	//URL指向要访问的数据库名mydata
//    static String url = "jdbc:oracle:thin:@10.44.55.205:1521:ORCL";
//    // 驱动程序名
//    static String driver = "oracle.jdbc.driver.OracleDriver";
//    //MySQL配置时的用户名
//    static String user = "app";
//    //MySQL配置时的密码
//    static String password = "app";
    
    public static void main(String[] args) {
    	
//    	String[] tables = {"T_JZFP_XMXX"};//需要生成的表名称
//    	String[] tables = {"t_course","t_teacher"};//需要生成的表名称
    	String[] tables = {"t_dzf_zf_qzfyyjwtslhzb"};//需要生成的表名称
    	for(String table:tables){
    		List<Map<String, String>> columns = new ArrayList<Map<String,String>>();
    		if(isMysql){
    			columns = findTableMysql(table);
    			//处理columns,转换mysql到java数据类型,把字段名称转换为java变了名称user_name-->userName,获取数据长度
    			changeColumnsMysql(columns);
    		}else{
    			columns = findTableOracle(table);
    			//处理columns,转换oracle到java数据类型,把字段名称转换为java变了名称user_name-->userName,获取数据长度
        		changeColumnsOracle(columns);
    		}
    		createPojo(columns,table);
    	}
    }
    
    /**
     * 创建pojo实体类
     * @param columns
     * @param tableName
     */
    public static void createPojo(List<Map<String, String>> columns,String tableName){
    	try {
    		String pojoName =  (tableName.subSequence(0, 1)+"").toUpperCase()+tableName.toLowerCase().substring(1);//实体类名称,首字母大写
    		pojoName = fieldToProperty(pojoName);  //实体类名称转换为驼峰法命名
    		String filePath = JavaPath.project_path +"\\src\\" + JavaPath.pojo_path.replace(".", "\\")+"\\"+pojoName+".java";
			context=new  VelocityContext();
			context.put("package", JavaPath.pojo_path);			//添加包名
			context.put("tableName", tableName);				//表名称
			context.put("model", pojoName);						//实体类名称
			context.put("columns", columns);
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			context.put("nowDate", dateFormat.format(new Date()));	//添加日期
			generateFile(context,Velocity.getTemplate(JavaPath.pojovm_path, "UTF-8"),filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     *  columnName, dataType, columnType
     * @param columns
     */
    public static void changeColumnsMysql(List<Map<String, String>> columns){
    	for(Map<String, String> column:columns){
    		column.put("dataType", mysqlTypeTojava(column.get("dataType")));
    		column.put("columnLength", mysqlLength(column.get("columnType")));
    		column.put("javaName", fieldToProperty(column.get("columnName")));
    	}
    }
    
    /**
     *  columnName, dataType
     * @param columns
     */
    public static void changeColumnsOracle(List<Map<String, String>> columns){
    	for(Map<String, String> column:columns){
    		column.put("dataType", oracleTypeTojava(column.get("dataType")));
    		column.put("columnLength", "0");	//oracle无法查询字段长度，设置0生成pojo不显示length
    		column.put("javaName", fieldToProperty(column.get("columnName")));
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
    
    //连接mysql数据库获取表字段
    public static List<Map<String, String>> findTableMysql(String tableName){
    	//声明Connection对象
        Connection con;
        List<Map<String, String>> columns = new ArrayList<Map<String, String>>();
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select COLUMN_NAME,DATA_TYPE,COLUMN_TYPE,COLUMN_COMMENT from INFORMATION_SCHEMA.Columns where table_name='"+tableName+"'";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
            	Map<String, String> column = new HashMap<String, String>();
            	column.put("columnName", rs.getString("COLUMN_NAME"));
            	column.put("dataType", rs.getString("DATA_TYPE"));
            	column.put("columnType", rs.getString("COLUMN_TYPE"));
            	column.put("columnComment", rs.getString("COLUMN_COMMENT"));
            	columns.add(column);
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can't find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            e.printStackTrace();
        }finally{
        	System.out.println("数据库数据成功获取:"+tableName);
        }
        System.out.println(columns.toString());
    	return columns;
    }
    
    //连接oracle数据库获取表字段
    public static List<Map<String, String>> findTableOracle(String tableName){
    	//声明Connection对象
        Connection con;
        //驱动程序名
        List<Map<String, String>> columns = new ArrayList<Map<String, String>>();
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select COLUMN_NAME columnName,DATA_TYPE dataType from user_tab_columns WHERE TABLE_NAME ='"+tableName+"'";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
            	Map<String, String> column = new HashMap<String, String>();
            	column.put("columnName", rs.getString("columnName"));
            	column.put("dataType", rs.getString("dataType"));
            	columns.add(column);
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can't find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取:"+tableName);
        }
        System.out.println(columns.toString());
    	return columns;
    }
    
    /**
     * 将mysql数据类型转换为java数据类型
     * @param mysqlType
     * @return
     */
    public static String mysqlTypeTojava(String mysqlType){
    	
    	String javaType = "String";
    	if(mysqlType.equals("varchar")){
    		javaType = "String";
    	}else if(mysqlType.equals("char")){
    		javaType = "String";
    	}else if(mysqlType.equals("blob")){
    		javaType = "byte[]";
    	}else if(mysqlType.equals("float")){
    		javaType = "Float";
    	}else if(mysqlType.equals("int")){
    		javaType = "Integer";
    	}else if(mysqlType.equals("double")){
    		javaType = "Double";
    	}else if(mysqlType.equals("decimal")){
    		javaType = "BigDecimal";
    	}else if(mysqlType.equals("tinyint")){
    		javaType = "Integer";
    	}else if(mysqlType.equals("datetime")){
    		javaType = "Date";
    	}else if(mysqlType.equals("date")){
    		javaType = "Date";
    	}else if(mysqlType.equals("time")){
    		javaType = "Time";
    	}else if(mysqlType.equals("year")){
    		javaType = "Date";
    	}else {
    		javaType = "Strings";
    	}
    	return javaType;
    }
    
    /**
     * 将mysql数据类型转换为java数据类型
     * @param mysqlType
     * @return
     */
    public static String oracleTypeTojava(String oracleType){
    	
    	String javaType = "String";
    	if(oracleType.equals("CHAR")){
    		javaType = "String";
    	}else if(oracleType.equals("VARCHAR")){
    		javaType = "String";
    	}else if(oracleType.equals("VARCHAR2")){
    		javaType = "String";
    	}else if(oracleType.equals("NUMBER")){
    		javaType = "Integer";
    	}else if(oracleType.equals("FLOAT")){
    		javaType = "Float";
    	}else if(oracleType.equals("DATE")){
    		javaType = "Date";
    	}else {
    		javaType = "Strings";
    	}
    	return javaType;
    }
    
    /**
     * 获取数据类型长度
     * @param mysqlType float(5,2)/float(2)/datetime
     * @return 0表示无长度
     */
    public static String mysqlLength(String mysqlType){
    	
    	String length = "0";
    	if(mysqlType.contains("(")&&mysqlType.contains(")")){	//包含长度
    		length = mysqlType.substring(mysqlType.indexOf("(")+1, mysqlType.indexOf(")"));
    		if(length.contains(",")){		//包含小数
    			length = length.split(",")[0];
    		}
    	}
    	return length;
    }
    
    /** 
     * 字段转换成对象属性 例如：user_name to userName 
     * @param field 
     * @return 
     */  
    public static String fieldToProperty(String field) {  
        if (null == field) {
            return "";
        }  
        char[] chars = field.toCharArray();  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < chars.length; i++) {  
            char c = chars[i];  
            if (c == '_') {  
                int j = i + 1;  
                if (j < chars.length) {  
                    sb.append((chars[j]+"").toUpperCase());  
                    i++;  
                }  
            } else {  
                sb.append(c);  
            }  
        }  
        return sb.toString();  
    }
    
    /** 
     * 对象属性转换为字段  例如：userName to user_name 
     * @param property 字段名 
     * @return 
     */  
    public static String propertyToField(String property) {  
        if (null == property) {  
            return "";  
        }  
        char[] chars = property.toCharArray();  
        StringBuffer sb = new StringBuffer();  
        for (char c : chars) {  
            if (CharUtils.isAsciiAlphaUpper(c)) {  
                sb.append("_" + CharUtils.toString(c).toLowerCase());  
            } else {  
                sb.append(c);  
            }  
        }  
        return sb.toString();  
    }

}