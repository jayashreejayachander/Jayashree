package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataExcel {
	static Object[][] data;

	public static Object[][] readData(String dataSheetName) {

		XSSFWorkbook wbook;
		try {
			wbook = new XSSFWorkbook("./data/"+dataSheetName+".xlsx");

			XSSFSheet sheet = wbook.getSheet("TestLeaf");

			int lastRowNum = sheet.getLastRowNum();
			System.out.println(lastRowNum + " Row Count");
			int cellCount = sheet.getRow(0).getLastCellNum();
			System.out.println(cellCount + " Column Count");

			data = new Object[lastRowNum][cellCount];
			for (int i = 1; i <= lastRowNum; i++) {
				XSSFRow row = sheet.getRow(i);

				for (int j = 0; j < cellCount; j++) {
					XSSFCell cell = row.getCell(j);
					data[i - 1][j] = cell.getStringCellValue();
				}
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		return data;
	}

}
