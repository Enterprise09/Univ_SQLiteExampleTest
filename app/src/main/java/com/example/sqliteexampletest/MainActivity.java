package com.example.sqliteexampletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String CONTENT_URI = "content://com.example.sqliteexampletest/mydb";

    Button btn_add, btn_update, btn_delete, btn_query;
    TextView tv_display;
    public ContentResolver cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_query = (Button) findViewById(R.id.btn_query);
        tv_display = (TextView) findViewById(R.id.tv_display);

        btn_add.setOnClickListener(listener);
        btn_update.setOnClickListener(listener);
        btn_delete.setOnClickListener(listener);
        btn_query.setOnClickListener(listener);

        cr = getContentResolver();

    }//end of onCreate()

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int btnId = v.getId();
            ContentValues row = new ContentValues();
            Uri content_uri = Uri.parse(CONTENT_URI);
            switch (btnId) {
                case R.id.btn_add:
                    Toast.makeText(getApplicationContext(), "Add", Toast.LENGTH_SHORT).show();
                    row.put("number", 200106054);
                    row.put("name", "홍길동");
                    row.put("department", "컴퓨터");
                    row.put("age", 18);
                    row.put("grade", 3);
                    cr.insert(content_uri, row);
                    tv_display.setText("레코드 추가 : 1");
                    break;
                case R.id.btn_update:
                    Toast.makeText(getApplicationContext(), "Update", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_delete:
                    Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_query:
                    Toast.makeText(getApplicationContext(), "Query", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };//end of listener
}//end of MainActivity class