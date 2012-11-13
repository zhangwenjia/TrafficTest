package com.zhangwenjia.traffictest;

import com.zhangwenjia.traffictest.bean.ChoiseQustionBean;
import com.zhangwenjia.traffictest.bean.TrueOrFalseQustionBean;
import com.zhangwenjia.traffictest.db.TrafficDataBaseAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ChoiseQustionBean cq = new ChoiseQustionBean();
        cq.number="1020501";
		cq.chapter="8";
		cq.part="2";
		cq.question_mode="0";
		cq.question_content="高速公路没有限速标志的，最高时速不得超过——。";
		cq.picture_name="1020501";
		cq.picture_path="";
		cq.a="90公里";
		cq.b="100公里";
		cq.c="120公里";
		cq.d="130公里";
		cq.correct_answer_4choise=3;
		cq.answer_analysis="1.在高速公路所有车辆都不能超过时速120公里；\n2.在加速车道时时速不低于60公里；\n3.货车在高速公路不能高于时速100公里。";
        
        TrafficDataBaseAdapter.getInstance(this).insertOptionChoiseQuestion(cq);
        
        
        TrueOrFalseQustionBean tofq = new TrueOrFalseQustionBean();
        tofq.number="2050901";
		tofq.chapter="1";
		tofq.part="3";
		tofq.question_mode="1";
		tofq.question_content="已达到国家强制报废标准的机动车，必须报废。机动车所有人向机动车回收企业交售车辆时应同时提交机动车登记证书、好牌和行驶证。";
		tofq.picture_name="2050901";
		tofq.picture_path="";
		tofq.correct_answer_4truefalse=1;
		tofq.answer_analysis="除取得《报废汽车回收企业资格证书》的企业外，任何单位和个人不得从事汽车回收活动。";
        
        
        TrafficDataBaseAdapter.getInstance(this).insertTrueOrFalseQuestion(tofq);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
