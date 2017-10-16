package com.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.connnectDataBase.ConnectMySql;


public class ExportExcel {
	public static void main(String[] args) throws Throwable {
		Date startdate = new Date();
		Connection conn = ConnectMySql.getConnnection();
		System.out.println(startdate);
		// 创建在内存中存放的行数，每次读取到最大行数时都会将数据flush到硬盘。本例子中设置为100；
		SXSSFWorkbook wb = new SXSSFWorkbook(100);
		// 创建一个sheet页
		SXSSFSheet sh = wb.createSheet();
		String sql = "select * from test01";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
  	ResultSet resultSet = prepareStatement.executeQuery();
  	ResultSetMetaData rsmd = resultSet.getMetaData();
  	int columnCount = rsmd.getColumnCount();
  	System.out.println(columnCount);
  	while(resultSet.next()){
  		String string = resultSet.getString(1);
  		System.out.println(string);
  	}
		for (int rownum = 0; rownum < 1000; rownum++) {
			// 在Excel中创建新的一行
			Row row = sh.createRow(rownum);
			// 循环每一行的列，这里一行有10个列
			for (int cellnum = 0; cellnum < 10; cellnum++) {
				// 创建一个新的单元格
				Cell cell = row.createCell(cellnum);
				String address = new CellReference(cell).formatAsString();
				
				//System.out.println(address);
				cell.setCellValue(address);
			}
		}
    
    FileOutputStream out = null;
		try {
			out = new FileOutputStream("C:\\Users\\guoqing\\Desktop\\sxssf.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    try {
			wb.write(out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    // dispose of temporary files backing this workbook on disk
    wb.dispose();
    Date enDate = new Date();
		System.out.println(enDate);
	}
}
