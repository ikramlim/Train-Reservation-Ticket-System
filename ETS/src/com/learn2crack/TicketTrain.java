package com.learn2crack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TicketTrain extends Activity{
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.ticket_layout);
	        
	        TextView tvDate = (TextView) findViewById(R.id.date);
	        TextView tvCode = (TextView) findViewById(R.id.traincode);
	        TextView tvFrom = (TextView) findViewById(R.id.from);
	        TextView tvTo = (TextView) findViewById(R.id.to);
	        TextView tvTime = (TextView) findViewById(R.id.time);
	        TextView tvATime = (TextView) findViewById(R.id.atime);
	        TextView tvPrice = (TextView) findViewById(R.id.price);
	       // TextView tvName = (TextView) findViewById(R.id.name);
	        //TextView tvIC = (TextView) findViewById(R.id.IC);
	        //TextView tvCon = (TextView) findViewById(R.id.contact);
	       
	        

	        Intent i = getIntent();

	        String Date = i.getStringExtra("Date");
	        String tno = i.getStringExtra("tno");
	        String fromw = i.getStringExtra("fromw");
	        String tow = i.getStringExtra("tow");
	        String ftime = i.getStringExtra("ftime");
	        String atime = i.getStringExtra("atime");
	        String price = i.getStringExtra("price");
	       // String name = i.getStringExtra("name");
	        //String ic = i.getStringExtra("ic");
	        //String con = i.getStringExtra("con");
	       

	        tvDate.setText(Date);
	        tvCode.setText(tno);
	        tvFrom.setText(fromw);
	        tvTo.setText(tow);
	        tvTime.setText(ftime);
	        tvATime.setText(atime);
	        tvPrice.setText(price);
	        //tvName.setText(name);
	        //tvIC.setText(ic);
	        //tvCon.setText(con);
}
}