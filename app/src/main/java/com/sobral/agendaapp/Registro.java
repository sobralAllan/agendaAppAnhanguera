package com.sobral.agendaapp;

import java.util.Date;

public class Registro {
    //Declarando Variáveis
    private int codigo;
    private String assunto;
    private Date dataEvento;
    private String descricao;

    //Criar um construtor - Sem parâmetro
    public Registro(){
        this.codigo     = 0;
        this.assunto    = "";
        this.dataEvento = new Date();//0000/00/00
        this.descricao  = "";
    }//fim do construtorPadrao

    public Registro(int codigo, String assunto, Date dataEvento, String descricao){
        this.codigo     = codigo;
        this.assunto    = assunto;
        this.dataEvento = dataEvento;
        this.descricao  = descricao;
    }//fim do método

    //métodos gets e sets
    public int getCodigo(){
        return this.codigo;
    }//fim do consultarCodigo

    public String getAssunto(){
        return this.assunto;
    }//fim do consultarAssunto

    public Date getDataEvento(){
        return this.dataEvento;
    }//fim do método

    public String getDescricao(){
        return this.descricao;
    }//fim do método

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }//fim do set

    public void setAssunto(String assunto){
        this.assunto = assunto;
    }//fim do setAssunto

    public void setDataEvento(Date dataEvento){
        this.dataEvento = dataEvento;
    }//fim do setDataEvento

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }//fim do método










}//fim da classe
