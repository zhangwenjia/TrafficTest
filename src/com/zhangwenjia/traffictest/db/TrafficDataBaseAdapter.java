package com.zhangwenjia.traffictest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zhangwenjia.traffictest.bean.ChoiseQustionBean;
import com.zhangwenjia.traffictest.bean.TrueOrFalseQustionBean;
import com.zhangwenjia.traffictest.bean.UserAnswer4Choise;
import com.zhangwenjia.traffictest.bean.UserAnswer4TrueOrFalse;

public class TrafficDataBaseAdapter {

	private Context context; // �����Ķ���
	private SQLiteDatabase db; // ���ݿ����
	private static final int DB_VERSION = 4; // ���ݿ�汾��
	private static TrafficDataBaseAdapter dbAdapter = null; // ���ݿ�����������

	/** ���ݿ��� */
	public static final String DB_NAME = "traffic_test.db";

	/** ѡ����� */
	public static final String TB_CHOICE_QUESTION = "table_choice_question";
	/** �ж���� */
	public static final String TB_TRUE_OR_FALSE = "table_true_or_false";

	/** -------------------����Ĺ����ֶ� -----------------*/
	/** ����Id */
	public static final String F_ID = "_id";
	/** ��ţ�ѡ����1******���ж�����2******��**|**|**�ֱ��ʾ�½���ţ� */
	public static final String F_NUMBER = "number";
	/** �ڼ��� */
	public static final String F_CHAPTER = "chapter";
	/** �ڼ��� */
	public static final String F_PART = "part";
	/** ��Ŀ���ͣ��жϣ�ѡ�� */
	public static final String F_QUESTION_MODE = "question_mode";
	/** ��Ŀ�������� */
	public static final String F_QUESTION_CONTENT = "question_content";
	/** ͼƬ�� */
	public static final String F_PICTURE_NAME = "picture_name";
	/** ͼƬ·�� */
	public static final String F_PICTURE_PATH = "picture_path";
	/** ������� */
	public static final String F_ANSWER_ANALYSIS = "answer_analysis";
	/** ������������ˣ����Ǵ��ˣ� */
	public static final String F_USER_RESULT = "user_result";

	/**----------------------------ѡ�����---------------------------*/
	/** ѡ������ȷ�� */
	public static final String F_CORRECT_ANSWER_4CHOISE = "correct_answer_4choise";
	/** ѡ�����û��� */
	public static final String F_USER_ANSWER_4CHOISE = "user_answer_4choise";
	/** ѡ�� */
	public static final String F_A = "a";
	public static final String F_B = "b";
	public static final String F_C = "c";
	public static final String F_D = "d";
	
	/**----------------------------�ж����---------------------------*/
	/** �ж���� */
	public static final String F_CORRECT_ANSWER_4TRUEFALSE = "correct_answer_4truefalse";
	/** �ж����û��� */
	public static final String F_USER_ANSWER_4TRUEFALSE = "user_answer_4truefalse";

	
	
	
	private TrafficDataBaseAdapter(Context context) {
		this.context = context;
	}

	private TrafficDataBaseAdapter openDataBase() {
		DataBaseHelper dbHelper = new DataBaseHelper(context);
		db = dbHelper.getWritableDatabase();
		return dbAdapter;
	}

	public synchronized static TrafficDataBaseAdapter getInstance(
			Context context) {
		if (null == dbAdapter) {
			dbAdapter = new TrafficDataBaseAdapter(context);
		}
		return dbAdapter.openDataBase();
	}

	public void closeDataBase() {
		if (null != db && db.isOpen()) {
			db.close();
		}
	}

	/*-----------------------------------------���ݿ�����----------------------------------------------*/
	/**
	 * ���ݿ�������
	 */
	private class DataBaseHelper extends SQLiteOpenHelper {

		public DataBaseHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = new String();
			// ����ѡ������
			sql = "create table " + TB_CHOICE_QUESTION 
					+ "(" 
					+ F_ID + " integer primary key AUTOINCREMENT," 
					+ F_NUMBER + " text,"
					+ F_CHAPTER + " text," 
					+ F_PART + " text," 
					+ F_QUESTION_MODE + " text," 
					+ F_QUESTION_CONTENT + " text NOT NULL,"
					+ F_PICTURE_NAME + " text," 
					+ F_PICTURE_PATH + " text,"
					+ F_A + " text NOT NULL,"
					+ F_B + " text NOT NULL,"
					+ F_C + " text NOT NULL,"
					+ F_D + " text NOT NULL,"
					+ F_CORRECT_ANSWER_4CHOISE + " text NOT NULL,"
					+ F_ANSWER_ANALYSIS + " text," 
					+ F_USER_ANSWER_4CHOISE + " text," 
					+ F_USER_RESULT + " boolean" 
					+ ")";
			db.execSQL(sql);

