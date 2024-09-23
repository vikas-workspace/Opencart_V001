package com.utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	
	String path;
	
	public ExcelUtility(String path)
	{
		this.path=path;
	}
	
	
	public int getRowCount(String sheetName) throws IOException
	{
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet= workbook.getSheet(sheetName);
		
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rownum) throws IOException
	{
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet= workbook.getSheet(sheetName);
		
		row=sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
		
	}
	
	
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException
	{
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet= workbook.getSheet(sheetName);
		row= sheet.getRow(rownum);
		cell= row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		
		try 
		{
		  data =formatter.formatCellValue(cell); // Return the formatted value of a cell as a String regardless
		} 
		catch (Exception e) 
		{
			data="";
		}
		
		workbook.close();
		fi.close();
		return data;
	}
	
	
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException
	{
		File xlfile= new File(path);
		
		if(!xlfile.exists())
		{
			workbook= new XSSFWorkbook();
			fo= new FileOutputStream(path);
			workbook.write(fo);
		}
		
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheetName)==-1) // If sheet not exist then create new sheet
		  workbook.createSheet(sheetName);
		  sheet=workbook.getSheet(sheetName);
		  
		if(sheet.getRow(rownum)==null) // If row not exists then create new row 
			sheet.createRow(rownum);
		row= sheet.getRow(rownum);
		
		cell= row.createCell(colnum);
		cell.setCellValue(data);
		fo= new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	public void fillGreenColour(String sheetName, int rownum, int colnum) throws IOException
	{
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet= workbook.getSheet(sheetName);
		
		row= sheet.getRow(rownum);
		cell= row.getCell(colnum);
		
		style= workbook.createCellStyle();
		
		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	
	
	public void fillRedColour(String sheetName, int rownum, int colnum) throws IOException
	{
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet= workbook.getSheet(sheetName);
		
		row= sheet.getRow(rownum);
		cell= row.getCell(colnum);
		
		style= workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	
	

}
