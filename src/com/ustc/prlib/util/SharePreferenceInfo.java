package com.ustc.prlib.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SharePreferenceInfo {
	private Context mContext;
	private String iniName = "init_info";

	public SharePreferenceInfo(Context context) {
		super();
		this.mContext = context;
	}

	//Ĭ�ϲ���
	public int getDefaultOperate() {
		SharedPreferences mPreferences = mContext.getSharedPreferences( iniName, Context.MODE_PRIVATE);
		return mPreferences.getInt( "_DefaultOperate", -1 );
	}
	public void updateDefaultOperate(int operate) {
		Editor editor = mContext.getSharedPreferences( iniName, Context.MODE_PRIVATE).edit();
		editor.putInt( "_DefaultOperate", operate);
		editor.commit();
	}

	//Ĭ�϶���ģ��ID���޷���-1
	public int getDefaultSmsTempletId() {
		SharedPreferences mPreferences = mContext.getSharedPreferences( iniName, Context.MODE_PRIVATE);
		return mPreferences.getInt( "_DefaultSmsTempletId", -1);
	}
	public void updateDefaultSmsTempletId(int id) {
		Editor editor = mContext.getSharedPreferences( iniName, Context.MODE_PRIVATE).edit();
		editor.putInt ( "_DefaultSmsTempletId", id);
		editor.commit();
	}
	//Ĭ�϶���ģ������ 
	public String getDefaultSmsContent() {
		SharedPreferences mPreferences = mContext.getSharedPreferences( iniName, Context.MODE_PRIVATE);
		return mPreferences.getString( "_DefaultSmsContent", null);
	}
	public void updateDefaultSmsContent(String content) {
		Editor editor = mContext.getSharedPreferences( iniName, Context.MODE_PRIVATE).edit();
		editor.putString ( "_DefaultSmsContent", content);
		editor.commit();
	}


	//Ĭ�Ͽ��ģ��ID���޷���-1
	public int getDefaultCompressTempletId() {
		SharedPreferences mPreferences = mContext.getSharedPreferences( iniName, Context.MODE_PRIVATE);
		return mPreferences.getInt( "_DefaultCompressTempletId", -1);
	}
	public void updateDefaultCompressTempletId(int id) {
		Editor editor = mContext.getSharedPreferences( iniName, Context.MODE_PRIVATE).edit();
		editor.putInt ( "_DefaultCompressTempletId", id);
		editor.commit();
	}
	//Ĭ�Ͽ��ģ������ 
	public String getDefaultCompressContent() {
		SharedPreferences mPreferences = mContext.getSharedPreferences( iniName, Context.MODE_PRIVATE);
		return mPreferences.getString( "_DefaultCompressContent", null);
	}
	public void updateDefaultCompressContent(String content) {
		Editor editor = mContext.getSharedPreferences( iniName, Context.MODE_PRIVATE).edit();
		editor.putString ( "_DefaultCompressContent", content);
		editor.commit();
	}

}
