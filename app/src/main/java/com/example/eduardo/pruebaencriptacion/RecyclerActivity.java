package com.example.eduardo.pruebaencriptacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    /*
Declarar instancias globales
*/
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        List items = new ArrayList();
        items.add(new Categoria("Correo",R.drawable.gmail));
        items.add(new Categoria("Red Social",R.drawable.twitter));
        items.add(new Categoria("Game",R.drawable.game));
        items.add(new Categoria("Internet", R.drawable.internet));

        // Obtener el Recycler
        recycler = (RecyclerView)findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new GridLayoutManager(this,2);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new CategoriaAdapter(items);
        recycler.setAdapter(adapter);


    }
}
