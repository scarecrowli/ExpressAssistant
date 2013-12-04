package com.ustc.prlib.util; 

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ustc.prlib.vo.BaseVo;

/**
 * @description : ����һЩͨ�õĽ���json���ݷ���
 * @version : v1.0
 */
public class JsonParserUtil {

	/**
	 * �������json����ת��Ϊ���� { "":"" } 
	 * �ö������̳���:BaseVo
	 */
	public static BaseVo parseJson2Vo(InputStream in, Class<? extends BaseVo> classzz) {
		BaseVo vo = null;
		try {
			Gson gson = new Gson();
			InputStreamReader inReader = new InputStreamReader(in);
			vo = gson.fromJson(inReader, classzz);
		} catch ( Exception e) {
			e.printStackTrace();
			System.out.println("parseJson2Vo json����ʧ��" + e.getMessage());
		}
		return vo;
	}

	public static BaseVo parseJson2Vo(String jsonStr, Class<? extends BaseVo> classzz) {
		BaseVo vo = null;
		try {
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonObject jsonObject = (JsonObject) parser.parse( jsonStr );
			vo = gson.fromJson(jsonObject, classzz);
		} catch ( Exception e) {
			e.printStackTrace();
			System.out.println("parseJson2Vo json����ʧ��" + e.getMessage());
		}
		return vo;
	}
	
	/**
	 * �������json����ת��Ϊ���� { "":"" } 
	 * �ö������̳���:BaseVo
	 */
	public static JsonArray parseJson2JsonArray(String jsonStr) {
		JsonArray jsonArray = null;
		try {
 			JsonParser parser = new JsonParser();
			JsonObject jsonObject = (JsonObject) parser.parse( jsonStr );
			jsonArray = (JsonArray) jsonObject.get("item");
		} catch ( Exception e) {
			e.printStackTrace();
			System.out.println("parseJson2JsonArray ����ʧ��" + e.getMessage() + jsonStr);
		}
		return jsonArray;
	}
	
	/**
	 * ��JsonElementת��Ϊ����
	 * @param element
	 * @param classzz
	 * @return
	 */
	public static BaseVo JsonElement2Obj(JsonElement element, Class<? extends BaseVo> classzz) {
		Gson gson = new Gson();
		return gson.fromJson(element, classzz);
	}
	
	/**
	 * ������ת��Ϊ����,json��ʽ  (����item���ڵ�)
	 * {  "item":[
	 *   	{ 	"text":"" } ,
	 *   	{   "text":"" }
	 *		]		
	 *	}
	 * @param jsonStr	json�ַ���
	 * @param nodeName item�ڵ�����
	 * @param type  new TypeToken<BaseVo>(){}.getType();
	 * @return
	 */
	public static ArrayList<? extends BaseVo> parseJson2List(String jsonStr, Type type, String nodeName){
		ArrayList<? extends BaseVo> arrvo = null;
		try {
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonObject jsonObject = (JsonObject) parser.parse( jsonStr );
			JsonArray jsonArray = (JsonArray) jsonObject.get( nodeName );
			arrvo = gson.fromJson(jsonArray, type);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("parseJson2List ����ʧ�� " + e.getMessage());
		}
		return arrvo;
	}
	
	
	/**
	 * ������ת��Ϊ����,json��ʽ  (������item���ڵ�)
	 *  [ { "text":"" } ,
	 *    { "text":"" }
	 *  ]		
	 * @param jsonStr
	 * @return
	 */
	public static ArrayList<? extends BaseVo> parseJson2ListNoItem(String jsonStr, Type type ) {
		ArrayList<? extends BaseVo> arrvo = null;
		try {
 			Gson gson = new Gson();
			arrvo = gson.fromJson(jsonStr, type);
		} catch ( Exception e ) {
			e.printStackTrace();
			System.out.println("json����ʧ��"  + jsonStr);
		}
		return arrvo;
	}
	 
}
