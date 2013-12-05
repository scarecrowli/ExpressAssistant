package com.ustc.prlib.vo;

public class BaseParam {
	public static final int OPERATE_SENDSMS = 1;   //���ŷ���
	public static final int OPERATE_CALLPHONE = 2;   //�绰����
	public static final int OPERATE_ADDCONNECT = 3;   //��ϵ�����
	
	public static final String SMS_FILENAME = "smstemplate.json"; 	//����ģ���ļ�
	public static final String COMPRESS_FILENAME = "expressCompany.json";  //��ݹ�˾�ļ�
	
	public static final int OPERATION_ADD_SMS_TEMPLATE = 4;   //��Ӷ���ģ��
	public static final int OPERATION_ADD_COMPANY_TEMPLATE = 5;  //��ӹ�˾ģ��
	public static final int OPERATION_EDIT_SMS_TEMPLATE	= 6;  //�༭����ģ��
	public static final int OPERATION_EDIT_COMPANY_TEMPLATE = 7;  //�༭��˾ģ��
	public static final int OPERATION_DELETE_SMS_TEMPLATE	= 8;  //ɾ������ģ��
	public static final int OPERATION_DELETE_COMPANY_TEMPLATE = 9;  //ɾ����˾ģ��
	
	public static final int ITEM_POSITON_LEFT = 7;
	public static final int ITEM_POSITON_RIGHT = 8;
	 
	public static final String OPERATION_TYPE = "operationType";
	public static final String CLICK_ITEM_POSION = "clickItemPosion";
	public static final String CLICK_ITEM_CONTENT = "clickItemContent";
}