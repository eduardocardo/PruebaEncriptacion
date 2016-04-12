package com.example.eduardo.pruebaencriptacion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText pass,rePass;
    private TextView info,recontra;
    private Button check;
    private DataSource db;
    public static  final String validadorContraseña = "password valido";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pass =(EditText)findViewById(R.id.et_contra);
        info =(TextView)findViewById(R.id.tv_aviso);
        check=(Button)findViewById(R.id.bt_validar);
        rePass=(EditText)findViewById(R.id.et_repContra);
        recontra=(TextView)findViewById(R.id.tv_repContra);
        db = DataSource.getInstance(this);

        if(!usuarioRegistrado())
        {
            info.setText("Esta contraseña nunca podrá ser recuperada");

        }
        else
        {
            info.setText("");
            rePass.setVisibility(View.INVISIBLE);
            recontra.setVisibility(View.INVISIBLE);
        }

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //se comprueba que el campo contraseña no esta vacio
                if(pass.getText().length() > 0 && !pass.getText().toString().isEmpty())
                {
                    if(!usuarioRegistrado())
                    {
                        if(rePass.getText().length() > 0 && !rePass.getText().toString().isEmpty())
                        {
                            //se comprueba que las dos contraseñas introducidas son iguales
                            if(pass.getText().toString().equals(rePass.getText().toString()))
                            {
                                //se graba en la bd el texto "password valido" que servirá para comprobar que
                                //la contraseña es correcta
                                try {
                                    String texto = AES.encriptar(validadorContraseña,AES.generarKey(pass.getText().toString()));
                                    db.saveNewRegister("kiyo",texto,"scush","");
                                    //se indica en la sharedpreferences que el usuario se ha registrado
                                    registrarUsuario();
                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Introduce la misma contraseña en ambos campos",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"Vuelve a introducir la contraseña",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else //el usuario ya esta registrado,se comprueba que la contraseña es correcta
                    {
                        Cursor c = db.getRegisterById("4");
                        while(c.moveToNext())
                        {

                            try {
                                String validador = c.getString(c.getColumnIndex(DataSource.ColumnNames.PASSWORD));
                                String decodificado = AES.desencriptar(validador,AES.generarKey(pass.getText().toString()));
                                if(validadorContraseña.equals(decodificado))
                                {

                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    intent.putExtra("pass",decodificado);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this,"La contraseña no es correcta",Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Introduce una contraseña",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public boolean usuarioRegistrado()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("registro",false);
    }

    public void registrarUsuario()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("registro",true);
        editor.commit();
    }
}
