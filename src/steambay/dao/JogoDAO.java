/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steambay.dao;
import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import steambay.entity.Jogo;
/**
 *
 * @author Daniel, Thales e Gabriel
 */
public class JogoDAO {
    
    private final Conexao connct = new Conexao();
    
    public ArrayList<Jogo> buscar(String nome) {  
        connct.conectar();
        ArrayList<Jogo> resultados = new ArrayList<>();  
        ResultSet rs;
        try {  
           rs = connct.getComando().executeQuery("SELECT * FROM jogo WHERE nome LIKE '"  
                 + nome + "';");  
           while (rs.next()) {  
              Jogo temp = new Jogo();
              // pega todos os atributos do jogo
              temp.setNome(rs.getString("nome"));
              temp.setTipo(rs.getBoolean("tipo"));
              temp.setQtde(rs.getInt("quantidade"));
              temp.setTamanho(rs.getString("tamanho"));
              temp.setPreco(rs.getFloat("preco"));
              temp.setEspecificacao(rs.getString("especificacao"));
              temp.setDescricao(rs.getString("descricao"));
              temp.setFornecedor(rs.getInt("fornecedor_id"));
              resultados.add(temp);
           }
           return resultados;  
        } catch (SQLException e) {  
            connct.imprimeErro("Erro ao buscar jogo", e.getMessage());
           return null;
        }  
   }
    
   public void insere(Jogo jogo){  
      connct.conectar();  
      try {
         if (!jogo.getNome().isEmpty() && !jogo.getTamanho().isEmpty() && buscar(jogo.getNome()).size() < 1 && jogo.getFornecedor() > 0) {
            connct.getComando().execute("INSERT INTO jogo (nome, tipo, quantidade, tamanho, preco, especificacao, descricao, fornecedor_id) VALUES('" 
               + jogo.getNome() + "', "
               + jogo.isTipo() + ", "
               + jogo.getQtde() + ", '"
               + jogo.getTamanho() + "', "
               + jogo.getPreco() + ", '"
               + jogo.getEspecificacao() + "', '"
               + jogo.getDescricao() + "', "
               + jogo.getFornecedor() + ")");
            System.out.println("Inserido!");
            JOptionPane.showMessageDialog(null, "Jogo cadastrado com sucesso!");
         }
      } catch (SQLException e) {
         connct.imprimeErro("Erro ao inserir Jogo", e.getMessage());
      } finally {
         connct.fechar();
      }
   }
   
   public void apagar(String nome) {  
      connct.conectar();  
      try {  
         connct.getComando()  
               .execute("DELETE FROM jogo WHERE nome LIKE '"  
                 + nome + "';");
         JOptionPane.showMessageDialog(null, "Jogo apagado com sucesso!");
      } catch (SQLException e) {
         connct.imprimeErro("Erro ao apagar jogo", e.getMessage());
      } finally {
         connct.fechar();
      }
   }
   
   public void atualizar(Jogo jogo) {
      if (!jogo.getNome().isEmpty() && !jogo.getTamanho().isEmpty()) {
        connct.conectar();
        String com = "UPDATE jogo SET nome = '" + jogo.getNome() 
              + "', tipo = " + jogo.isTipo() 
              + ", quantidade = " + jogo.getQtde()
              + ", tamanho = '" + jogo.getTamanho()
              + "', preco = " + jogo.getPreco()
              + ", especificacao = '" + jogo.getEspecificacao()
              + "', descricao = '" + jogo.getDescricao()
              + "', fornecedor_id = " + jogo.getFornecedor()
              + " WHERE  nome LIKE '" + jogo.getNome() + "';";  
        System.out.println("Atualizada!");
        JOptionPane.showMessageDialog(null, "Jogo atualizado com sucesso!");
        try {  
           connct.getComando().executeUpdate(com);  
        } catch (SQLException e) {  
           e.printStackTrace();
        } finally {  
           connct.fechar();  
        }
      }
   }
}
