 package com.example.crudlistwithpic;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener {
	
	//EditText txtSearch;
	ListView lv;
	ArrayList<Person> list=new ArrayList<Person>();
	ArrayList<Person> source= new ArrayList<Person>();
	ItemAdapter adapter;
	//MyAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //txtSearch=(EditText) this.findViewById(R.id.editText1);
        this.lv=(ListView) this.findViewById(R.id.listView1);
        adapter=new ItemAdapter(this, list);
        //this.adapter=new ItemAdapter(this,list);
        this.lv.setAdapter(adapter);  
        
        lv.setOnItemClickListener(this);
      
    
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent=new Intent(this,UpdatePerson.class);
		this.startActivityForResult(intent, 0); //add new item
		return super.onOptionsItemSelected(item);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode==0 && resultCode==Activity.RESULT_OK){
			Bundle b=data.getExtras();
			Uri uri=b.getParcelable("image");
			String name=b.getString("name");
			
			
			list.add((new Person(uri, name)));
			this.adapter.notifyDataSetChanged();
		}
	}



	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle(list.get(arg2).getName());
		
		ImageView myiv=new ImageView(this);
			myiv.setImageURI(list.get(arg2).getImageUri());
		TextView myname=new TextView(this);
			myname.setText(list.get(arg2).getName());
		
		
		LinearLayout mainLayout=new LinearLayout(this);	
				mainLayout.setOrientation(LinearLayout.HORIZONTAL);
				mainLayout.addView(myiv);
				
		LinearLayout subLayout=new LinearLayout(this);
			subLayout.setOrientation(LinearLayout.VERTICAL);
			subLayout.addView(myname);
			
			mainLayout.addView(subLayout);
			
			builder.setView(mainLayout);
			builder.setNeutralButton("Okey", null);
			
	AlertDialog dialog=builder.create();
		dialog.show();

	}
    
    
}
