package com.medicinesearch.database;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

import com.medicinesearch.entity.UserEntity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper
{

	public static final String DB_NAME = "medicinesearch.db";

	public static final Class<?>[] Tables = new Class<?>[]
	{ UserEntity.class };

	// private static final String TBL_NAME = "CollTbl";

	// private static final String CREATE_TBL = " create table "
	// +
	// " CollTbl(_id integer primary key autoincrement,name text,url text, desc text) ";

	private SQLiteDatabase db;

	public DatabaseOpenHelper(Context c)
	{
		super(c, DB_NAME, null, 2);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		for (Class<?> tclass : Tables)
		{
			createTable(tclass, db);
		}
	}
	public void updata(String tablename, ContentValues values,String selection,String[] whereArgs){
		SQLiteDatabase db = getWritableDatabase();
		db.update(tablename, values,selection, whereArgs);
		db.close();
	}

	public void insert(String tableName, ContentValues values)
	{
		SQLiteDatabase db = getWritableDatabase();
		System.out.println(db.insert(tableName, null, values));
		db.close();

	}

	public Cursor query(String tableName, String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy)
	{
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(tableName, columns, selection, selectionArgs,
				groupBy, having, orderBy);
		return c;
	}
	

	public void del(String tableName, int id)
	{
		if (db == null) db = getWritableDatabase();
		db.delete(tableName, "_id=?", new String[]
		{ String.valueOf(id) });
	}

	public void close()
	{
		if (db != null) db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
	}

	private void createTable(Class<?> tclass, SQLiteDatabase db)
	{
		boolean isfrist = true;
		StringBuilder tStringBuilder = new StringBuilder("create table t");
		tStringBuilder.append(tclass.getSimpleName());
		tStringBuilder.append(" (");
		for (Field field : tclass.getFields())
		{
			if (isfrist == false)
			{
				tStringBuilder.append(',');
			}
			isfrist = false;
			Class<?> type = field.getType();
			String name = field.getName();
			tStringBuilder.append(name);
			tStringBuilder.append(' ');
			if (type.equals(Date.class) || type.equals(int.class)
					|| type.equals(Long.class) || type.equals(long.class)
					|| type.equals(Boolean.class) || type.equals(boolean.class)
					|| type.equals(Integer.class))
			{
				tStringBuilder.append("integer");
			}
			else if (type.equals(float.class) || type.equals(Float.class))
			{
				tStringBuilder.append("float");
			}
			else if (type.equals(double.class) || type.equals(Double.class))
			{
				tStringBuilder.append("double");
			}
			else if (type.equals(UUID.class))
			{
				tStringBuilder.append("text");
			}
			else
			{
				tStringBuilder.append("text");
			}
			if (name.equals("ID"))
			{
				tStringBuilder.append(" PRIMARY KEY autoincrement");
			}
		}
		tStringBuilder.append(')');
		try
		{
			db.execSQL(tStringBuilder.toString());
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}

}
