package com.medicinesearch.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpDownloader
{
	private URL url = null;

	public String download(String urlStr)
	{
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		try
		{
			url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();
			buffer = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));
			while ((line = buffer.readLine()) != null)
			{
				sb.append(line);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				buffer.close();
			}
			catch (Exception e2)
			{
				// TODO: handle exception
			}
		}
		return sb.toString();
	}

	public int download(String urlStr, String path, String fileName)
	{
		FileUtils fileUtils = new FileUtils();
		InputStream inputStream = null;

		if (fileUtils.isFileExist(path , fileName))
		{
			return 1;
		}
		else
		{
			try
			{
				inputStream = getInputStreamFromUrl(urlStr);
				File resultFile = fileUtils.write2SDFromInput(path, fileName,
						inputStream);
				if (resultFile == null)
				{
					return -1;
				}
			}

			catch (Exception e)
			{
				e.printStackTrace();
				return -1;
			}
			finally
			{
				try
				{
					inputStream.close();
				}
				catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}
		}
		return 0;
	}

	public InputStream getInputStreamFromUrl(String urlStr)
			throws MalformedURLException, Exception
	{
		url = new URL(urlStr);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		InputStream inputStream = urlConn.getInputStream();
		return inputStream;
	}

}
