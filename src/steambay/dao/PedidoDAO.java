/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steambay.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import steambay.entity.Jogo;
import steambay.entity.Pedido;

/**
 *
 * @author Daniel, Thales e Gabriel
 */
public class PedidoDAO {

    Conexao conexao = new Conexao();

    public ArrayList<Jogo> buscarJogo(String nome) {
        conexao.conectar();
        ArrayList<Jogo> resultados = new ArrayList<>();
        ResultSet rs;
        try {
            rs = conexao.getComando().executeQuery("SELECT * FROM jogo WHERE nome LIKE '%"
                    + nome + "%';");
            while (rs.next()) {
                Jogo temp = new Jogo();
                temp.setId(rs.getInt("id"));
                temp.setNome(rs.getString("nome"));
                temp.setTipo(rs.getBoolean("tipo"));
                temp.setQtde(rs.getInt("quantidade"));
                temp.setTamanho(rs.getString("tamanho"));
                temp.setPreco(rs.getFloat("preco"));
                temp.setEspecificacao(rs.getString("especificacao"));
                temp.setDescricao(rs.getString("descricao"));
                resultados.add(temp);
            }
            return resultados;
        } catch (SQLException e) {
            conexao.imprimeErro("Erro ao buscar jogo!", e.getMessage());
            return null;
        }
    }

    public int buscarPedido() {
        conexao.conectar();
        Pedido temp = new Pedido();
        int resultado = 0;
        ResultSet rs;
        try {
            rs = conexao.getComando().executeQuery("SELECT MAX(id) as id FROM pedido");
            while (rs.next()) {
                temp.setId(rs.getInt("id"));
                resultado = temp.getId();
            }
            return resultado;
        } catch (SQLException e) {
            conexao.imprimeErro("Erro ao buscar id do pedido!", e.getMessage());
            return 0;
        }
    }

    public void fechaConexao() {
        conexao.fechar();
    }

    public void inserePedido(Pedido pedido) {
        conexao.conectar();
        try {
            conexao.getComando().execute("INSERT INTO pedido (quantidade, total) VALUES("
                    + pedido.getQuantidade() + ", "
                    + pedido.getTotal() + ")");
            System.out.println("Pedido inserido com sucesso!");
        } catch (SQLException e) {
            conexao.imprimeErro("Erro ao inserir pedido!", e.getMessage());
        } finally {
            conexao.fechar();
        }
    }

    public void insereJogoPedido(int jogo, int pedido) {
        conexao.conectar();
        try {
            conexao.getComando().execute("INSERT INTO jogo_pedido (Jogo_id, Pedido_id) VALUES("
                    + jogo + ", "
                    + pedido + ")");
            System.out.println("Relacionamento Jogo_Pedido inserido com sucesso!");
        } catch (SQLException e) {
            conexao.imprimeErro("Erro ao inserir o relacionamento Jogo_Pedido!", e.getMessage());
        } finally {
            conexao.fechar();
        }
    }
}
