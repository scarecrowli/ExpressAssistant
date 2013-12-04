package com.ustc.prlib.xunfei;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.iflytek.speech.RecognizerResult;
import com.iflytek.speech.SpeechConfig.RATE;
import com.iflytek.speech.SpeechError;
import com.iflytek.ui.RecognizerDialog;
import com.iflytek.ui.RecognizerDialogListener;
import com.ustc.prlib.util.ButtonColorFilter;
import com.ustc.prlib.util.MainListAdapter;
import com.ustc.prlib.util.PublicUtil;
import com.ustc.prlib.util.SharePreferenceInfo;
import com.ustc.prlib.vo.BaseParam;
import com.ustc.prlib.vo.PhoneVo;
import com.xiang.xunfei.R;


/**
 * @description : �������������
 * @package com.xiang.xunfei
 * @title:MainActivity.java
 * @author :  
 * @date :2013-12-3 ����11:15:50 
 * @version : v4.0
 */
public class MainActivity extends Activity implements OnClickListener, RecognizerDialogListener{
	private static String POSITION_LEFT = "1";
	private static String POSTIION_RIGHT = "2";
	private StringBuilder builder;

	private ListView listView; 
	private MainListAdapter adapter;
	private ArrayList<PhoneVo> listVo;

	private Context context = this;
	private Button btn_start, btn_more; 
	private SharedPreferences mSharedPreferences;//���棬���浱ǰ�������������һ������Ӧ�ó���ʹ��.

	private RecognizerDialog iatDialog;//ʶ��Dialog
	private String mInitParams;//��ʼ������
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initWidget();

		mInitParams = "appid=" + getString( R.string.app_id);

		//��ʼ��תдDialog, appid��Ҫ��http://open.voicecloud.cn��ȡ.
		iatDialog = new RecognizerDialog(this, mInitParams);
		iatDialog.setListener(this);
		//��ʼ���������.
		mSharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
	}


	private void initWidget() {
		listVo = new ArrayList<PhoneVo>();
		PhoneVo vo = new PhoneVo();
		vo.setContent("���ã��ܸ���Ϊ��������˵�ֻ��ţ�");
		vo.setPositon( POSITION_LEFT );
		listVo.add(vo);
		adapter = new MainListAdapter(context, listVo);

		listView = (ListView)findViewById( R.id.main_listview );
		listView.setDividerHeight(0);
		listView.setAdapter( adapter);

		btn_more = (Button)findViewById( R.id.main_btn_set );
		btn_more.setOnClickListener( this );
		btn_start = (Button)findViewById( R.id.main_btn_getword );
		btn_start.setOnClickListener( this );
		ButtonColorFilter.setButtonFocusChanged(btn_more);
	}

	/**
	 * ��ʾתд�Ի���.
	 * @param
	 */
	public void showIatDialog() {
		//��ȡ�������
		String engine = mSharedPreferences.getString(
				getString(R.string.preference_key_iat_engine),
				getString(R.string.preference_default_iat_engine));

		//��ȡarea������POI����ʱ��Ҫ����.
		String area = "";
		//����תдDialog�������poi����.
		iatDialog.setEngine(engine, area, null);
		//���ò����ʲ��������ھ��󲿷��ֻ�ֻ֧��8K��16K����������11K��22K�����ʽ��޷�����¼��. 
		String rate = mSharedPreferences.getString(
				getString(R.string.preference_key_iat_rate),
				getString(R.string.preference_default_iat_rate));
		if(rate.equals("rate8k"))
			iatDialog.setSampleRate(RATE.rate8k);
		else if(rate.equals("rate11k"))
			iatDialog.setSampleRate(RATE.rate11k);
		else if(rate.equals("rate16k"))
			iatDialog.setSampleRate(RATE.rate16k);
		else if(rate.equals("rate22k"))
			iatDialog.setSampleRate(RATE.rate22k);

		builder = new StringBuilder(); 
		iatDialog.show(); 
	}

	//����ʶ������� ��������
	@Override
	public void onEnd(SpeechError arg0) {
		if ( !"".equals( builder.toString() )) {
			String phone = PublicUtil.getPhone( builder.toString() );
			if ( phone != null ) {
				PhoneVo vo = new PhoneVo();
				vo.setContent( phone );
				vo.setPositon( POSTIION_RIGHT );
				listVo.add( vo );
				adapter.notifyDataSetChanged();
				Toast.makeText(context, "ʶ�����"+ phone, Toast.LENGTH_SHORT).show();
				startOperate( phone);
			} else {
				Toast.makeText(context, "����Ч����:" + builder.toString(), Toast.LENGTH_LONG).show();
			}
		}
	}

	SharePreferenceInfo info = new SharePreferenceInfo(context);
	EditText et;
	String phone;
	private void startOperate(String phoneStr) {
		this.phone = phoneStr;
		switch ( info.getDefaultOperate() ) {
		case BaseParam.OPERATE_CALLPHONE :
			PublicUtil.callPhone(context, phone);
			break;

		case BaseParam.OPERATE_SENDSMS :
			String content = info.getDefaultSmsContent() + 
			"��" + info.getDefaultCompressContent() + "��";
			PublicUtil.editSendSms(context, phone, content);
			break;

		case BaseParam.OPERATE_ADDCONNECT :
			et = new EditText(this);
			new AlertDialog.Builder(this)
			.setTitle("��������ϵ��")
			.setIcon( android.R.drawable.ic_dialog_info)
			.setView( et )
			.setPositiveButton("ȷ��",  
					new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if ( !"".equals( et.getText().toString().trim() ) ) {
						PublicUtil.insertContact(context, et.getText().toString(), phone);
					}
				}
			}  
					)
					.setNegativeButton("ȡ��", null)
					.show();
			break;
		}


	}

	@Override
	public void onResults(ArrayList<RecognizerResult> results, boolean arg1) {
		for (RecognizerResult recognizerResult : results) {
			builder.append(recognizerResult.text);
		}
	}

	@Override
	public void onClick(View v) {
		switch ( v.getId() ) {
		case R.id.main_btn_getword:
			showIatDialog();
			break;

		case  R.id.main_btn_set :
			startActivity(new Intent(MainActivity.this, SetActivity.class));
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
