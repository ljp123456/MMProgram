package com.example.mmprogram;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Registration;

import com.example.entity.User;
import com.example.xmppmanager.XmppTool;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	private ImageView mCream;
	private EditText mUserName,mPwd,mRePwd,mNickName;
	private RadioButton mMsex,mFsex;
	private Button mRegister;
	private IQ result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  
		  
        startActivityForResult(intent, 1);  
		setContentView(R.layout.activity_register);
		//��ʼ��view
		initView();
		//����
		setListener();
	}
	private void setListener() {
		mCream.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  
				startActivityForResult(intent, 1);  
			}
		});
		
		mRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				registerInfo();
			}

		});
		
	}
	//ע����Ϣ
	private void registerInfo() {
		String name = mUserName.getText().toString();
		if(name.equals(null)){
			mUserName.setError("�û�������Ϊ��");
			return;
		}
		String nickName = mNickName.getText().toString();
		if(nickName.equals(null)){
			mNickName.setError("�ǳƲ���Ϊ��");
			return;
		}
		String pwd = mPwd.getText().toString();
		if(pwd.equals(null)){
			mPwd.setError("���벻��Ϊ��");
			return;
		}
		String rePwd = mRePwd.getText().toString();
		if(!pwd.equals(rePwd)){
			mRePwd.setError("ǰ�����벻һ��");
			return ;
		}
		String sex = "��";
		String mSex = mMsex.getText().toString(); 
		String fSex = mFsex.getText().toString();
		if(mSex.equals(null)){
			sex = fSex;
		}
		User user = new User(name, nickName, pwd, sex);
		//���ӷ���������ע��
		register(user);
	}
	private void register(final User user) {
		 new Thread(){
	        	public void run() {
//	        		Log.i("main", user.getUserName()+"--"+user.getNickName()+"--"+user.getUserPwd()+"--"+user.getUserSex());
	        		XmppTool.getConnection();
	        		Registration reg = new Registration();  
	                reg.setType(IQ.Type.SET);  
	                reg.setTo(XmppTool.getConnection().getServiceName());
	                reg.setUsername(user.getUserName());
	        		reg.setPassword(user.getUserPwd());
	        		reg.addAttribute("name",user.getNickName());
	        		reg.addAttribute("sex",user.getUserSex());
	        		reg.addAttribute("android", "android");
	        		PacketFilter filter = new AndFilter(new PacketIDFilter(
	                reg.getPacketID()), new PacketTypeFilter(IQ.class));
	        		PacketCollector collector = XmppTool.getConnection().createPacketCollector(filter);
	        		XmppTool.getConnection().sendPacket(reg);
	        		result = (IQ) collector.nextResult(SmackConfiguration
	                        .getPacketReplyTimeout());
	        		Log.i("main",result+"--------");
	        		collector.cancel();// ֹͣ����results���Ƿ�ɹ��Ľ����
	        		
	    			runOnUiThread( new Runnable() {
						public void run() {
							if (result == null) {
							Toast.makeText(RegisterActivity.this, "������û�з��ؽ��", Toast.LENGTH_SHORT).show();
							} else if (result.getType() == IQ.Type.ERROR) {
								if (result.getError().toString()
										.equalsIgnoreCase("conflict(409)")) {
									Toast.makeText(RegisterActivity.this, "����˺��Ѿ�����", Toast.LENGTH_SHORT).show();
								} else {
									Toast.makeText(RegisterActivity.this, "ע��ʧ��",Toast.LENGTH_SHORT).show();
								}
							} else if (result.getType() == IQ.Type.RESULT) {
								Toast.makeText(RegisterActivity.this, "ע��ɹ�", 3000).show();
								Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
								startActivity(intent);
							}
						}
					});

	        	}
		 }.start();
	}
	private void initView() {
		mCream = (ImageView) findViewById(R.id.ibCream);
		mUserName = (EditText) findViewById(R.id.etRUserName);
		mNickName = (EditText) findViewById(R.id.etRNickName);
		mPwd = (EditText) findViewById(R.id.etRPwd);
		mRePwd = (EditText) findViewById(R.id.etRRePwd);
		mRegister = (Button) findViewById(R.id.btnRegister);
		mMsex = (RadioButton) findViewById(R.id.rbSexM);
		mFsex = (RadioButton) findViewById(R.id.rbSexF);
	}
	
	 @Override  
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	        super.onActivityResult(requestCode, resultCode, data);  
	        if (resultCode == Activity.RESULT_OK) {  
	            String sdStatus = Environment.getExternalStorageState();  
	            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd�Ƿ����   
	                return;  
	            }  
	            String name = new DateFormat().format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";     
	           
	            Log.i("main", name);
	            
	            Bundle bundle = data.getExtras();  
	            Bitmap bitmap = (Bitmap) bundle.get("data");// ��ȡ������ص����ݣ���ת��ΪBitmapͼƬ��ʽ   
	          
	            FileOutputStream b = null;  
	            File file = Environment.getExternalStorageDirectory();
	            
	            file.mkdirs();// �����ļ���   
	            String fileName = file.getPath()+"/"+name;  
	            Log.i("main", fileName);
	            try {  
	                b = new FileOutputStream(fileName);  
	                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// ������д���ļ�   
	                
	                Log.i("main", bitmap.toString());
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            } finally {  
	                try {  
	                    b.flush();  
	                    b.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	            Log.i("main", "-------------");
	            mCream.setImageBitmap(bitmap);
	        }  
	    }  

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.cream, menu);
			return true;
		}
}
