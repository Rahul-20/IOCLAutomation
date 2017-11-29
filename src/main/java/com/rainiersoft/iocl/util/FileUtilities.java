package com.rainiersoft.iocl.util;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileUtilities 
{
	public static void createExcelWorkBook(String sheetName,String reportType)
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetName);

		Object[][] datatypes = {
				{"CreationDate","TruckRegNum","FanNumber","Customer","InvoiceQty","FilledQty","StartTime","EndTime"},
				{"int", "Primitive", 2},
				{"float", "Primitive", 4},
				{"double", "Primitive", 8},
				{"char", "Primitive", 1},
				{"String", "Non-Primitive", "No fixed size"}
		};


	}

}
