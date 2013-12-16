package com.medicinesearch.download;


import com.medicinesearch.util.HttpDownloader;

public class Downloader extends Thread
{
	private String url;

	private String fileName;

	private String dir = "MedicineSearch";
	
	public Downloader(String url, String fileName)
	{
		this.url = url;
		this.fileName = fileName;
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		HttpDownloader httpDownloader = new HttpDownloader();
		int result = httpDownloader.download(url, dir, fileName);
		if (result == 0)
		{
			System.out.println("下载成功!");
			return;
		} 
		else if(result == 1)
		{
			System.out.println("文件已存在!");
			return ;
		}
		else
		{
			System.out.println("下载失败！");
			return ;
		}
	}

}
