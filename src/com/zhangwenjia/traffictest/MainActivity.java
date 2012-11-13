package com.zhangwenjia.traffictest;


import java.io.File;
import java.io.IOException;

import com.zhangwenjia.traffictest.bean.ChoiseQustionBean;
import com.zhangwenjia.traffictest.bean.TrueOrFalseQustionBean;
import com.zhangwenjia.traffictest.db.TrafficDataBaseAdapter;

import jxl.*;
import jxl.read.biff.BiffException;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*
        ChoiseQustionBean cq = new ChoiseQustionBean();
        cq.number="1020501";
		cq.chapter="8";
		cq.part="2";
		cq.question_mode="0";
		cq.question_content="���ٹ�·û�����ٱ�־�ģ����ʱ�ٲ��ó���������";
		cq.picture_name="1020501";
		cq.picture_path="";
		cq.a="90����";
		cq.b="100����";
		cq.c="120����";
		cq.d="130����";
		cq.correct_answer_4choise=3;
		cq.answer_analysis="1.�ڸ��ٹ�·���г��������ܳ���ʱ��120���\n2.�ڼ��ٳ���ʱʱ�ٲ�����60���\n3.�����ڸ��ٹ�·���ܸ���ʱ��100���";
        
        TrafficDataBaseAdapter.getInstance(this).insertOptionChoiseQuestion(cq);
        
        
        TrueOrFalseQustionBean tofq = new TrueOrFalseQustionBean();
        tofq.number="2050901";
		tofq.chapter="1";
		tofq.part="3";
		tofq.question_mode="1";
		tofq.question_content="�Ѵﵽ����ǿ�Ʊ��ϱ�׼�Ļ����������뱨�ϡ��������������������������ҵ���۳���ʱӦͬʱ�ύ�������Ǽ�֤�顢���ƺ���ʻ֤��";
		tofq.picture_name="2050901";
		tofq.picture_path="";
		tofq.correct_answer_4truefalse=1;
		tofq.answer_analysis="��ȡ�á���������������ҵ�ʸ�֤�顷����ҵ�⣬�κε�λ�͸��˲��ô����������ջ��";
        
        TrafficDataBaseAdapter.getInstance(this).insertTrueOrFalseQuestion(tofq);
        */
        
        
        // Environment.getExternalStorageDirectory() + "/vancl/apk/";
        try {
			Workbook workbook = Workbook.getWorkbook(new File(Environment.getExternalStorageDirectory()+"/"+"zhangwenjia.xls"));
			Sheet sheet = workbook.getSheet(0);
			Cell a1 = sheet.getCell(0,0); 
			Cell b2 = sheet.getCell(1,1); 
			Cell c2 = sheet.getCell(2,1); 

			String stringa1 = a1.getContents(); 
			String stringb2 = b2.getContents(); 
			String stringc2 = c2.getContents(); 
			
			
			Log.d("mylog","0,0 is:"+stringa1);
			Log.d("mylog","1,1 is:"+stringb2);
			Log.d("mylog","2,1 is:"+stringc2);
			
			workbook.close();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
