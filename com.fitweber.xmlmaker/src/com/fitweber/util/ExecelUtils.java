package com.fitweber.util;

import java.io.File;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
/**
 * 
 * <pre>
 * 程序的中文名称。
 * </pre>
 * @author wheatmark  hajima11@163.com
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class ExecelUtils {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList readExecelSimple(String execelName) {
		ArrayList result = new ArrayList();
		try {
			Workbook book = Workbook.getWorkbook(new File(execelName));

			Sheet sheet = book.getSheet(0);
			int rowNum = sheet.getRows();

			for (int i = 1; i < rowNum; i++) {
				Cell[] row = sheet.getRow(i);
				if ((row[1].getContents() != null)
						&& (!"".equals(row[1].getContents()))) {
					int rowLen = row.length;
					ArrayList rowContents = new ArrayList();
					for (int j = 0; j < rowLen; j++) {
						String temp = row[j].getContents();
						rowContents.add(temp);
					}
					result.add(rowContents);
				}
			}
			System.out.println(result.toString());
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
