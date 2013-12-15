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
 * copy���ݿ⵽apk�� 
 *  
 */  
public class DatabaseUtil {  
  
	private File file;
    private Context context;  
    public static String dbName = "medicinesearch.db";// ���ݿ������  
    private static String DATABASE_PATH;// ���ݿ����ֻ����·��  
  
    public DatabaseUtil(Context context) {  
    	 this.context = context;
         this.file = context.getDatabasePath(dbName);
         DatabaseUtil.DATABASE_PATH = file.getPath(); 
    }  
  
    /** 
     * �ж����ݿ��Ƿ���� 
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
     * �������ݿ⵽�ֻ�ָ���ļ����� 
     *  
     * @throws IOException 
     */  
    public void copyDataBase() throws IOException {
        File dir = new File(DATABASE_PATH);
        if (!dir.getParentFile().exists()){
            // �ж��ļ����Ƿ���ڣ������ھ��½�һ��
            dir.getParentFile().mkdir();
        }
        FileOutputStream os = new FileOutputStream(DATABASE_PATH);// �õ����ݿ��ļ���д����
        InputStream is = context.getResources().openRawResource(R.raw.medicinesearch);// �õ����ݿ��ļ���������
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
