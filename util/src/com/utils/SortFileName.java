package com.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SortFileName {
	public static Map<String, String> fileNameMap = new HashMap<String, String>();
	private String filePath;

	public SortFileName(String filePath) {
		this.filePath = filePath;
	}

	public void getFileName() {
		ifExistPrintFileDelete();
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println(filePath + "²»´æÔÚ£¡");
			return;
		}
		putFileNameToFileNameMap(file);
	}

	private void ifExistPrintFileDelete() {
		String fileName01 = filePath + "/Ö´ÐÐË³Ðò.txt";
		File file2 = new File(fileName01);
		if (file2.exists()) {
			file2.delete();
		}
	}

	private void putFileNameToFileNameMap(File file) {
		if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File file2 : listFiles) {
				putFileNameToFileNameMap(file2);
			}
		}else{
			String name = file.getName();
			String[] split = name.split("\\.");
			String substring = split[0].substring(split[0].length() - 2, split[0].length());
			fileNameMap.put(substring, "@ " + filePath + "/" + name);
		}
	/*	File fnFile[] = file.listFiles();
		for (int i = 0; i < fnFile.length; i++) {
			if (fnFile[i].isDirectory()) {
				File[] listFiles = fnFile[i].listFiles();
				for (int j = 0; j < listFiles.length; j++) {
					
				}
			}
			String fileName = fnFile[i].getName();
			String[] split = fileName.split("\\.");
			String substring = split[0].substring(split[0].length() - 2, split[0].length());
			fileNameMap.put(substring, "@ " + filePath + "/" + fileName);
		}*/
	}

	public void sortFileNamePrint() {
		String fileName = filePath + "/Ö´ÐÐË³Ðò.txt";
		File file = createFile(fileName);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file, "UTF-8");
			Set<String> keySet = fileNameMap.keySet();
			List<String> keylist = new ArrayList<String>();
			for (String string : keySet) {
				keylist.add(string);
			}
			Collections.sort(keylist);
			for (String string : keylist) {
				writer.println(fileNameMap.get(string));
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	private File createFile(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	public static void main(String[] args) {
		SortFileName sortFileName = new SortFileName("C:/Users/Administrator/Desktop/test");
		sortFileName.getFileName();
		sortFileName.sortFileNamePrint();
	}
}
