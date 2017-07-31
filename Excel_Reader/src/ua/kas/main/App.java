package ua.kas.main;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class App {

	public static void main(String[] args) throws Exception {
		InputStream in = new FileInputStream("test.xls");
		HSSFWorkbook wb = new HSSFWorkbook(in);

		Sheet sheet = wb.getSheetAt(0);
		Iterator<Row> it = sheet.iterator();
		while (it.hasNext()) {
			Row row = it.next();
			Iterator<Cell> cells = row.iterator();
			while (cells.hasNext()) {
				Cell cell = cells.next();

				try {
					System.out.print(cell.getNumericCellValue() + " ");
				} catch (Exception e) {
					System.out.print(cell.getStringCellValue());
				}

			}
			System.out.println();
		}
		wb.close();
	}
}