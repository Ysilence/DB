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

public class MainActivity extends AppCompatActivity {
    private TextView tshow;
    private EditText tname;
    private EditText temail;
    private EditText tnumber;
    private Button btwrite;
    private Button btread;
    private Button btupdate;
    private Button btremove;
    MyHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tshow=(TextView)findViewById(R.id.txtshow);

        tname=(EditText)findViewById(R.id.etname);
        temail=(EditText)findViewById(R.id.etemail);
        tnumber=(EditText)findViewById(R.id.etnumber);

        btwrite=(Button)findViewById(R.id.Bwrite);
        btread=(Button)findViewById(R.id.Bread);
        btupdate=(Button)findViewById(R.id.Bupdate);
        btremove=(Button)findViewById(R.id.Bremove);

        myHelper=new MyHelper(this);

        btwrite.setOnClickListener((View.OnClickListener) this);
        btread.setOnClickListener((View.OnClickListener) this);
        btupdate.setOnClickListener((View.OnClickListener) this);
        btremove.setOnClickListener((View.OnClickListener) this);



    }

    public void onClick(View view)
    {
        String name;
        String email;
        String number;
        SQLiteDatabase db;
        ContentValues values;



        switch(view.getId())
        {
            case R.id.Bwrite:
                name=tname.getText().toString();
                email=temail.getText().toString();
                number=tnumber.getText().toString();
                db=myHelper.getWritableDatabase();
                values=new ContentValues();
                if(name.isEmpty()||number.isEmpty()||email.isEmpty()){
                    Toast.makeText(this,"Input is empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    values.put("name",name);
                    values.put("phone",number);
                    values.put("email",email);
                    db.insert("info",null,values);
                    Toast.makeText(this,"write success",Toast.LENGTH_SHORT).show();
                }
                db.close();
                break;
            case R.id.Bremove:
                db=myHelper.getWritableDatabase();
                db.delete("info",null,null);
                Toast.makeText(this,"delete success",Toast.LENGTH_SHORT);
                db.close();
                tshow.setText("");
                break;
            case R.id.Bupdate:
                db=myHelper.getWritableDatabase();
                values=new ContentValues();
                values.put("number",tnumber.getText().toString());
                db.update("info",values,"name=?",new String[]{tname.getText().toString()});
              //  db.update("info",values,"email=?",new String[]{temail.getText().toString()});
                Toast.makeText(this,"update success",Toast.LENGTH_SHORT);
                db.close();
                break;
            case R.id.Bread:
                db=myHelper.getWritableDatabase();
                Cursor cursor=db.query("info",null,null,null,null,null,null,null);
                if(cursor.getCount()==0)
                {
                    Toast.makeText(this,"There is no data",Toast.LENGTH_SHORT);
                }
                else {
                    cursor.moveToFirst();
                    tshow.setText("name："+cursor.getString(1)+"email:"+cursor.getString(2)+"phone number:"+cursor.getString(3));
                }
                while(cursor.moveToNext())
                {
                    tshow.append("\n"+"name："+cursor.getString(1)+"email:"+cursor.getString(2)+"phone number:"+cursor.getString(3));
                }
                cursor.close();
                db.close();
                break;

        }
    }



}
