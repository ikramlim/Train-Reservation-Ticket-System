package com.learn2crack;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.app.AlertDialog;
import android.widget.AdapterView;

import android.content.DialogInterface;
import android.widget.TextView;

/**
 * Created by ikram on 17/12/2014.
 */
public class Spinnerdata extends Activity
{

    private EditText edtDate;
    Calendar c;
    int val;
    Button btnSearch;
    //UI DatePickerDialog
    Spinner sp1;
    Spinner sp2;
    Spinner sp3;

    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinnerdata);

        edtDate = (EditText) findViewById(R.id.et1);
        c = Calendar.getInstance();

        //setup button listener
        btnSearch = (Button) findViewById(R.id.button);
        btnSearch.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View v) {
                                             if (sp1.getSelectedItem().toString().equals("KL Sentral") && sp2.getSelectedItem().toString().equals("Ipoh")) {
                                                 kls_ipoh();
                                             } else if (sp1.getSelectedItem().toString().equals("KL Sentral") && sp2.getSelectedItem().toString().equals("Batu Gajah")) {
                                                 kls_bg();
                                             } else if (sp1.getSelectedItem().toString().equals("KL Sentral") && sp2.getSelectedItem().toString().equals("Kampar")) {
                                                 kls_kpr();
                                             } else if (sp1.getSelectedItem().toString().equals("KL Sentral") && sp2.getSelectedItem().toString().equals("Tapah Road")) {
                                                 kls_tpr();
                                             } else if (sp1.getSelectedItem().toString().equals("KL Sentral") && sp2.getSelectedItem().toString().equals("Sungkai")) {
                                                 kls_ski();
                                             } else if (sp1.getSelectedItem().toString().equals("KL Sentral") && sp2.getSelectedItem().toString().equals("Slim River")) {
                                                 kls_slr();
                                             } else if (sp1.getSelectedItem().toString().equals("KL Sentral") && sp2.getSelectedItem().toString().equals("Behrang")) {
                                                 kls_beh();
                                             } else if (sp1.getSelectedItem().toString().equals("KL Sentral") && sp2.getSelectedItem().toString().equals("Tanjung Malim")) {
                                                 kls_tgm();
                                             } else if (sp1.getSelectedItem().toString().equals("KL Sentral") && sp2.getSelectedItem().toString().equals("Kuala Kubu Baru")) {
                                                 kls_kkb();
                                             } else if (sp1.getSelectedItem().toString().equals("KL Sentral") && sp2.getSelectedItem().toString().equals("Rawang")) {
                                                 kls_raw();
                                             } else if (sp1.getSelectedItem().toString().equals("KL Sentral") && sp2.getSelectedItem().toString().equals("Sungai Buloh")) {
                                                 kls_sgb();
                                             } else if (sp1.getSelectedItem().toString().equals("KL Sentral") && sp2.getSelectedItem().toString().equals("Kepong Sentral")) {
                                                 kls_kgs();
                                             } else if (sp1.getSelectedItem().toString().equals("KL Sentral") && sp2.getSelectedItem().toString().equals("Kuala Lumpur")) {
                                                 kls_klo();
                                             } else if (sp1.getSelectedItem().toString().equals("KL Sentral") && sp2.getSelectedItem().toString().equals("KL Sentral")) {
                                                 kls_kls();
                                             }
                                         }
                                     }

        );


        //Write spinner and arrayAdapter for list the String stored in array
        sp1 = (Spinner) findViewById(R.id.sp1);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.to_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);

        sp2 = (Spinner) findViewById(R.id.sp2);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(
                this, R.array.to_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter2);

        sp3 = (Spinner) findViewById(R.id.sp3);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(
                this, R.array.guest_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapter3);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(val);


            }

        };


        edtDate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                new DatePickerDialog(Spinnerdata.this, date,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
                val = 1;


            }



    public void onItemClick(AdapterView<?> listView, View view,
                            int position, long id) {
        Intent i = new Intent(Spinnerdata.this, Payment_Form.class);
        //pass the data, first need to fetch the data of TextView.
        edtDate = (EditText) findViewById(R.id.et1);

        String date = edtDate.getText().toString();
        i.putExtra("Date", date);

        startActivity(i);
    }

    });



    }



    private void updateLabel(int val) {

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        if(val==1)
            edtDate.setText(sdf.format(c.getTime()));
        //else
        //edtDate2.setText(sdf.format(myCalendar.getTime()));

    }

    private void kls_ipoh()
    {
        Intent sc = new Intent (this, KLSIPOHCursorAdapterActivity.class);
        startActivity(sc);
    }

    private void kls_bg()
    {
        Intent sc = new Intent (this, KLSBGCursorAdapterActivity.class);
        startActivity(sc);
    }

    private void kls_kpr()
    {
        Intent sc = new Intent (this, KLSKPRCursorAdapterActivity.class);
        startActivity(sc);
    }
    private void kls_tpr()
    {
        Intent sc = new Intent (this, KLSTPRCursorAdapterActivity.class);
        startActivity(sc);
    }
    private void kls_ski()
    {
        Intent sc = new Intent (this, KLSSKICursorAdapterActivity.class);
        startActivity(sc);
    }
    private void kls_slr()
    {
        Intent sc = new Intent (this, KLSSLRCursorAdapterActivity.class);
        startActivity(sc);
    }
    private void kls_beh()
    {
        Intent sc = new Intent (this, KLSBEHCursorAdapterActivity.class);
        startActivity(sc);
    }
    private void kls_tgm()
    {
        Intent sc = new Intent (this, KLSTGMCursorAdapterActivity.class);
        startActivity(sc);
    }
    private void kls_kkb()
    {
        Intent sc = new Intent (this, KLSKKBCursorAdapterActivity.class);
        startActivity(sc);
    }
    private void kls_raw()
    {
        Intent sc = new Intent (this, KLSRAWCursorAdapterActivity.class);
        startActivity(sc);
    }
    private void kls_sgb()
    {
        Intent sc = new Intent (this, KLSSGBCursorAdapterActivity.class);
        startActivity(sc);
    }
    private void kls_kgs()
    {
        Intent sc = new Intent (this, KLSKGSCursorAdapterActivity.class);
        startActivity(sc);
    }
    private void kls_klo()
    {
        Intent sc = new Intent (this, KLSKLOCursorAdapterActivity.class);
        startActivity(sc);
    }
    private void kls_kls()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("Your Title");

        // set dialog message
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        Spinnerdata.this.finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }






}
