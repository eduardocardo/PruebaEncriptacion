package com.example.eduardo.pruebaencriptacion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Eduardo on 10/04/2016.
 */
public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaHolder> {

    private List<Categoria> items;



    public class CategoriaHolder extends RecyclerView.ViewHolder
    {
        private TextView titulo;
        private ImageView image;

        public CategoriaHolder(View v)
        {
            super(v);
            titulo =(TextView)v.findViewById(R.id.tv_titu);
            image =(ImageView)v.findViewById(R.id.iv_ico);
        }

    }

    /**
     * Constructos de la clase CategoriaAdapter
     * @param cate
     */
    public CategoriaAdapter(List<Categoria> cate)
    {
        items = cate;
    }

    @Override
    public CategoriaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_catego, parent, false);
        return new CategoriaHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoriaHolder holder, int position) {
        holder.image.setImageResource(items.get(position).getIcono());
        holder.titulo.setText(items.get(position).getTitulo());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
