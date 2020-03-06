package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView t1;
    private EditText tname;
    private EditText temail;
    private EditText tnumber;
    private Button btwrite;
    private Button btread;
    private Button btupdate;
    private Button btremove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=(TextView)findViewById(R.id.txtshow);

        tname=(EditText)findViewById(R.id.etname);
        temail=(EditText)findViewById(R.id.etemail);
        tnumber=(EditText)findViewById(R.id.etnumber);

        btwrite=(Button)findViewById(R.id.Bwrite);
        btread=(Button)findViewById(R.id.Bread);
        btupdate=(Button)findViewById(R.id.Bupdate);
        btremove=(Button)findViewById(R.id.Bremove);


    }
}
