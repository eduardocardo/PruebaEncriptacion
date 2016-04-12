package com.example.eduardo.pruebaencriptacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by Eduardo on 28/03/2016.
 * Clase que permite crear el esquema con la estructura de la base de datos
 */
public class DataSource{

    //nombre de la tabla
    public static final String TABLE_NAME = "Datos";
    //indica el tipo de dato
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";



    //clase interna que contiene los campos de la tabla
    public  static abstract class ColumnNames
    {
        public static final String ID_REGISTER = BaseColumns._ID;
        public static final String USER ="Usuario";
        public static final String PASSWORD ="Contraseña";
        public static final String TYPE = "Tipo";
        public static final String SUB_TYPE ="Subtipo";
    }

    //script de la creación de la tabla
    public static final String CREATE_SCRIPT = "create table " + TABLE_NAME + "("
            + ColumnNames.ID_REGISTER + " " + INT_TYPE + " primary key autoincrement,"
            + ColumnNames.USER + " " + STRING_TYPE + " not null,"
            + ColumnNames.PASSWORD + " " + STRING_TYPE + " not null,"
            + ColumnNames.TYPE + " " + STRING_TYPE + " not null,"
            +ColumnNames.SUB_TYPE + " " + STRING_TYPE + ")";

    //script de insercion de datos iniciales
    public static final  String INSERT_SCRIPT ="insert into " + TABLE_NAME +
            " values(null," + "\"holaaaaaa\"," +"\"1234\"," +"\"Correo\"," +"\"Gmail\")";

    private RegisterReaderDbHelper readerDBHelper;
    private SQLiteDatabase database;
    private Context context;
    private static DataSource sInstance;

    /**
     * Contructor de la clase DataSource
     * @param context es el contexto donde se crearä la base de datos
     */
    private DataSource(Context context)
    {
        readerDBHelper = new RegisterReaderDbHelper(context);
        database = readerDBHelper.getWritableDatabase();
        this.context = context;
    }

    public static DataSource getInstance(Context context)
    {
        if (sInstance == null) {
            sInstance = new DataSource(context.getApplicationContext());
        }
        return sInstance;
    }


    /**
     * Metodo que permite añadir un nuevo registro a la base de datos
     * @param u es el nombre de usuario a añadir
     * @param p es la contraseña
     * @param t es el tipo de registro
     * @param subt es el subtipo de registro
     */
    public void saveNewRegister(String u,String p,String t,String subt)
    {
        //se crea un contenedor de valores que almacenará los datos pasados por parametro
        ContentValues values = new ContentValues();
        //se añaden los datos al contenedor
        values.put(ColumnNames.USER,u);
        values.put(ColumnNames.PASSWORD,p);
        values.put(ColumnNames.TYPE,t);
        values.put(ColumnNames.SUB_TYPE,subt);

        //se realiza la inserción en la base de datos
        //metodo insert :primer parametro nombre de la tabla;segundo parámetro ->si pones null y el contenedor de valores
        //está vacío entonces no se realiza la insercion de la fila en la tabla;tercer parámetro -> los valores a insertar
         long num = database.insert(TABLE_NAME,null,values);
        if(num != -1) //se comprueba si se ha podido realizar la inserción en la tabla
        {
            Toast.makeText(context,"Los datos han sido guardados",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"Error,no se han podido guardar los datos",Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Metodo que obtiene un cursor que contiene los datos correspondientes al registro cuya id
     * sea igual a la pasada por parametro
     * @param id es la id del registro a buscar
     * @return un cursor con los datos de un registro
     */
    public Cursor getRegisterById(String id)
    {
        Cursor c = null;
        //se crea la consulta a busca
        String query ="select * from " + TABLE_NAME + " where " + ColumnNames.ID_REGISTER + " = ?";
        c = database.rawQuery(query,new String[]{id}); //se obtiene el cursor en funcion del parametro
        return  c;
    }

    /**
     * Metodo que obtiene un cursor con todos los registros que tengan el mismo tipo que el pasado
     * por parametro
     * @param ti es el tipo del que se quieren obtener los registros
     * @return un cursor con todos los registros con ese tipo
     */
    public Cursor getRegistersByType(String ti)
    {
        String query ="select * from " + TABLE_NAME + " where " + ColumnNames.TYPE + " = ?";
        Cursor c = database.rawQuery(query,new String[]{ti});
        return c;
    }


    /**
     * Metodo que elimina un registro de la base de datos segun la id pasada por parámetro
     * @param id es la id del registro a eliminar
     * @return true si se ha podido eliminar el registro,false si no se ha podido
     */
    public boolean deleteRegister(String id)
    {
        boolean registroBorrado = false;
        String selection = ColumnNames.ID_REGISTER + "= ?";
        String [] selectionArgs = new String[]{id};
        int num = database.delete(TABLE_NAME, selection, selectionArgs);
        if(num != 0)
        {
            registroBorrado = true;
        }
        return registroBorrado;
    }

    public boolean updateRegister(String id,String pass)
    {
        boolean registroModificado = false;
        //se crea un contenedor de valores
        ContentValues values = new ContentValues();
        //se añade el valor a modificar
        values.put(ColumnNames.PASSWORD, pass);

        String selection = ColumnNames.ID_REGISTER + "= ?";
        String[] selectionArgs = new String[]{id};

        int num = database.update(TABLE_NAME,values,selection,selectionArgs);
        if(num != 0)
        {
            registroModificado = true;
        }
        return registroModificado;
    }


    public Cursor getAllRegister()
    {
        String query ="select * from " + TABLE_NAME;
        Cursor c = database.rawQuery(query,null);
        return c;
    }
}
