package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static void main(String[] args) throws IOException {

		XSSFWorkbook wbook = new XSSFWorkbook("./data/TC001.xlsx");
		XSSFSheet sheet = wbook.getSheet("TestLeaf");

		int lastRowNum = sheet.getLastRowNum();
		System.out.println(lastRowNum + " Row Count");
		int cellCount = sheet.getRow(0).getLastCellNum();
		System.out.println(cellCount + " Column Count");

		for (int i = 1; i <= lastRowNum; i++) {
			XSSFRow row = sheet.getRow(i);

			for (int j = 0; j < cellCount; j++) {
				XSSFCell cell = row.getCell(1);
				String text = cell.getStringCellValue();
				System.out.println(text);
			}
		}
	}

}
