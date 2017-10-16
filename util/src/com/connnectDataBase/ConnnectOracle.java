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

public class ConnnectOracle {
	public static Connection getConnnection() throws SQLException, IOException {
		Properties properties = new Properties();
		InputStream in = null;
	  in = Files.newInputStream(Paths.get("E:\\workSpace\\learn\\src\\oracle.properties"));
		properties.load(in);
		String drivers = properties.getProperty("jdbc.drivers");
		try {
			Class.forName(drivers);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("jdbc.url");
		String username =properties.getProperty("jdbc.username");
		String password =properties.getProperty("jbbc.password");
		return DriverManager.getConnection(url,username,password);
	}
  public static void main(String[] args) throws SQLException, IOException {
  	Connection connnection = ConnnectOracle.getConnnection();
  	String sql = "select * from JOBS";
  	PreparedStatement prepareStatement = connnection.prepareStatement(sql);
  	ResultSet resultSet = prepareStatement.executeQuery();
  	while(resultSet.next()){
  		String string = resultSet.getString(1);
  		System.out.println(string);
  	}
	}
}
