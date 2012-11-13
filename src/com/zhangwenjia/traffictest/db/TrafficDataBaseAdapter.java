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

	private Context context; // 上下文对象
	private SQLiteDatabase db; // 数据库对象
	private static final int DB_VERSION = 4; // 数据库版本号
	private static TrafficDataBaseAdapter dbAdapter = null; // 数据库适配器对象

	/** 数据库名 */
	public static final String DB_NAME = "traffic_test.db";

	/** 选择题表 */
	public static final String TB_CHOICE_QUESTION = "table_choice_question";
	/** 判断题表 */
	public static final String TB_TRUE_OR_FALSE = "table_true_or_false";

	/** -------------------各表的公共字段 -----------------*/
	/** 主键Id */
	public static final String F_ID = "_id";
	/** 题号（选择题1******，判断题是2******，**|**|**分别表示章节序号） */
	public static final String F_NUMBER = "number";
	/** 第几张 */
	public static final String F_CHAPTER = "chapter";
	/** 第几节 */
	public static final String F_PART = "part";
	/** 题目类型（判断，选择） */
	public static final String F_QUESTION_MODE = "question_mode";
	/** 题目主干内容 */
	public static final String F_QUESTION_CONTENT = "question_content";
	/** 图片名 */
	public static final String F_PICTURE_NAME = "picture_name";
	/** 图片路径 */
	public static final String F_PICTURE_PATH = "picture_path";
	/** 试题分析 */
	public static final String F_ANSWER_ANALYSIS = "answer_analysis";
	/** 试题结果（答对了，还是错了） */
	public static final String F_USER_RESULT = "user_result";

	/**----------------------------选择题表---------------------------*/
	/** 选择题正确答案 */
	public static final String F_CORRECT_ANSWER_4CHOISE = "correct_answer_4choise";
	/** 选择题用户答案 */
	public static final String F_USER_ANSWER_4CHOISE = "user_answer_4choise";
	/** 选项 */
	public static final String F_A = "a";
	public static final String F_B = "b";
	public static final String F_C = "c";
	public static final String F_D = "d";
	
	/**----------------------------判断题表---------------------------*/
	/** 判断题答案 */
	public static final String F_CORRECT_ANSWER_4TRUEFALSE = "correct_answer_4truefalse";
	/** 判断题用户答案 */
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

	/*-----------------------------------------数据库助手----------------------------------------------*/
	/**
	 * 数据库助手类
	 */
	private class DataBaseHelper extends SQLiteOpenHelper {

		public DataBaseHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = new String();
			// 创建选择题库表
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

			// 创建判断题库表
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

	/*-----------------------------------------增删改查操作----------------------------------------------*/
	
	/*---------------------------------------增加、插入数据操作------------------------------------------*/
	/**
	 * 插入一条选择题数据
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
		// 插入数据的场景只是在录入数据时，用户的选择是在更改这条数据时
		
		db.insert(TB_CHOICE_QUESTION, null, contentValues);
		db.close();
	}
	
	/**
	 * 插入一条判断题数据
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
	
	/*-----------------------------------------更新，修改操作----------------------------------------*/
	/**
	 * 修改用于答题结果for选择题
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
	 * 修改用于答题结果for判断题
	 */
	public void userAnswerTheQuestion(UserAnswer4TrueOrFalse userAnswer){
		ContentValues contentValues = new ContentValues();
		
        contentValues.put(F_USER_ANSWER_4TRUEFALSE, userAnswer.user_answer_4truefalse);
        contentValues.put(F_USER_RESULT, userAnswer.user_result);
        String[] whereArgs = new String[] {userAnswer.number};
        db.update(TB_TRUE_OR_FALSE, contentValues, F_NUMBER + "=?", whereArgs);
        db.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*-----------------------------------------查询操作----------------------------------------------*/
	
	
	
	// 按章搜索
	// 同一章下按节搜索
	// 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
