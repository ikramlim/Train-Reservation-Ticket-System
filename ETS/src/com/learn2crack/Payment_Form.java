package com.learn2crack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/**
 * Created by ikram on 21/12/2014.
 */
public class Payment_Form extends Activity {
	
	String etsno;
	String fromm;
	String tom;
	String timew;
	String arrtimew;
	String prices;
	String name;
	String icno;
	String conno;
	InputStream is=null;
	String result=null;
	String line=null;
	int code;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);
        

       
        final TextView tvDate = (TextView) findViewById(R.id.date);
        final TextView tvCode = (TextView) findViewById(R.id.traincode);
        final TextView tvFrom = (TextView) findViewById(R.id.from);
        final TextView tvTo = (TextView) findViewById(R.id.to);
        final TextView tvTime = (TextView) findViewById(R.id.time);
        final TextView tvATime = (TextView) findViewById(R.id.atime);
        final TextView tvPrice = (TextView) findViewById(R.id.price);
        
       final EditText ename = (EditText) findViewById (R.id.editText);
       final EditText eic = (EditText) findViewById (R.id.editText2);
       final EditText econ = (EditText) findViewById (R.id.editText3);

        Intent i = getIntent();

        String Date = i.getStringExtra("Date");
        String tno = i.getStringExtra("tno");
        String fromw = i.getStringExtra("fromw");
        String tow = i.getStringExtra("tow");
        String ftime = i.getStringExtra("ftime");
        String atime = i.getStringExtra("atime");
        String price = i.getStringExtra("price");

        tvDate.setText(Date);
        tvCode.setText(tno);
        tvFrom.setText(fromw);
        tvTo.setText(tow);
        tvTime.setText(ftime);
        tvATime.setText(atime);
        tvPrice.setText(price);

        Button insert=(Button) findViewById(R.id.button2);
        
        insert.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etsno = tvCode.getText().toString();
				fromm= tvFrom.getText().toString();
				tom= tvTo.getText().toString();
				timew= tvTime.getText().toString();
				arrtimew= tvATime.getText().toString();
				prices= tvPrice.getText().toString();
				name= ename.getText().toString();
				icno= eic.getText().toString();
				conno= econ.getText().toString();
				
				insert();
			}
        });
    }

			private void insert() {
				// TODO Auto-generated method stub
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				 
			   	nameValuePairs.add(new BasicNameValuePair("ets_no",etsno));
			   	nameValuePairs.add(new BasicNameValuePair("frm",fromm));
			   	nameValuePairs.add(new BasicNameValuePair("tow",tom));
			   	nameValuePairs.add(new BasicNameValuePair("timew",timew));
			   	nameValuePairs.add(new BasicNameValuePair("arrival_timew",arrtimew));
			   	nameValuePairs.add(new BasicNameValuePair("price",prices));
			   	nameValuePairs.add(new BasicNameValuePair("name",name));
			   	nameValuePairs.add(new BasicNameValuePair("ic_no",icno));
			   	nameValuePairs.add(new BasicNameValuePair("contact_no",conno));
			    	
			    	try
			    	{
					HttpClient httpclient = new DefaultHttpClient();
				        HttpPost httppost = new HttpPost("http://10.0.2.2/fyp.php");
				        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				        HttpResponse response = httpclient.execute(httppost); 
				        HttpEntity entity = response.getEntity();
				        is = entity.getContent();
				        Log.e("pass 1", "connection success ");
				}
			        catch(Exception e)
				{
			        	Log.e("Fail 1", e.toString());
				    	Toast.makeText(getApplicationContext(), "Paid, Thank for support.",
						Toast.LENGTH_LONG).show();
				}     
			        
			        try
			        {
			            BufferedReader reader = new BufferedReader
						(new InputStreamReader(is,"iso-8859-1"),8);
			            StringBuilder sb = new StringBuilder();
			            while ((line = reader.readLine()) != null)
				    {
			                sb.append(line + "\n");
			            }
			            is.close();
			            result = sb.toString();
				    Log.e("pass 2", "connection success ");
				}
			        catch(Exception e)
				{
			            Log.e("Fail 2", e.toString());
				}     
			       
				try
				{
			            JSONObject json_data = new JSONObject(result);
			            code=(json_data.getInt("code"));
						
			            if(code==1)
			            {
					Toast.makeText(getBaseContext(), "Inserted Successfully",
						Toast.LENGTH_SHORT).show();
			            }
			            else
			            {
					 Toast.makeText(getBaseContext(), "Sorry, Try Again",
						Toast.LENGTH_LONG).show();
			            }
				}
				catch(Exception e)
				{
			            Log.e("Fail 3", e.toString());
				}
			    }
			
			    @Override
			    public boolean onCreateOptionsMenu(Menu menu) {
			        getMenuInflater().inflate(R.menu.menu_main, menu);
			        return true;
			    }    
	
				
				
				
				
			}
		
    

    
    
    	
    
    
    
    /*public void addButtonClickListener() {


        Button btn1;
        btn1 = (Button) findViewById(R.id.button2);

        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg) {


                Intent i = new Intent(Payment_Form.this, TicketTrain.class);

                //pass the data, first need to fetch the data of EditText.

                EditText edtName = (EditText) findViewById(R.id.editText);
                EditText edtIC = (EditText) findViewById(R.id.editText2);
                EditText edtCon = (EditText) findViewById(R.id.editText3);


                String name = edtName.getText().toString();
                String ic = edtIC.getText().toString();
                String con = edtCon.getText().toString();


                i.putExtra("name", name);
                i.putExtra("ic", ic);
                i.putExtra("con", con);


                startActivity(i);


            }
        });
    }*/

