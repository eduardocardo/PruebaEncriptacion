package com.example.eduardo.pruebaencriptacion;

import android.widget.ImageView;

/**
 * Created by Eduardo on 10/04/2016.
 */
public class Categoria {

    private String titulo;
    private int icono;

    public Categoria(String t,int i)
    {
        titulo = t;
        icono = i;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }
}
