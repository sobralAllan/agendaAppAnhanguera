package com.sobral.agendaapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class RegistroAdapter extends RecyclerView.Adapter<RegistroAdapter.ViewHolder> {
    private List<Registro> lista;

    public RegistroAdapter(List<Registro> lista){
        this.lista = lista;
    }//fim do construtor

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView codigo, assunto, data, descricao;
        //Representando esses botões
        Button btEditar, btExcluir;

        //Criar o construtor
        public ViewHolder(View itemView) {
            super(itemView);

            codigo = itemView.findViewById(R.id.txtCodigo);
            assunto = itemView.findViewById(R.id.txtAssunto);
            data = itemView.findViewById(R.id.txtData);
            descricao = itemView.findViewById(R.id.txtDescricao);

            btEditar = itemView.findViewById(R.id.btnEditar);
            btExcluir = itemView.findViewById(R.id.btnExcluir);
        }//fim do viewHolder
    }//fim da static class

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_registro, parent, false);
        return new ViewHolder(view);
    }//fim do método -- preenchimento

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();//Verificar se há uma conexão com o firebase
        Registro registro = lista.get(position);//Pegando a lista que está no banco de dados

        holder.codigo.setText(String.valueOf(registro.getCodigo()));//Coletar o código do BD
        holder.assunto.setText(registro.getAssunto());//Coletando o dado que está no firebase em texto e trazendo na tela
        //Formatar a data - YYYY-MM-DD -> DD-MM-YYYY
        SimpleDateFormat conversao = new SimpleDateFormat("dd/MM/yyyy");
        holder.data.setText(conversao.format(registro.getDataEvento()));//Coletando a data da collection no Firebase
        holder.descricao.setText(registro.getDescricao());//Coletando o dado da collection de descricao

        //Botão Excluir
        holder.btExcluir.setOnClickListener(v ->{
            db.collection("registro")
                    .document("" + registro.getCodigo())
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        lista.remove(position);
                        notifyItemRemoved(position);
                    });
        });//fim do excluir

        //Botão Editar
        holder.btEditar.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CadastrarActivity.class);

            //Coletar cada elemento da collection e mostrar em uma tela cadastrar para edição e atualização
            intent.putExtra("Id",registro.getId());
            intent.putExtra("codigo", registro.getCodigo());
            intent.putExtra("data", registro.getDataEvento());
            intent.putExtra("assunto", registro.getAssunto());
            intent.putExtra("descricao", registro.getDescricao());

            v.getContext().startActivity(intent);//Atualiza o dado da collection
        });//fim da esculta do editar
    }//fim do método onBind

    @Override
    public int getItemCount() {
        return lista.size();
    }//fim do contador da lista - Define a quantidade de dados que está na collection
}//fim da class viewHolder
