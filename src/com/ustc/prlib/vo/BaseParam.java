package com.ustc.prlib.vo;

public class BaseParam {
	public static final int OPERATE_SENDSMS = 1;   //���ŷ���
	public static final int OPERATE_CALLPHONE = 2;   //�绰����
	public static final int OPERATE_ADDCONNECT = 3;   //��ϵ�����
	
	public static final String SMS_FILENAME = "smstemplet.json"; 	//����ģ���ļ�
	public static final String COMPRESS_FILENAME = "express.json";  //���ģ���ļ�
	
	public static final int ADDTYPE_SMS_TEMPLET = 4;   //��Ӷ���ģ��
	public static final int ADDTYPE_EXPRESS_TEMPLET = 5;  //��ӿ��ģ��
	public static final int ADDTYPE_DEFAULT_OPERATE = 6;  //����Ĭ�ϲ���
	
	public static final int ITEM_POSITON_LEFT = 7;
	public static final int ITEM_POSITON_RIGHT = 8;
	 
	public static final String ADDTYPE = "type";
}