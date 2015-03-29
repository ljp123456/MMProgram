package com.example.mmprogram;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

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
import android.widget.ImageButton;
import android.widget.ImageView;

public class RegisterActivity extends Activity {

	private ImageView mCream;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  
		  
        startActivityForResult(intent, 1);  
		setContentView(R.layout.activity_register);
		
		initView();
	}
	private void initView() {
		mCream = (ImageView) findViewById(R.id.ibCream);
		mCream.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  
				  
                startActivityForResult(intent, 1);  
			}
		});
	}
	
	 @Override  
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	        // TODO Auto-generated method stub   
	        super.onActivityResult(requestCode, resultCode, data);  
	        if (resultCode == Activity.RESULT_OK) {  
	            String sdStatus = Environment.getExternalStorageState();  
	            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd�Ƿ����   
	                Log.i("TestFile",  
	                        "SD card is not avaiable/writeable right now.");  
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
