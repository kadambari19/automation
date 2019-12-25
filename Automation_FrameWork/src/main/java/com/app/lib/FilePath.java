package com.app.lib;

/**
 * 
 * @author Kadambari Shastry
 *
 */
public interface FilePath {
	
	String dirPath = System.getProperty("user.dir");
	String propPath = dirPath+"./src/main/resources/data.properties";
	String excelPath = dirPath+"./src/main/resources/excel/Data.xlsx";
	

}
