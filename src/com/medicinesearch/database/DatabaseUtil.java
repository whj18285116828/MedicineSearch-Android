package com.medicinesearch.database;


import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  

import com.medicinesearch_android.R;


  
import android.content.Context;  
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteException;  
  
  
/** 
 * copy数据库到apk包 
 *  
 */  
public class DatabaseUtil {  
  
	private File file;
    private Context context;  
    public static String dbName = "medicinesearch.db";// 数据库的名字  
    private static String DATABASE_PATH;// 数据库在手机里的路径  
  
    public DatabaseUtil(Context context) {  
    	 this.context = context;
         this.file = context.getDatabasePath(dbName);
         DatabaseUtil.DATABASE_PATH = file.getPath(); 
    }  
  
    /** 
     * 判断数据库是否存在 
     *  
     * @return false or true 
     */  
    public boolean checkDataBase() {
        SQLiteDatabase db = null;
        try {
            db = SQLiteDatabase.openDatabase(DATABASE_PATH, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {

        }
        if (db != null) {
            db.close();
        }
        return db != null;
    }  
  
    /** 
     * 复制数据库到手机指定文件夹下 
     *  
     * @throws IOException 
     */  
    public void copyDataBase() throws IOException {
        File dir = new File(DATABASE_PATH);
        if (!dir.getParentFile().exists()){
            // 判断文件夹是否存在，不存在就新建一个
            dir.getParentFile().mkdir();
        }
        FileOutputStream os = new FileOutputStream(DATABASE_PATH);// 得到数据库文件的写入流
        InputStream is = context.getResources().openRawResource(R.raw.medicinesearch);// 得到数据库文件的数据流
        byte[] buffer = new byte[8192];
        int count = 0;
        while ((count = is.read(buffer)) > 0) {
            os.write(buffer, 0, count);
            os.flush();
        }
        is.close();
        os.close();
    } 
}  
