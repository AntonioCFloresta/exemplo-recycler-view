package br.com.senai.guilherme.exemplorecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CursoItemViewHolder extends RecyclerView.ViewHolder {
    TextView textViewTitulo;
    TextView textViewTurno;

    public CursoItemViewHolder(View itemView) {
        super(itemView);

        textViewTitulo = (TextView) itemView.findViewById(R.id.textViewTitulo);
        textViewTurno = (TextView) itemView.findViewById(R.id.textViewTurno);
    }
}
