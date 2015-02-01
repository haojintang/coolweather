package com.example.coolweather.db;

import java.util.ArrayList;
import java.util.List;

import com.example.coolweather.model.City;
import com.example.coolweather.model.County;
import com.example.coolweather.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {
	private static CoolWeatherDB coolWeatherDB=null;
	private final String DB_NAME="cool_weather";
	private final int DB_VERSION=1;
	private SQLiteDatabase db=null;
	private CoolWeatherDB(Context context){
		CoolWeatherOpenHelper dbHelper=new CoolWeatherOpenHelper(context,DB_NAME,null,DB_VERSION);
		db=dbHelper.getWritableDatabase();
	}
	public synchronized static CoolWeatherDB getInstance(Context context){
		if(coolWeatherDB==null){
			coolWeatherDB=new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}
	
	/*
	 * 将province实例存到数据库
	 */
	public void saveProvince(Province province){
		if(province!=null){
			ContentValues contentValues=new ContentValues();
			contentValues.put("province_name",province.getProvinceName());
			contentValues.put("province_code",province.getProvinceCode());
			db.insert("Province", null, contentValues);
		}
	}
	/*
	 * 获取province全部信息
	 */
	public List<Province> loadProvince(){
		List<Province> list=new ArrayList<Province>();
		Cursor cursor=db.query("Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Province tem=new Province();
				tem.setId(cursor.getInt(cursor.getColumnIndex("id")));
				tem.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				tem.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(tem);
			}while(cursor.moveToNext());
			
		}
		return list;
	}
	/*
	 * 将city的信息存储到数据库
	 */
	public void saveCity(City city){
		if(city!=null){
			ContentValues values=new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert("City", null, values);
		}
	}
	/*
	 * 获取City全部信息
	 */
	public List<City> loadCity(int provinceId){
		List<City> list=new ArrayList<City>();
		Cursor cursor = db.query("City", null, "province_id = ?",
				new String[] { String.valueOf(provinceId) }, null, null, null);
		if(cursor.moveToFirst()){
			do{
				City tem=new City();
				tem.setId(cursor.getInt(cursor.getColumnIndex("id")));
				tem.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				tem.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				tem.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));
				list.add(tem);
			}while(cursor.moveToNext());
			
		}
		return list;
	}
	/*
	 * 将city的信息存储到数据库
	 */
	public void saveCounty(County county){
		if(county!=null){
			ContentValues values=new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert("County", null, values);
		}
	}
	/*
	 * 获取City全部信息
	 */
	public List<County> loadCounty(int cityId){
		List<County> list=new ArrayList<County>();
		Cursor cursor = db.query("County", null, "city_id = ?",
				new String[] { String.valueOf(cityId) }, null, null, null);
		if(cursor.moveToFirst()){
			do{
				County tem=new County();
				tem.setId(cursor.getInt(cursor.getColumnIndex("id")));
				tem.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
				tem.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
				tem.setCityId(cursor.getInt(cursor.getColumnIndex("city_id")));
				list.add(tem);
			}while(cursor.moveToNext());
			
		}
		return list;
	}
}
