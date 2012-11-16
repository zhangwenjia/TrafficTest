package com.zhangwenjia.traffictest.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.zhangwenjia.traffictest.bean.ChoiseQustionBean;
import com.zhangwenjia.traffictest.db.TrafficDataBaseAdapter;

public class Util {

	public static void excelDataToDB(Context context) {
		ArrayList<ChoiseQustionBean> list = new ArrayList<ChoiseQustionBean>();
		try {
			Workbook workbook = Workbook.getWorkbook(new File(Environment.getExternalStorageDirectory() + "/" + "zhangwenjia.xls"));
			Sheet sheet = workbook.getSheet(0);

			for (int i = 1; i <= 4; i++) { // ÐÐ
				ChoiseQustionBean choiseBean = new ChoiseQustionBean();
				for (int j = 0; j <= 14; j++) { // ÁÐ
					Cell cellTemp = sheet.getCell(j, i);
					switch (j) {
					case 0:
						choiseBean.number = cellTemp.getContents();
						Log.d("mylog", "choiseBean.number is:" + choiseBean.number);
						break;
					case 1:
						choiseBean.chapter = cellTemp.getContents();
						Log.d("mylog", "chapter is:" + choiseBean.chapter);
						break;
					case 2:
						choiseBean.part = cellTemp.getContents();
						Log.d("mylog", "part is:" + choiseBean.part);
						break;
					case 3:
						choiseBean.question_mode = cellTemp.getContents();
						Log.d("mylog", "question_mode is:" + choiseBean.question_mode);
						break;
					case 4:
						choiseBean.question_content = cellTemp.getContents();
						Log.d("mylog", "question_content is:" + choiseBean.question_content);
						break;
					case 5:
						choiseBean.picture_name = cellTemp.getContents();
						Log.d("mylog", "picture_name is:" + choiseBean.picture_name);
						break;
					case 6:
						choiseBean.picture_path = cellTemp.getContents();
						Log.d("mylog", "picture_path is:" + choiseBean.picture_path);
						break;
					case 7:
						choiseBean.a = cellTemp.getContents();
						Log.d("mylog", "a is:" + choiseBean.a);
						break;
					case 8:
						choiseBean.b = cellTemp.getContents();
						Log.d("mylog", "b is:" + choiseBean.b);
						break;
					case 9:
						choiseBean.c = cellTemp.getContents();
						Log.d("mylog", "c is:" + choiseBean.c);
						break;
					case 10:
						choiseBean.d = cellTemp.getContents();
						Log.d("mylog", "d is:" + choiseBean.d);
						break;
					case 11:
						choiseBean.correct_answer_4choise = cellTemp.getContents();
						Log.d("mylog", "correct_answer_4choise is:" + choiseBean.correct_answer_4choise);
						break;
					case 12:
						choiseBean.answer_analysis = cellTemp.getContents();
						Log.d("mylog", "answer_analysis is:" + choiseBean.answer_analysis);
						break;
					case 13:
						choiseBean.user_answer_4choise = cellTemp.getContents();
						Log.d("mylog", "user_answer_4choise is:" + choiseBean.user_answer_4choise);
						break;
					case 14:
						choiseBean.user_result = cellTemp.getContents();
						Log.d("mylog", "user_result is:" + choiseBean.user_result);
						break;
					}
				}
				list.add(choiseBean);

			}

			workbook.close();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		TrafficDataBaseAdapter.getInstance(context).insertOptionChoiseQustionList(list);
	}

}
