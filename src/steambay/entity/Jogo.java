/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steambay.entity;

/**
 *
 * @author Daniel, Thales e Gabriel
 */
public class Jogo {
    private int id;
    private String nome;
    private boolean tipo;
    private int qtde;
    private String tamanho; //Alterado o tipo deste atributo
    private float preco; 
    private String especificacao;
    private String descricao;
    private int fornecedor;

    public Jogo()
    {
    }
    
    public Jogo (String nome, boolean tipo, int qtde, String tamanho, float preco, String especificacao, String descricao, int fornecedor)
    {
        setNome(nome);
        setTipo(tipo);
        setQtde(qtde);
        setTamanho(tamanho);
        setPreco(preco);
        setEspecificacao(especificacao);
        setDescricao(descricao);
        setFornecedor(fornecedor);
    }
    
    public int getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(int fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
