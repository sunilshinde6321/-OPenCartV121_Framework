package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class ExcelUtlity 
{  
	public FileInputStream FI;
	public FileOutputStream FO;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public XSSFCellStyle style;
	String path;

	public ExcelUtlity(String path) 
	{
		this.path = path;

	}
	public int getRowCount(String sheetName ) throws IOException 
	{
		FI = new FileInputStream(path);
		workbook = new XSSFWorkbook(FI);
		sheet= workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		FI.close();
		return rowcount;
	}

	public int getCellCount(String sheetName , int rownum) throws IOException 
	{
		FI = new FileInputStream(path);
		workbook = new XSSFWorkbook(FI);
		sheet= workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		FI.close();
		return cellcount;
	}
	public String getcellData(String sheetName ,int rownum , int colnum) throws IOException
	{
		FI = new FileInputStream(path);
		workbook = new XSSFWorkbook(FI);
		sheet= workbook.getSheet(sheetName);
		row= sheet.getRow(rownum);
		cell= row.getCell(colnum);

		DataFormatter  formatter  = new DataFormatter();
		String data;
		try
		{
			data = formatter.formatCellValue(cell);

		}
		catch (Exception e) 
		{
			data = " ";

		}
		workbook.close();
		FI.close();
		return data;	
	}

	public void setCellData(String SheetName, int rownum , int colnum, String data) throws IOException
	{
		File xlfile = new 	File(path);
		if(!xlfile.exists())
		{
			workbook = new XSSFWorkbook();
			FO= new FileOutputStream(path);
			workbook.write(FO);

		}
		FI= new FileInputStream(path);
		workbook = new XSSFWorkbook(FI);

		if(workbook.getSheetIndex(SheetName)==-1)
		{
		workbook.createSheet(SheetName);
		}
		sheet.getWorkbook().getSheet(SheetName);


		if(sheet.getRow(rownum) != null);
		sheet.createRow(rownum);
		row=sheet.getRow(rownum);

		cell = row.createCell(colnum);
		cell.setCellValue(data);
		FO= new FileOutputStream(path);
		workbook.write(FO);
		workbook.close();
		FI.close();
		FO.close();

	}
	public void FillGreenColor(String SheetName, int rownum , int colnum ) throws IOException 
	{
		 FI= new FileInputStream(path);
		 workbook = new XSSFWorkbook(FI);
		 sheet= workbook.getSheet(SheetName);
		 
		 row = sheet.getRow(rownum);
		 cell = row.getCell(colnum);
		 
		 style = workbook.createCellStyle();
		 
		 style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		 style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		 cell.setCellStyle(style);
		 FO= new FileOutputStream(path);
		 workbook.write(FO);
		 workbook.close();
		 FI.close();
		 FO.close();
	}

	public void FillRedColor(String SheetName, int rownum , int colnum ) throws IOException
	{

		 FI= new FileInputStream(path);
		 workbook = new XSSFWorkbook(FI);
		 sheet= workbook.getSheet(SheetName);
		 
		 row = sheet.getRow(rownum);
		 cell = row.getCell(colnum);
		 
		 style = workbook.createCellStyle();
		 
		 style.setFillForegroundColor(IndexedColors.RED.getIndex());
		 style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		 cell.setCellStyle(style);
		 FO= new FileOutputStream(path);
		 workbook.write(FO);
		 workbook.close();
		 FI.close();
		 FO.close();
	}
	
}
