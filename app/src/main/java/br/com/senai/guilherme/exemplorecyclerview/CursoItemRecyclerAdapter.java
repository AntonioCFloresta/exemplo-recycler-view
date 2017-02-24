package br.com.senai.guilherme.exemplorecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CursoItemRecyclerAdapter extends RecyclerView.Adapter<CursoItemViewHolder> {
    private List<CursoItem> mLista;

    public CursoItemRecyclerAdapter(List<CursoItem> lista) {
        mLista = lista;
    }

    @Override
    public CursoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_curso_item, parent, false);
        return new CursoItemViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(CursoItemViewHolder holder, int position) {
        CursoItem cursoItem = mLista.get(position);
        holder.textViewTitulo.setText(cursoItem.getTitulo());
        holder.textViewTurno.setText(cursoItem.getTurno());
    }

    @Override
    public int getItemCount() {
        return mLista.size();
    }
}
