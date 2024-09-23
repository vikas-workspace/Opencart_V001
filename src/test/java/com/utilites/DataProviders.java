package com.utilites;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path =".\\testData\\OpenCart_Logindata.xlsx";// taking excel file from testData
		
		ExcelUtility xlutil= new ExcelUtility(path);// creating an object for excel utility
		
		int totalrows = xlutil.getRowCount("sheet1");
		int totalcols = xlutil.getCellCount("sheet1", 1);
		
		String logindata[][]= new String [totalrows][totalcols];//Created for two dimensional array which can store data
		for(int i=1;i<=totalrows;i++)//1 // read the data from excel storing in two dimensional array
		{
			for(int j=0;j<totalcols;j++)//0 i is row j is col
			{
				logindata[i-1][j]=xlutil.getCellData("sheet1", i, j);//1,0
			}
		}
		return logindata;// Returning two dimensional array
	}
	
	// DataProvidr 2
	
	
	// DataProvidr 3
	
	
	// DataProvidr 4

}