			// �����ж�����
			sql = "create table " + TB_TRUE_OR_FALSE 
					+ "(" 
					+ F_ID + " integer primary key AUTOINCREMENT," 
					+ F_NUMBER + " text,"
					+ F_CHAPTER + " text," 
					+ F_PART + " text," 
					+ F_QUESTION_MODE + " text," 
					+ F_QUESTION_CONTENT + " text NOT NULL,"
					+ F_PICTURE_NAME + " text," 
					+ F_PICTURE_PATH + " text,"
					+ F_CORRECT_ANSWER_4TRUEFALSE + " text NOT NULL,"
					+ F_ANSWER_ANALYSIS + " text," 
					+ F_USER_ANSWER_4TRUEFALSE + " text,"
					+ F_USER_RESULT + " boolean" 
					+ ")";
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists " + TB_CHOICE_QUESTION);
			db.execSQL("drop table if exists " + TB_TRUE_OR_FALSE);
            onCreate(db);
		}
	}

	/*-----------------------------------------��ɾ�Ĳ����----------------------------------------------*/
	
	/*---------------------------------------���ӡ��������ݲ���------------------------------------------*/
	/**
	 * ����һ��ѡ��������
	 */
	public void insertOptionChoiseQuestion(ChoiseQustionBean cq){
		ContentValues contentValues = new ContentValues();
		contentValues.put(F_NUMBER, cq.number);
		contentValues.put(F_CHAPTER, cq.chapter);
		contentValues.put(F_PART, cq.part);
		contentValues.put(F_QUESTION_MODE, cq.question_mode);
		contentValues.put(F_QUESTION_CONTENT, cq.question_content);
		contentValues.put(F_PICTURE_NAME, cq.picture_name);
		contentValues.put(F_PICTURE_PATH, cq.picture_path);
		contentValues.put(F_A, cq.a);
		contentValues.put(F_B, cq.b);
		contentValues.put(F_C, cq.c);
		contentValues.put(F_D, cq.d);
		contentValues.put(F_CORRECT_ANSWER_4CHOISE, cq.correct_answer_4choise);
		contentValues.put(F_ANSWER_ANALYSIS, cq.answer_analysis);
		// �������ݵĳ���ֻ����¼������ʱ���û���ѡ�����ڸ�����������ʱ
		
		db.insert(TB_CHOICE_QUESTION, null, contentValues);
		db.close();
	}
	
	/**
	 * ����һ���ж�������
	 * @param tofq
	 */
	public void insertTrueOrFalseQuestion(TrueOrFalseQustionBean tofq){
		ContentValues contentValues = new ContentValues();
		contentValues.put(F_NUMBER, tofq.number);
		contentValues.put(F_CHAPTER, tofq.chapter);
		contentValues.put(F_PART, tofq.part);
		contentValues.put(F_QUESTION_MODE, tofq.question_mode);
		contentValues.put(F_QUESTION_CONTENT, tofq.question_content);
		contentValues.put(F_PICTURE_NAME, tofq.picture_name);
		contentValues.put(F_PICTURE_PATH, tofq.picture_path);
		contentValues.put(F_CORRECT_ANSWER_4TRUEFALSE, tofq.correct_answer_4truefalse);
		contentValues.put(F_ANSWER_ANALYSIS, tofq.answer_analysis);
		
		db.insert(TB_TRUE_OR_FALSE, null, contentValues);
		db.close();
	}
	
	/*-----------------------------------------���£��޸Ĳ���----------------------------------------*/
	/**
	 * �޸����ڴ�����forѡ����
	 */
	public void userAnswerTheQuestion(UserAnswer4Choise userAnswer){
		ContentValues contentValues = new ContentValues();
		
        contentValues.put(F_USER_ANSWER_4CHOISE, userAnswer.user_answer_4choise);
        contentValues.put(F_USER_RESULT, userAnswer.user_result);
        String[] whereArgs = new String[] {userAnswer.number};
        db.update(TB_CHOICE_QUESTION, contentValues, F_NUMBER + "=?", whereArgs);
        db.close();
	}
	
	/**
	 * �޸����ڴ�����for�ж���
	 */
	public void userAnswerTheQuestion(UserAnswer4TrueOrFalse userAnswer){
		ContentValues contentValues = new ContentValues();
		
        contentValues.put(F_USER_ANSWER_4TRUEFALSE, userAnswer.user_answer_4truefalse);
        contentValues.put(F_USER_RESULT, userAnswer.user_result);
        String[] whereArgs = new String[] {userAnswer.number};
        db.update(TB_TRUE_OR_FALSE, contentValues, F_NUMBER + "=?", whereArgs);
        db.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*-----------------------------------------��ѯ����----------------------------------------------*/
	
	
	
	// ��������
	// ͬһ���°�������
	// 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
