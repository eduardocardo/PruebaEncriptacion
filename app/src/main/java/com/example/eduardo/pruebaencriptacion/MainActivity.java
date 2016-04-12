package com.example.eduardo.pruebaencriptacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {



    private EditText usuario,contra,tipo,subtipo;
    private Button insertarRegistro,buscar,listar;
    private Key publicKey;
    private Key privateKey;
    public  static DataSource dataSource;
    private Key key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usuario=(EditText)findViewById(R.id.et_user);
        contra=(EditText)findViewById(R.id.et_pass);
        tipo=(EditText)findViewById(R.id.et_type);
        subtipo=(EditText)findViewById(R.id.et_subtype);
        insertarRegistro=(Button)findViewById(R.id.bt_add);
        buscar =(Button)findViewById(R.id.bt_buscar);
        listar=(Button)findViewById(R.id.bt_list);


       dataSource = DataSource.getInstance(this);
        //se crea clave publica y privada
       // key = AES.generateKey();


      /*  try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.generateKeyPair();
            publicKey = kp.getPublic();
            privateKey = kp.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/


        /*codif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //se realiza la codificacion
                //String msjCodificado = RSA.encrypt(publicKey,boxMsj.getText().toString());
                String msjCodificado = null;
                try {
                    msjCodificado = AES.encriptar(boxMsj.getText().toString(), key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                boxCod.setText(msjCodificado);
            }
        });

        decodif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               *//* byte[] decodedBytes = null;
                try {
                    Cipher c = Cipher.getInstance("RSA");
                    c.init(Cipher.DECRYPT_MODE, privateKey);
                    decodedBytes = c.doFinal(boxCod.getText().toString().getBytes());//se codifica
                    msjDeco.setText(Base64.encodeToString(decodedBytes,Base64.DEFAULT));
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                }
                String p = Base64.encodeToString(publicKey.getEncoded(),Base64.DEFAULT);
                String msjDecodificado = RSA.decrypt(privateKey,p);*//*
                String msjDecodificado = null;
                try {
                    msjDecodificado = AES.desencriptar(boxCod.getText().toString(), key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                msjDeco.setText(msjDecodificado);
            }

        });
*/
        insertarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSource.saveNewRegister(usuario.getText().toString(),contra.getText().toString(),
                        tipo.getText().toString(),subtipo.getText().toString());
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(MainActivity.this,Main2Activity.class);
               // inten.putExtra("database",dataSource);
                startActivity(inten);
            }
        });
        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this,ListActivity.class);
                Intent intent = new Intent(MainActivity.this,RecyclerActivity.class);
                startActivity(intent);
            }
        });


    }
}
