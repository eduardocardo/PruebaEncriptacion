package com.example.eduardo.pruebaencriptacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class criptoActivity extends AppCompatActivity {

    private EditText msj,textEnc,textDesenc;
    private Button enc,desenc;
    private static final String password ="1234";
    private static final String salt ="estoesunpruebamanes";
    private SecretKey secretKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cripto);

        msj =(EditText)findViewById(R.id.et_encript);
        textEnc=(EditText)findViewById(R.id.et_textEncript);
        textDesenc=(EditText)findViewById(R.id.et_textDesencript);
        enc=(Button)findViewById(R.id.bt_encript);
        desenc=(Button)findViewById(R.id.bt_desencript);

        char[] bpassword = password.toCharArray();
        byte[] bsalt = salt.getBytes();
        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(bpassword, bsalt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }


        enc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msjCodificado = null;
                try {
                    msjCodificado= AES.encriptar(msj.getText().toString(),secretKey);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                textEnc.setText(msjCodificado);
            }
        });

        desenc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msjDeco = null;
                try {
                    msjDeco = AES.desencriptar(textEnc.getText().toString(),secretKey);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                textDesenc.setText(msjDeco);
            }
        });

    }
}
