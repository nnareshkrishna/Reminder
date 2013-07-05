package com.example.taskfour;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;


public class MainActivity extends Activity{
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
     setContentView(R.layout.main);
    CalendarView calendarView=(CalendarView) findViewById(R.id.calendarView1);
    calendarView.setOnDateChangeListener(new OnDateChangeListener() {

        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month,
                int dayOfMonth) {
             // TODO Auto-generated method stub
        	Intent intent=new Intent() ;
        	intent.putExtra("edit", dayOfMonth+"-"+(month+1)+"-"+year) ;
        	intent.setClass(getApplicationContext(),SecondActivity.class);
        	startActivity(intent) ;
        }	
    });
}
}


