package com.ustc.prlib.xunfei;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ustc.prlib.util.ButtonColorFilter;
import com.ustc.prlib.util.JsonParserUtil;
import com.ustc.prlib.util.PrivateFileReadSave;
import com.ustc.prlib.util.SharePreferenceInfo;
import com.ustc.prlib.vo.BaseParam;
import com.ustc.prlib.vo.ExpressVo;
import com.ustc.prlib.vo.SmsVo;
import com.xiang.xunfei.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @description : ģ�����
 * @package com.xiang.xunfei
 * @title:AddTempletActivity.java
 * @author : email:xiangyanhui@unitepower.net
 * @date :2013-12-3 ����3:46:33 
 * @version : v4.0
 */
public class AddTempletActivity extends Activity  implements OnClickListener{
	private Context context = this;
	private Button btn_save, btn_back; 
	private EditText et;
	private TextView tv_title;
	private LinearLayout ll_defaultOperate, ll_sendSms, ll_call, ll_addConnect;
	private Button btn_sendSms, btn_call, btn_addConnect; 
	private SharePreferenceInfo info;
	private int addType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_item);
		addType = getIntent().getIntExtra(BaseParam.ADDTYPE, -1);
		info = new SharePreferenceInfo(context);
		initWidget();
	}

	private void initWidget() {
		btn_save = (Button)findViewById( R.id.addtemplet_btn_save );
		btn_back = (Button)findViewById( R.id.addtemplet_btn_back );
		et = (EditText)findViewById( R.id.addtemplet_et );
		tv_title = (TextView)findViewById( R.id.addtemplet_tv_title );
		ButtonColorFilter.setButtonFocusChanged( btn_save);
		ButtonColorFilter.setButtonFocusChanged( btn_back);

		ll_defaultOperate = (LinearLayout)findViewById( R.id.addtemplet_ll_defaultoperate );
		ll_sendSms = (LinearLayout)findViewById( R.id.addtemplet_ll_sendsms );
		ll_call = (LinearLayout)findViewById( R.id.addtemplet_ll_call );
		ll_addConnect = (LinearLayout)findViewById( R.id.addtemplet_ll_addconnect );

		btn_sendSms = (Button)findViewById( R.id.addtemplet_btn_sendsms );
		btn_call = (Button)findViewById( R.id.addtemplet_btn_call );
		btn_addConnect = (Button)findViewById( R.id.addtemplet_btn_addconnect );

		ll_sendSms.setOnClickListener(this);
		ll_call.setOnClickListener(this);
		ll_addConnect.setOnClickListener(this);
		btn_save.setOnClickListener(this);
		btn_back.setOnClickListener(this);

		switch( addType ) {
		case BaseParam.ADDTYPE_SMS_TEMPLET :
			ll_defaultOperate.setVisibility( View.GONE );
			tv_title.setText("��Ӷ���ģ��");
			et.setHint("�������������");
			break;
		case BaseParam.ADDTYPE_EXPRESS_TEMPLET :
			ll_defaultOperate.setVisibility( View.GONE );
			tv_title.setText("��ӿ��");
			et.setHint("������������");
			break;

		case BaseParam.ADDTYPE_DEFAULT_OPERATE :
			ll_defaultOperate.setVisibility( View.VISIBLE );
			et.setVisibility( View.GONE );
			btn_save.setVisibility( View.GONE );
			tv_title.setText("��ѡ��Ĭ�ϲ���");
			switch( info.getDefaultOperate() ) {
			case BaseParam.OPERATE_SENDSMS :  
				btn_sendSms.setBackgroundResource( R.drawable.btn_sel );
				break;
			case BaseParam.OPERATE_CALLPHONE :  
				btn_call.setBackgroundResource( R.drawable.btn_sel );
				break;
			case BaseParam.OPERATE_ADDCONNECT :  
				btn_addConnect.setBackgroundResource( R.drawable.btn_sel );
				break;
			}
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private void addSmsTemplet(String content) {
		int currentId = 0;
		ArrayList<SmsVo> listVo = null;
		String temp = PrivateFileReadSave.read(BaseParam.SMS_FILENAME, context);
		if ( temp != null ) {
			Type type = new TypeToken<ArrayList<SmsVo>>(){}.getType();
			listVo = (ArrayList<SmsVo>) JsonParserUtil.parseJson2ListNoItem(temp, type);
			 
		}  
		if ( listVo == null ) {
			listVo = new ArrayList<SmsVo>();
		} else {
			currentId = listVo.get(listVo.size()-1).getId() + 1;
		}
		
		SmsVo vo = new SmsVo();
		vo.setContent( content );
		vo.setId( currentId );
		listVo.add(vo);
		
		Gson gson = new Gson();
		String result = gson.toJson( listVo );
		PrivateFileReadSave.save(BaseParam.SMS_FILENAME, result, context);
		info.updateDefaultSmsTempletId( currentId );
		info.updateDefaultSmsContent(content);
		Toast.makeText(context, "��ӳɹ�", 0).show();
		setResult(1);
		finish();
	}
	
	@SuppressWarnings("unchecked")
	private void addCompressTemplet(String content) {
		int currentId = 0;
		ArrayList<ExpressVo> listVo = null;
		String temp = PrivateFileReadSave.read(BaseParam.COMPRESS_FILENAME, context);
		if ( temp != null ) {
			Type type = new TypeToken<ArrayList<ExpressVo>>(){}.getType();
			listVo = (ArrayList<ExpressVo>) JsonParserUtil.parseJson2ListNoItem(temp, type);
		}  
		
		if ( listVo == null ) {
			listVo = new ArrayList<ExpressVo>();
		} else {
			currentId = listVo.get(listVo.size()-1).getId() + 1;
		}
		
		ExpressVo vo = new ExpressVo();
		vo.setContent( content );
		vo.setId( currentId );
		listVo.add(vo);
		
		Gson gson = new Gson();
		String result = gson.toJson( listVo );
		PrivateFileReadSave.save(BaseParam.COMPRESS_FILENAME, result, context);
		info.updateDefaultCompressTempletId( currentId );
		info.updateDefaultCompressContent(content);
		Toast.makeText(context, "��ӳɹ�", 0).show();
		setResult(1);
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addtemplet_btn_save:
			switch ( addType ) {
			case BaseParam.ADDTYPE_SMS_TEMPLET:
				if ( !"".equals( et.getText().toString() ) && et.getText().toString() != null ) {
					addSmsTemplet( et.getText().toString() );
				} else {
					Toast.makeText(context, "ģ�����ݲ���Ϊ��", 0).show();
				}
				break;
			case BaseParam.ADDTYPE_EXPRESS_TEMPLET:
				if ( !"".equals( et.getText().toString() ) && et.getText().toString() != null ) {
					addCompressTemplet( et.getText().toString() );
				} else {
					Toast.makeText(context, "������Ʋ���Ϊ��", 0).show();
				}
				break;
			}
			break;
		case R.id.addtemplet_btn_back:
			finish();
			break;

		case R.id.addtemplet_ll_sendsms:
			info.updateDefaultOperate( BaseParam.OPERATE_SENDSMS );
			Toast.makeText(context, "Ĭ�ϲ����޸ĳɹ�", 0).show();
			setResult(1);
			finish();
			break;
		case R.id.addtemplet_ll_call:
			info.updateDefaultOperate( BaseParam.OPERATE_CALLPHONE );
			Toast.makeText(context, "Ĭ�ϲ����޸ĳɹ�", 0).show();
			setResult(1);
			finish();
			break;
		case R.id.addtemplet_ll_addconnect:
			info.updateDefaultOperate( BaseParam.OPERATE_ADDCONNECT );
			Toast.makeText(context, "Ĭ�ϲ����޸ĳɹ�", 0).show();
			setResult(1);
			finish();
			break;
		}
	}
}
