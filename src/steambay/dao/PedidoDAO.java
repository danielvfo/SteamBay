/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steambay.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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

    public int buscarPedidoRecente() {
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

    public ArrayList<Jogo> buscarPedidoItens(int id) {
        conexao.conectar();
        ArrayList<Jogo> resultados = new ArrayList<>();
        ResultSet rs;
        try {
            rs = conexao.getComando().executeQuery("select b.nome, b.preco, b.tipo, b.id"
                    + " from pedido as a"
                    + " inner join jogo_pedido as c on a.id = c.Pedido_id"
                    + " inner join jogo as b on c.Jogo_id = b.id"
                    + " where a.id = "
                    + id + ";");
            while (rs.next()) {
                Jogo tempJ = new Jogo();
                tempJ.setNome(rs.getString("nome"));
                tempJ.setPreco(rs.getFloat("preco"));
                tempJ.setTipo(rs.getBoolean("tipo"));
                tempJ.setId(rs.getInt("id"));
                resultados.add(tempJ);
            }
            return resultados;
        } catch (SQLException e) {
            conexao.imprimeErro("Erro ao buscar jogo!", e.getMessage());
            return null;
        }
    }

    public void fechaConexao() {
        conexao.fechar();
    }

    public void inserePedido(Pedido pedido) throws Exception {
        conexao.conectar();
        if (pedido.getTotal() >= 0) {
            try {
                conexao.getComando().execute("INSERT INTO pedido (quantidade, total) VALUES("
                        + pedido.getQuantidade() + ", "
                        + pedido.getTotal() + ")");
                System.out.println("Pedido inserido com sucesso!");
                JOptionPane.showMessageDialog(null, "Pedido inserido com sucesso!");
            } catch (SQLException e) {
                conexao.imprimeErro("Erro ao inserir pedido!", e.getMessage());
            } finally {
                conexao.fechar();
            }
        } else {
            throw new Exception("Erro! Valor do pedido negativo!");
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

    public void apagar(int id) {
        conexao.conectar();
        try {
            if (JOptionPane.showConfirmDialog(null, "Deseja excluir o pedido?") == 0) {
                conexao.getComando().executeUpdate("DELETE FROM jogo_pedido WHERE pedido_id = " + id + ";");
                conexao.getComando().executeUpdate("DELETE FROM pedido WHERE id = " + id + ";");             
                System.out.println("Pedido de id = " + id + " removido com sucesso!");
                JOptionPane.showMessageDialog(null, "Pedido de id = " + id + " removido com sucesso!");
            }           
        } catch (SQLException e) {
            conexao.imprimeErro("Erro ao apagar pedido ", e.getMessage());
        } finally {
            conexao.fechar();
        }
    }
    
    public void atualizar(int jogo, int pedido) {
        conexao.conectar();
        try {
                conexao.getComando().execute("INSERT INTO jogo_pedido (Jogo_id, Pedido_id) VALUES("
                    + jogo + ", "
                    + pedido + ")");
                System.out.println("Pedido de id = " + pedido + " atualizado com sucesso!");        
        } catch (SQLException e) {
            conexao.imprimeErro("Erro ao atualizar pedido ", e.getMessage());
        } finally {
            conexao.fechar();
        }
    }
    
    public void removeRelacao(int pedido) {
        conexao.conectar();
        try {
                conexao.getComando().executeUpdate("DELETE FROM jogo_pedido WHERE pedido_id = " + pedido + ";");
                System.out.println("Pedido de id = " + pedido + " removido com sucesso!");
                JOptionPane.showMessageDialog(null, "Pedido de id = " + pedido + " atualizado com sucesso!");   
        } catch (SQLException e) {
            conexao.imprimeErro("Erro ao atualizar pedido ", e.getMessage());
        } finally {
            conexao.fechar();
        }
    }
}
