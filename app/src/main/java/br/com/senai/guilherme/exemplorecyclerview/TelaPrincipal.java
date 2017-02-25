package br.com.senai.guilherme.exemplorecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class TelaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        RecyclerView recyclerViewCursos = (RecyclerView) findViewById(R.id.recyclerViewCursos);
        recyclerViewCursos.setLayoutManager(new LinearLayoutManager(this));

        CursoItemRecyclerAdapter mAdapter = new CursoItemRecyclerAdapter(buscarCursos());
        recyclerViewCursos.setAdapter(mAdapter);
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
}
