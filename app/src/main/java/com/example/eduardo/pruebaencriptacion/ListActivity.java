package com.example.eduardo.pruebaencriptacion;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

public class ListActivity extends AppCompatActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lista=(ListView)findViewById(R.id.listView);
        //se obtiene el cursor con todos los datos
        Cursor c =MainActivity.dataSource.getAllRegister();
        //se crea el adapter
        /*SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item,
                c,
                new String[]{DataSource.ColumnNames.USER,DataSource.ColumnNames.PASSWORD},
                new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);*/
        CustomCursorAdapter adapter = new CustomCursorAdapter(this,c);
        lista.setAdapter(adapter);
    }
}
