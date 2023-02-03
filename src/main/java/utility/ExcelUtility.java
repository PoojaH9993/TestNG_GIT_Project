package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static Workbook wb;
	public static Sheet ws;
	public static Cell cell;
	public static Row row;
	public static CellStyle style;

	public static int getRowCount(String xlfile, String xlsheet) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}

	public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	public static String getCellData(String xlfile, String xlsheet, int rownum, int column) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(column);
		String data;

		try {
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);// Return the formatted value of a cell as a string
			// regardless of the cell type
			return cellData;

		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	public static void setCellData(String xlfile, String xlsheet, int rownum, int column, String data)
			throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.createCell(column); // it will create a new cell after last cell or after cell number = column
		cell.setCellValue(data);

		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		fo.flush();
		wb.close();
		fi.close();
		fo.close();
	}

	public static Object[][] getAllTestCasesFromSheet(String xlfile, String xlsheet,String sheetname) throws IOException{
		Object[][] tabArray=(Object[][])null;
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		if(xlsheet==null) {
			row = ws.getRow(0);	
			int startRowInt=0;
			int startColumnInt=1;
			int totalRowsInt=ws.getLastRowNum();
			int totalColumnsInt=row.getLastCellNum();
			tabArray=new Object[totalRowsInt][startRowInt+1];
			for(int rowIndex=startRowInt;rowIndex<totalRowsInt;rowIndex++) {
				Map<String, String> columnData=new HashMap();
				for(int columnIndex=startColumnInt;columnIndex<=totalColumnsInt;columnIndex++) {
					columnData.put(getCellData(xlfile, xlsheet, 0, columnIndex),getCellData(xlfile, xlsheet, rowIndex, columnIndex));
					columnData.put("CurrentRow",String.valueOf(rowIndex));
				}
				tabArray[rowIndex][0]=columnData;
			}
		}
		return tabArray; 
	}


//	public static Object[][] getAllMatchingTestCasesWithFilter(String sheetNameStr, String testCaseNameStr, String filterNameStr, String filtervalueStr) throws IOException{
//		String filePath;
//		Object[][] tabArray=(Object[][])null;
//		fi = new FileInputStream(filePath);
//		wb =  getXSSFWorkbook(sheetNameStr, fi);
//		ws = wb.getSheet(sheetNameStr);
//		row = ws.getRow(0);	
//		int startRowInt=0;
//		int startColumnInt=1;
//		int totalRowsInt=ws.getLastRowNum();
//		int totalColumnsInt=row.getLastCellNum();
//		tabArray=new Object[totalRowsInt][startRowInt+1];
//		for(int rowIndex=startRowInt;rowIndex<totalRowsInt;rowIndex++) {
//			Map<String, String> columnData=new HashMap();
//			for(int columnIndex=startColumnInt;columnIndex<=totalColumnsInt;columnIndex++) {
//				columnData.put(getCellData(xlfile, xlsheet, 0, columnIndex),getCellData(xlfile, xlsheet, rowIndex, columnIndex));
//				columnData.put("CurrentRow",String.valueOf(rowIndex));
//			}
//			tabArray[rowIndex][0]=columnData;
//		}
//		return tabArray; 
//	}

	private static XSSFWorkbook getXSSFWorkbook(String sheetNameStr, FileInputStream fi2) throws IOException {

		XSSFWorkbook workbook;
		workbook = new XSSFWorkbook(fi);
		return workbook;
	}

}
