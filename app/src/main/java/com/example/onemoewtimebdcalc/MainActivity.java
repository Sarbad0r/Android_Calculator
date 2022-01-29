package com.example.onemoewtimebdcalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onemoewtimebdcalc.database.DataBaseManager;
import com.example.onemoewtimebdcalc.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {


    private EditText Fnumber , Snumber , Symbol;
    private TextView textAnswer;
    private Button button_check;
    private DataBaseManager dataBaseManager;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Fnumber = findViewById( R.id.FnumberEditText );
        Snumber = findViewById( R.id.SnumberEditText );
        Symbol = findViewById( R.id.editSymbol );
        textAnswer = findViewById( R.id.textResults );
        button_check = findViewById( R.id.button_check );
        mainViewModel = new ViewModelProvider(this).get( MainViewModel.class );

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( Color.BLACK));
        dataBaseManager = new DataBaseManager( this );

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();

        button_check.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    if (Fnumber.getText().toString().equals( "" ) || Snumber.getText().toString().equals( "" ))
                    {
                        Toast.makeText( MainActivity.this, "Empty field", Toast.LENGTH_SHORT ).show();
                    }
                    else
                    {

                        double result = 0.0;
                        switch (Symbol.getText().toString())
                        {
                            case "+":
                                result = Double.parseDouble( Fnumber.getText().toString() ) + Double.parseDouble( Snumber.getText().toString() );
                                break;
                            case "-":
                                result = Double.parseDouble( Fnumber.getText().toString() ) - Double.parseDouble( Snumber.getText().toString() );
                                break;
                            case "*":
                                result = Double.parseDouble( Fnumber.getText().toString() ) * Double.parseDouble( Snumber.getText().toString() );
                                break;
                            case "/":
                                result = Double.parseDouble( Fnumber.getText().toString() ) / Double.parseDouble( Snumber.getText().toString() );
                                break;

                            default:
                                Toast.makeText( MainActivity.this, "Error with symbol", Toast.LENGTH_SHORT ).show();
                                break;
                        }
                        String resInString = Fnumber.getText().toString() + " " + Symbol.getText().toString() + " " + Snumber.getText().toString() + " = " + result;
                        mainViewModel.getData( result );
                        dataBaseManager.openDb();
                        dataBaseManager.insertDb( resInString );
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText( MainActivity.this, "Failed App", Toast.LENGTH_SHORT ).show();
                }
            }
        } );

        mainViewModel.setData.observe( this , it->{
            textAnswer.setText( it.toString() );
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.main_menu, menu );
        return super.onCreateOptionsMenu( menu );
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_res:
                intent();
                break;
        }
        return super.onOptionsItemSelected( item );
    }

    private void intent()
    {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        startActivity( intent );
    }
}