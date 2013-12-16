package com.medicinesearch.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class FileUtils {
	
	private String SDPATH;
	
	public String getSDPATH(){
		return SDPATH;
	}
	
	public FileUtils(){
		SDPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	}
	
	public File createSDFile(String dir, String fileName) throws IOException{
		File file = new File(SDPATH+ File.separator +dir + File.separator + fileName);
		file.createNewFile();
		return file;
	}
	
	public File createDir(String dirName){
		File dir = new File(SDPATH+ File.separator + dirName + File.separator);
		dir.mkdir();
		return dir;
	}
	
	public boolean isFileExist(String path,String fileName){
		File file = new File(SDPATH +File.separator+ path + File.separator + fileName);
		return file.exists();
	}
	
	public File write2SDFromInput(String path ,String fileName ,InputStream inputstream){
		File file = null;
		OutputStream outputstream = null;
		try {
			createDir(path);
			file = createSDFile(path,fileName);
			outputstream = new FileOutputStream(file);
			byte []buffer = new byte[4*1024];
			int temp;
			while((temp = inputstream.read(buffer)) != -1){
				outputstream.write(buffer,0,temp);
			}
			outputstream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				outputstream.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return file;
	}
}
