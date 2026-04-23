package com.sobral.agendaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastrar, btnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        //Chamando a classe superior
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//Modificando a tela, reconhecendo os botões

        this.btnCadastrar = findViewById(R.id.btnCadastrar);//Botão Cadastrar - Referência
        this.btnConsultar = findViewById(R.id.btnConsultar);//Botão Consultar

        //Ativar os botões
        this.btnCadastrar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CadastrarActivity.class);
            startActivity(intent);
        });//fim do botão cadastrar

        this.btnConsultar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ConsultarActivity.class);
            startActivity(intent);
        });//fim do botão consultar
    }//fim do onCreate
}//fim do main activity
