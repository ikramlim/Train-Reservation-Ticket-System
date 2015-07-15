package com.learn2crack;

/**
 * Created by ikram on 19/12/2014.
 */

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class KLSBEHCursorAdapterActivity extends  Activity{
    private KLSBEHDbAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchkls_beh);

        dbHelper = new KLSBEHDbAdapter(this);
        dbHelper.open();

        //Clean all data
        dbHelper.deleteAllCountries();
        //Add some data
        dbHelper.insertSomeCountries();

        //Generate ListView from SQLite Database
        displayListView();

    }

    private void displayListView() {


        Cursor cursor = dbHelper.fetchAllCountries();

        // The desired columns to be bound
        String[] columns = new String[] {
                KLSBEHDbAdapter.KEY_TNO,
                KLSBEHDbAdapter.KEY_FROMW,
                KLSBEHDbAdapter.KEY_TOW,
                KLSBEHDbAdapter.KEY_FROMTIME,
                KLSBEHDbAdapter.KEY_ARRIVALTIME,
                KLSBEHDbAdapter.KEY_PRICE,

        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                R.id.traincode,
                R.id.from,
                R.id.to,
                R.id.time,
                R.id.atime,
                R.id.price
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.train_list_view,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                 //Get the state's capital from this row in the database.
                String countryCode =
                        cursor.getString(cursor.getColumnIndexOrThrow("tno"));
                Toast.makeText(getApplicationContext(),
                        countryCode, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(KLSBEHCursorAdapterActivity.this,  Payment_Form.class);

                //pass the data, first need to fetch the data of TextView.
                TextView tvCode = (TextView) findViewById(R.id.traincode);
                TextView tvFrom = (TextView) findViewById(R.id.from);
                TextView tvTo = (TextView) findViewById(R.id.to);
                TextView tvTime = (TextView) findViewById(R.id.time);
                TextView tvATime = (TextView) findViewById(R.id.atime);
                TextView tvPrice = (TextView) findViewById(R.id.price);

                String str = tvCode.getText().toString();
                String str1 = tvFrom.getText().toString();
                String str2 = tvTo.getText().toString();
                String str3 = tvTime.getText().toString();
                String str4 = tvATime.getText().toString();
                String str5 = tvPrice.getText().toString();

                i.putExtra("Code", str);
                i.putExtra("Form", str1);
                i.putExtra("To", str2);
                i.putExtra("Time", str3);
                i.putExtra("ATime", str4);
                i.putExtra("Price", str5);

                startActivity(i);




            }
        });

        //Filter search bar

        EditText myFilter = (EditText) findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });

        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return dbHelper.fetchCountriesByName(constraint.toString());
            }
        });

    }
}


