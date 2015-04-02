package com.example.mmprogram;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

import com.example.xmppmanager.XmppTool;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{

	private EditText mUserName,mPwd;
	private Button mLogin;
	private TextView mRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        
        initView();
        setOnlistener();
    }
    private void initView() {
    	mUserName = (EditText) findViewById(R.id.etUserName);
    	mPwd = (EditText) findViewById(R.id.etPwd);
    	mLogin = (Button) findViewById(R.id.btnLogin);
    	mRegister = (TextView) findViewById(R.id.btnRegister);
    }
    private void setOnlistener() {
		mLogin.setOnClickListener(this);
		mRegister.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		//获取登录信息并进行判断
		case R.id.btnLogin:
			//得到登录名
			String userName = mUserName.getText().toString();
			//得到登录密码
			String pwd = mPwd.getText().toString();
			//判断成功则
			//跳转到主页面
			 final String account=mUserName.getText().toString();
             final String password=mPwd.getText().toString();
             if(account.equals("") || password.equals("")){
                 Toast.makeText(LoginActivity.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
             }else{
                 new Thread(){
                	 public void run() {
                		 try{
                             XmppTool.getConnection().login(account,password);
                              Presence presence = new Presence(Presence.Type.available);
                              XmppTool.getConnection().sendPacket(presence);
//                                  Toast.makeText(LoginActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
                                  
                                  startActivity(new Intent(LoginActivity.this,MainActivity.class));
                              }catch(XMPPException e) 
                              {
                                  XmppTool.closeConnection();
                              }
                	 };
                 }.start();
             }
			break;
		//转入注册页面
		case R.id.btnRegister:
			intent =  new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent);
			break;
		}
	}
}
