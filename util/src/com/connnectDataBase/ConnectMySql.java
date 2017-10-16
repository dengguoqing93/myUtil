package com.connnectDataBase;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectMySql {
  public static Connection getConnnection() throws SQLException, IOException {
		Properties properties = new Properties();
		InputStream in = null;
		//读取文件的方式
	  //in = Files.newInputStream(Paths.get("G:\\git\\myUtil\\util\\src\\com\\connnectDataBase\\mysql1.properties"));
	  in = Files.newInputStream(Paths.get("\\mysql1.properties"));
	  //in = Object.class.getResourceAsStream("/mysql1.properties");
	  		//getClass().getResourceAsStream("mysql1.properties");
		properties.load(in);
		String drivers = properties.getProperty("jdbc.drivers");
		if (drivers!=null) System.setProperty("jdbc.drivers", drivers);
		String url = properties.getProperty("jdbc.url")+"?useSSL=false";
		String username =properties.getProperty("jdbc.username");
		String password =properties.getProperty("jbbc.password");
		return DriverManager.getConnection(url,username,password);
	}
  public static void main(String[] args) throws SQLException, IOException {
  	Connection connnection = ConnectMySql.getConnnection();
  	String sql = "select * from test01";
  	PreparedStatement prepareStatement = connnection.prepareStatement(sql);
  	ResultSet resultSet = prepareStatement.executeQuery();
  	while(resultSet.next()){
  		String string = resultSet.getString(1);
  		System.out.println(string);
  	}
	}
}
