package com.zhangwenjia.traffictest.ui;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import android.os.Environment;
import android.util.Log;

public class ExcelDataToDB {

	public void excelDataToDB() {
		try {
			Workbook workbook = Workbook.getWorkbook(new File(Environment.getExternalStorageDirectory() + "/" + "zhangwenjia.xls"));
			Sheet sheet = workbook.getSheet(0);
			Cell a1 = sheet.getCell(0, 0);
			Cell b2 = sheet.getCell(1, 1);
			Cell c2 = sheet.getCell(2, 1);

			String stringa1 = a1.getContents();
			String stringb2 = b2.getContents();
			String stringc2 = c2.getContents();

			Log.d("mylog", "0,0 is:" + stringa1);
			Log.d("mylog", "1,1 is:" + stringb2);
			Log.d("mylog", "2,1 is:" + stringc2);

			workbook.close();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
