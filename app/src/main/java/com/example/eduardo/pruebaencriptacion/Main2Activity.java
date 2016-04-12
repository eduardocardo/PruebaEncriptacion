package com.example.eduardo.pruebaencriptacion;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {


    private RegisterReaderDbHelper readerDbHelper;
    private EditText id,usuario,pass,upPass;
    private Button search,eliminarRegistro,update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       // Intent intent = getIntent();
       // dataSource = (DataSource)intent.getSerializableExtra("database") ;

        id=(EditText)findViewById(R.id.et_idBuscada);
        usuario =(EditText)findViewById(R.id.et_usuarioBuscado);
        pass=(EditText)findViewById(R.id.et_passBuscada);
        search=(Button)findViewById(R.id.bt_consultar);
        eliminarRegistro=(Button)findViewById(R.id.bt_delete);
        update=(Button)findViewById(R.id.bt_update);
        upPass=(EditText)findViewById(R.id.et_updatePass);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = null;
                //se obtiene un cursor con los datos obtenidos a partir de la id introducida
                c = MainActivity.dataSource.getRegisterById(id.getText().toString());
                if(c != null)
                {
                    //se recorre el cursor obteniendo los datos que contiene
                    while(c.moveToNext())
                    {
                        String u = c.getString(c.getColumnIndex(DataSource.ColumnNames.USER));
                        usuario.setText(u);
                        String p = c.getString(c.getColumnIndex(DataSource.ColumnNames.PASSWORD));
                        pass.setText(p);
                    }
                }
            }
        });

        eliminarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean registroBorrado = MainActivity.dataSource.deleteRegister(id.getText().toString());
                if(registroBorrado)
                {
                    Toast.makeText(Main2Activity.this,"Registro eliminado",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Main2Activity.this,"Ese registro no ha podido ser eliminado",Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean registroModificado = MainActivity.dataSource.updateRegister(id.getText().toString(),upPass.getText().toString());
                if(registroModificado)
                {
                    Toast.makeText(Main2Activity.this,"El registro ha sido modificado",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Main2Activity.this,"El registro no ha sido modificado",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }





}
