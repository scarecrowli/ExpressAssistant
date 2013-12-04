package com.ustc.prlib.util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;

/**
 * @description : ˽���ļ����ļ�����
 * @package cn.apppark.mcd.util
 * @title:FileReadSave.java
 * @author : email:xiangyanhui@unitepower.net
 * @date :2012-4-9 ����10:53:53 
 * @version : v1.0
 */

public class PrivateFileReadSave {
	/**
	 * ��ȡ�ļ�
	 * @param fileName
	 * @return
	 */

	public static String read(String fileName,Context context)   {
		File f = new File( context.getFilesDir() +"/"+fileName);
		ByteArrayOutputStream byteArray = null;

		if ( f.exists()) {
			FileInputStream fileInputStream;
			try {
				fileInputStream = context.openFileInput(fileName);
				byteArray = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fileInputStream.read(buffer)) > 0) {
					byteArray.write(buffer, 0, len);
				}
				fileInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

		}

		if ( byteArray != null) {
			return byteArray.toString();
		} else {
			return null;
		}

	}


	/**
	 * �����ļ�
	 * @param fileName
	 * @param fileContent
	 */

	public static  boolean save(String fileName, String fileContent,Context context) {
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = context.openFileOutput( fileName, Context.MODE_PRIVATE);
			fileOutputStream.write(fileContent.getBytes());
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}


	/**
	 * ��ȡ�ļ����µ������ļ�,�����ļ���С,�������ļ�
	 */
	public static String countFileSize(Context context, String[] fileName)   {
		double len = 0.00;
		for (int i=0; i<fileName.length; i++) {
			File fname = new File( context.getFilesDir() +"/" + fileName[i]);
			if ( fname.exists() ) {
				len = len + fname.length();
			}
		}
		return (double) (Math.round(len/1024*100)/100.0)+"kb";
	}

	/**
	 * ɾ���ļ�
	 */
	public static boolean deleteFile(Context context, String[] fileName)   {
		boolean flag = true;
		try {
			for (int i=0; i<fileName.length; i++) {
				File fname = new File( context.getFilesDir() +"/" + fileName[i]);
				if ( fname.exists() ) {
					fname.delete();
				}
			}
		} catch (Exception e) {
			flag = false;
		}
		return  flag;
	}

	/**
	 * ɾ���ļ�
	 */
	public static boolean deleteFile(String fileName, Context context)   {
		boolean flag = true;
		try {
			File fname = new File( context.getFilesDir() +"/" + fileName);
			if ( fname.exists() ) {
				fname.delete();
			}
		} catch (Exception e) {
			flag = false;
		}
		return  flag;
	}
}
