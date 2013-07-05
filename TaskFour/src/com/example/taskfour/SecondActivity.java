package com.example.taskfour;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity implements OnClickListener {
	//FOR notifications, convert Activity to Service in "public class extends.....OnclickListener"
	TextView txt,date;
	EditText edt ;
	Button btn, show ;
	String str ;
	ArrayList<String>myList=new ArrayList<String>() ;
	List<String> list = new ArrayList<String>();
	Calendar c=Calendar.getInstance() ;
	SimpleDateFormat df=new SimpleDateFormat("d-M-yyyy") ;
	String cdate=df.format(c.getTime()) ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second) ;
		txt=(TextView)findViewById(R.id.textView1) ;
		date=(TextView)findViewById(R.id.textView2) ;
		date.setText(cdate) ;
		Bundle bundle=getIntent().getExtras() ;
		str=bundle.getString("edit") ;
		txt.setText(str) ;
		edt=(EditText)findViewById(R.id.editText1) ;
		btn=(Button)findViewById(R.id.button1) ;
		show=(Button)findViewById(R.id.button2) ;
		list=(List<String>)findViewById(R.id.listView1) ;
		btn.setOnClickListener(this) ;
		show.setOnClickListener(this) ;
	}
	@Override
	public void onClick(View arg0) {
		ContactClass ccdatabase=new ContactClass(this) ;
		SQLiteDatabase sd=ccdatabase.getWritableDatabase() ;
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.button1:
			String str1 ;
			str1=edt.getText().toString();
			//Code to add the event to database
			ContentValues cv=new ContentValues() ;
			cv.put(ccdatabase.EVENT,edt.getText().toString()) ;
			cv.put(ccdatabase.DATE,str) ;
			sd.insert(ccdatabase.DB_TABLE,ccdatabase.EVENT, cv) ;
			sd.close() ;
			Toast.makeText(getApplicationContext(), "Event added",Toast.LENGTH_SHORT) ;
			if(cdate.equals(str))
			{
			btn.setText(str1);
			NotificationManager noti=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE) ;
			Notification notify=new Notification(R.drawable.ic_launcher,"This is your notification", System.currentTimeMillis() );
			Intent intent=new Intent(this,MainActivity.class) ;
			PendingIntent pending= PendingIntent.getActivity(this,0, intent,0) ;
			notify.setLatestEventInfo(this,"Reminder",str1,pending) ;
			noti.notify(1, notify) ;
			}
			break;
		case R.id.button2:
			Cursor c ;
			 c = sd.rawQuery("SELECT EVENT,DATE FROM " +
	                    ccdatabase.DB_TABLE +
	                    " where DATE=cdate", null);
			if (c.moveToFirst()) {
			     do {
			        list.add(c.getString(0)); 

			     } while (c.moveToNext());
			  }
			sd.close();
			break ;
		}
		
		}
}
