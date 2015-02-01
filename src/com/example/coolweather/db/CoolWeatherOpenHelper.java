package com.example.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
	private final String CREATE_PROVINCE="create table Province("
			+ "id integer primary key autoincrement,"
			+ "province_name text,"
			+ "province_code text)";
	private final String CREATE_CITY="create table City("
			+ "id integer primary key autoincrement,"
			+ "city_name text,"
			+ "city_code text,"
			+ "province_id integer)";
	private final String CREATE_COUNTY="create table County("
			+ "id integer primary key autoincrement,"
			+ "county_name text,"
			+ "county_code text,"
			+ "city_id integer)";
	public CoolWeatherOpenHelper(Context context, String name, CursorFactory
			factory, int version) {
			super(context, name, factory, version);
			}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(this.CREATE_CITY);
		db.execSQL(this.CREATE_COUNTY);
		db.execSQL(this.CREATE_PROVINCE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
