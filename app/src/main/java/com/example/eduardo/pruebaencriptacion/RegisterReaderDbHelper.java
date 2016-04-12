package com.example.eduardo.pruebaencriptacion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eduardo on 28/03/2016.
 */
public class RegisterReaderDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PassWords.db";
    public static final int DATABASE_VERSION = 1;

    /**
     * Contructor de la clase RegisterReaderDbHelper
     * @param context es el contexto donde se crearä la bd
     */
    public RegisterReaderDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //se crea la tabla con su campos
        db.execSQL(DataSource.CREATE_SCRIPT);
        //se inserta unos valores de inicio en la tabla
        db.execSQL(DataSource.INSERT_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
