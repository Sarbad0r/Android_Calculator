package com.example.onemoewtimebdcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.onemoewtimebdcalc.database.DataBaseManager;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private ListView listView;
    private DataBaseManager dataBaseManager;
    private final ArrayList<String> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_result_activty );

        listView = findViewById( R.id.listView );
        dataBaseManager = new DataBaseManager( this );


    }

    @Override
    protected void onResume() {
        super.onResume();

        try
        {
            dataBaseManager.openDb();
            arr.addAll( dataBaseManager.readFromDb() );
            ArrayAdapter arrayAdapter = new ArrayAdapter( this , android.R.layout.simple_list_item_1, arr );

            listView.setAdapter( arrayAdapter );

            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    dataBaseManager.deleteFromDb( arr.get(i));
                    Intent intent = new Intent(ResultActivity.this , MainActivity.class);
                    startActivity( intent );
                    Toast.makeText( ResultActivity.this, "Deleted", Toast.LENGTH_SHORT ).show();
                }
            } );

        }
        catch (Exception e){
            Toast.makeText( this, "Error in database", Toast.LENGTH_SHORT ).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBaseManager.CloseDb();
    }
}