package com.sobral.agendaapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ConsultarActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RegistroAdapter adapter;//Referência a classe REGISTROADAPTER
    private List<Registro> lista = new ArrayList<>();//Array List para armazenar os dados no programa
    private FirebaseFirestore db;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        recycler = findViewById(R.id.recyclerRegistros);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RegistroAdapter(lista);
        recycler.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();//Verificar a conexão com a instância do firebase
        carregarDados();//Alimentar a tela
    }//fim do onCreate

    public void carregarDados(){
        db.collection("registro")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    lista.clear();//Esvaziar o Array

                    for(QueryDocumentSnapshot doc : queryDocumentSnapshots){
                        Registro registro = doc.toObject(Registro.class);
                        registro.setId(Integer.parseInt(doc.getId()));//talvez modificar para o id da collection
                        lista.add(registro);
                    }//fim do for

                    adapter.notifyDataSetChanged();
                });
    }//fim do carregarDados


}//fim da consultarActivity
