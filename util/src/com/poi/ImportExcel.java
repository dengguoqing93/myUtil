package com.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.eventusermodel.XLSX2CSV;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportExcel {

	public static void main(String[] args) throws IOException {
		Date startdate = new Date();
		System.out.println(startdate);
		FileInputStream in = null;
		try {
			in = new FileInputStream("C:\\Users\\guoqing\\Desktop\\sxssf.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFWorkbook wb = new XSSFWorkbook(in);
		//SXSSFWorkbook wb = new SXSSFWorkbook(wb1,200);
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sheet = wb.getSheetAt(i);
			int rows = sheet.getPhysicalNumberOfRows();
			System.out.println("该文档共有" + rows + "行");
			for (int j = 0; j < rows; j++) {
				Row row = sheet.getRow(j);
				if (row == null) {
					continue;
				}
				int cells = row.getPhysicalNumberOfCells();
				System.out.println("每行有"+cells+"个单元格");
				for (int k = 0; k < cells ; k++) {
					Cell cell = row.getCell(k);
					String value = cell.getStringCellValue();
					System.out.println(value);
				}
				
			}
		}
		wb.close();
		in.close();
		Date enddate = new Date();
		System.out.println(enddate);
	}
}
