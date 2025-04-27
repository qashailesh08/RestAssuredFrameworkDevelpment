package api.utilities;

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

public class XLUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workBook;
	public XSSFSheet sheet;
	public XSSFCell cell;
	public CellStyle style;
	public XSSFRow row;
	String path;

//   This constructor initializes the XLUtility class by setting the Excel file path to the provided value.
	public XLUtility(String path) {
		this.path = path;
	}

	/*
	 * The getRowCount method returns the index of the last row (i.e., total number
	 * of rows - 1) in the specified Excel sheet.
	 */
	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workBook.close();
		fi.close();
		return rowcount;
	}

	/*
	 * The getCellCount method returns the total number of cells (columns) in a
	 * specific row of an Excel sheet.
	 */
	public int getCellCount(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		workBook.close();
		fi.close();
		return cellCount;
	}
	/*
	 * The getCellData method reads and returns the value of a specific cell from an
	 * Excel sheet as a formatted string, handling any exceptions by returning an
	 * empty string.
	 * 
	 */

	public String getCellData(String sheetName, int rownum, int column) throws IOException {
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(column);
		DataFormatter formatter = new DataFormatter();
		String data;

		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		workBook.close();
		fi.close();
		return data;

	}
	/*
	 * The setCellData method checks if an Excel file and sheet exist; if not, it
	 * creates them, then sets the given data in a specific cell (row and column),
	 * and saves the file using Apache POI.
	 */

	public void setCellData(String sheetName, int rownum, int column, String data) throws IOException {
		File xlFile = new File(path);
		if (!xlFile.exists()) { // create q new file if not exits.
			workBook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workBook.write(fo);

		}

		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);

		if (workBook.getSheetIndex(sheet) == -1) { // if sheet not exits then create a new sheet
			workBook.createSheet(sheetName);
			sheet = workBook.getSheet(sheetName);
		}

		if (sheet.getRow(rownum) == null) // if row not exits create new row
			sheet.createRow(rownum);
		row = sheet.getRow(rownum);

		cell = row.createCell(column);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workBook.write(fo);
		workBook.close();
		fi.close();
		fo.close();

	}

	/*
	 * The fillGreenColor method opens an Excel file, accesses a specific sheet,
	 * row, and cell, creates a green background cell style using Apache POI,
	 * applies it to the cell, saves the changes, and closes the file streams.
	 */
	public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);

		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		style = workBook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workBook.write(fo);
		workBook.close();
		fi.close();
		fo.close();
	}

	/*
	 * The fillRedColor(String sheetName, int rownum, int colnum) method opens an
	 * Excel .xlsx file, finds the specified sheet, row, and cell, creates a red
	 * cell style using Apache POI, applies that red background to the target cell,
	 * and then saves the updated file. It uses FileInputStream to read the file,
	 * XSSFWorkbook to manipulate it, sets the fill color using IndexedColors.RED,
	 * and ensures the style is applied with FillPatternType.SOLID_FOREGROUND.
	 * Finally, it writes the changes and closes the file streams.
	 */
	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		style = workBook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workBook.write(fo);
		workBook.close();
		fi.close();
		fo.close();
	}

}
