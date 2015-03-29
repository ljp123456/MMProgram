package com.example.mmprogram;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
		//��ȡ��¼��Ϣ�������ж�
		case R.id.btnLogin:
			//�õ���¼��
			String userName = mUserName.getText().toString();
			//�õ���¼����
			String pwd = mPwd.getText().toString();
			//�жϳɹ���
			//��ת����ҳ��
			intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			break;
		//ת��ע��ҳ��
		case R.id.btnRegister:
			intent =  new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent);
			break;
		}
	}
}
