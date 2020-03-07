package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etname;
    private EditText etnumber;
    private EditText etemail;

    private Button write;
    private Button read;
    private Button update;
    private Button remove;

    private TextView show;

    MyHelper myHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etname=(EditText)findViewById(R.id.etname);
        etnumber=(EditText)findViewById(R.id.etnumber);
        etemail=(EditText)findViewById(R.id.etemail);

        write=(Button)findViewById(R.id.Bwrite);
        read=(Button)findViewById(R.id.Bread);
        update=(Button)findViewById(R.id.Bupdate);
        remove=(Button)findViewById(R.id.Bremove);

        write.setOnClickListener(this);
        read.setOnClickListener(this);
        update.setOnClickListener(this);
        remove.setOnClickListener(this);

        show=(TextView)findViewById(R.id.txtshow);

        myHelper=new MyHelper(this);
    }

    @Override
    public void onClick(View v) {
        String name;
        String phone;
        String email;
        SQLiteDatabase db;
        ContentValues values;
        switch (v.getId()){
            case R.id.Bwrite:
                name=etname.getText().toString();
                phone=etnumber.getText().toString();
                email=etemail.getText().toString();
                db=myHelper.getReadableDatabase();
                values=new ContentValues();
                if(name.isEmpty()||phone.isEmpty()||email.isEmpty()){
                    Toast.makeText(this,"Input is empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    values.put("name",name);
                    values.put("number",phone);
                    values.put("email",email);
                    db.insert("info",null,values);
                    Toast.makeText(this,"write success",Toast.LENGTH_SHORT).show();
                }
                db.close();
                break;
            case R.id.Bread:
                db=myHelper.getWritableDatabase();
                Cursor cursor=db.query("info",null,null,null,null,null,null,null);
                if(cursor.getCount()==0){
                    Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();
                }
                else{
                    cursor.moveToFirst();
                    show.setText("name:"+cursor.getString(1)+"email:"+cursor.getString(2)+"number:"+cursor.getString(3));
                }
                while (cursor.moveToNext()){
                    show.append("\n"+"name:"+cursor.getString(1)+"email:"+cursor.getString(2)+"number:"+cursor.getString(3));
                }
                cursor.close();
                db.close();
                break;
            case R.id.Bupdate:
                db=myHelper.getWritableDatabase();
                values=new ContentValues();
                values.put("number",etnumber.getText().toString());
                db.update("info",values,"name=?",new String[]{etname.getText().toString()});
                Toast.makeText(this,"update success",Toast.LENGTH_SHORT).show();
                db.close();
                break;
            case R.id.Bremove:
                db=myHelper.getWritableDatabase();
                db.delete("info",null,null);
                Toast.makeText(this,"remove success",Toast.LENGTH_SHORT).show();
                db.close();
                show.setText("no records");
                break;
        }
    }
}
