package com.example.crudlistwithpic;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdatePerson extends Activity implements OnClickListener {
	
	ImageView iv; 
	EditText txtName;
	Button btnSave, btnCancel;
	Uri uri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.updateperson_layout);
		
		this.iv=(ImageView) this.findViewById(R.id.imageView1);
		this.txtName=(EditText) this.findViewById(R.id.editText1);
		
		this.btnSave=(Button) this.findViewById(R.id.button1);
		this.btnCancel=(Button) this.findViewById(R.id.button2);
		
		this.iv.setOnClickListener(this);
		this.btnSave.setOnClickListener(this);
		this.btnCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		int id=arg0.getId();
		switch(id){
		case R.id.imageView1:// create an image picker
			Intent intent=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			this.startActivityForResult(intent, 111);
			break;
		case R.id.button1: //save
			String name=this.txtName.getText().toString();
			
			
			if(uri!=null && !name.equals("")  ){
				intent =new Intent();
				intent.putExtra("image",uri);
				intent.putExtra("name", name);
				
				this.setResult(Activity.RESULT_OK, intent);
			}
			else Toast.makeText(this, "Fill All Fields", Toast.LENGTH_SHORT).show();
			
			//break;
		case R.id.button2: //cancel
			this.finish(); //terminate activity
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		uri=data.getData();
		
		this.iv.setImageURI(uri);
	}
  
	
	
}
