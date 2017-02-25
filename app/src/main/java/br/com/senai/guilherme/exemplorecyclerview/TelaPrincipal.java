package br.com.senai.guilherme.exemplorecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class TelaPrincipal extends AppCompatActivity {
    CursoItemRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        RecyclerView recyclerViewCursos = (RecyclerView) findViewById(R.id.recyclerViewCursos);
        recyclerViewCursos.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CursoItemRecyclerAdapter(buscarCursos());
        recyclerViewCursos.setAdapter(mAdapter);

        recyclerViewCursos.addOnItemTouchListener(new RecyclerOnItemClickListener(this, new RecyclerOnItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                onItemClicado(position);
            }
        }));
    }

    private ArrayList<CursoItem> buscarCursos() {
        ArrayList<CursoItem> lista = new ArrayList<>();

        int i = 1;
        do {
            CursoItem curso = new CursoItem();
            curso.setId(i);
            curso.setTitulo("Curso " + i);

            if (i % 2 == 1) {
                curso.setTurno("Matutino");
            } else {
                curso.setTurno("Vespertino");
            }

            lista.add(curso);

            i++;
        } while (i < 10);

        return lista;
    }

    private void onItemClicado(int position) {
        CursoItem cursoItem = mAdapter.getItem(position);

        Toast.makeText(getApplicationContext(), cursoItem.getTitulo(), Toast.LENGTH_LONG).show();
    }
}
