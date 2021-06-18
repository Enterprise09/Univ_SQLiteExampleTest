package com.example.sqliteexampletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    Button btn_add, btn_update, btn_delete, btn_query;
    TextView tv_display;
    Cursor cursor;
    int ctn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_query = (Button) findViewById(R.id.btn_query);
        tv_display = (TextView) findViewById(R.id.tv_display);
        tv_display.setMovementMethod(new ScrollingMovementMethod());

        btn_add.setOnClickListener(listener);
        btn_update.setOnClickListener(listener);
        btn_delete.setOnClickListener(listener);
        btn_query.setOnClickListener(listener);

    }//end of onCreate()

    public int getDataCount(){
        int result;
        cursor = db.rawQuery("SELECT * FROM mydb", null);
        result = cursor.getCount();
        return result;
    }//end of getDataCount()

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int btnId = v.getId();
            MyDBOpenHelper helper = new MyDBOpenHelper(getApplicationContext(), "mydb", null, 1);
            db = helper.getWritableDatabase();
            switch (btnId) {
                case R.id.btn_add:
                    Toast.makeText(getApplicationContext(), "Add", Toast.LENGTH_SHORT).show();
                    db.execSQL("INSERT INTO mydb VALUES(null, 20010654, '홍길동', '컴퓨터', 18, 3);");
                    ctn = getDataCount();
                    tv_display.setText("레코드 추가 : " + ctn);
                    break;
                case R.id.btn_update:
                    Toast.makeText(getApplicationContext(), "Update", Toast.LENGTH_SHORT).show();
                    db.execSQL("UPDATE mydb SET name = '고길동'");
                    ctn = getDataCount();
                    tv_display.setText("레코드 갱신 : " + ctn);
                    break;
                case R.id.btn_delete:
                    Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
                    ctn = getDataCount();
                    db.execSQL("DELETE FROM mydb");
                    tv_display.setText("삭제된 레코드 수 : " + ctn);
                    break;
                case R.id.btn_query:
                    Toast.makeText(getApplicationContext(), "Query", Toast.LENGTH_SHORT).show();
                    int id;
                    int number;
                    int age;
                    int grade;
                    String name;
                    String department;
                    cursor = db.rawQuery("SELECT * FROM mydb", null);
                    ctn = cursor.getCount();
                    tv_display.setText("");
                    while(cursor.moveToNext()){
                        id = cursor.getInt(0);
                        number = cursor.getInt(1);
                        name = cursor.getString(2);
                        department = cursor.getString(3);
                        age = cursor.getInt(4);
                        grade = cursor.getInt(5);

                        tv_display.append("id : " + id + "\n");
                        tv_display.append("number : " + number + "\n");
                        tv_display.append("name : " + name + "\n");
                        tv_display.append("department : " + department + "\n");
                        tv_display.append("age : " + age + "\n");
                        tv_display.append("grade : " + grade + "\n");
                        tv_display.append("-------------------\n");
                    }
                    tv_display.append("Total : " + ctn);
                    break;
            }//end of switch
        }//end of onClick()
    };//end of listener
}//end of MainActivity class