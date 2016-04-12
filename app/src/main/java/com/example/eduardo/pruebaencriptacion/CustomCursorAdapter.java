package com.example.eduardo.pruebaencriptacion;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Eduardo on 30/03/2016.
 */
public class CustomCursorAdapter extends CursorAdapter {
    private Cursor cursor;

    public CustomCursorAdapter(Context context,Cursor c)
    {
        super(context,c);
        cursor = c;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.customlayout, null, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


       TextView user =(TextView)view.findViewById(R.id.tv_userList);

       user.setText(cursor.getString(cursor.getColumnIndex(DataSource.ColumnNames.USER)));
       TextView pass =(TextView)view.findViewById(R.id.tv_passList);
        pass.setText(cursor.getString(cursor.getColumnIndex(DataSource.ColumnNames.PASSWORD)));
        ImageView image =(ImageView)view.findViewById(R.id.iv_List);
        image.setImageResource(R.drawable.gmail);

    }


}
