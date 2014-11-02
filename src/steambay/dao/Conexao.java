/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package steambay.dao;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author Daniel, Thales e Gabriel
 */
public class Conexao {
   public static final int MYSQL = 0;  
   private static final String MySQLDriver = "com.mysql.jdbc.Driver";  
   private final String URL = "jdbc:mysql://localhost/steambay", NOME = "user", SENHA = "123456";  
  
   private Connection con;
   private Statement comando;

    public Statement getComando() {
        return comando;
    }
   
   public static Connection conexao(String url, String nome, String senha, int steambay) throws ClassNotFoundException, SQLException {  
      switch (steambay){        
      case MYSQL:           
         Class.forName(MySQLDriver);  
         break;  
      }  
      return DriverManager.getConnection(url, nome, senha);  
   }

   public void conectar() {  
      try {  
         con = Conexao.conexao(URL, NOME, SENHA, Conexao.MYSQL);  
         comando = con.createStatement();  
         System.out.println("Conectado!");  
      } catch (ClassNotFoundException e) {  
         imprimeErro("Erro ao carregar o driver", e.getMessage());  
      } catch (SQLException e) {  
         imprimeErro("Erro ao conectar", e.getMessage());  
      }  
   }  
  
   public void fechar() {  
      try {  
         comando.close();  
         con.close();  
         System.out.println("Conexão Fechada");  
      } catch (SQLException e) {  
         imprimeErro("Erro ao fechar conexão", e.getMessage());  
      }  
   }  
  
   public void imprimeErro(String msg, String msgErro) {  
      JOptionPane.showMessageDialog(null, msg, "Erro crítico", 0);  
      System.err.println(msg);  
      System.out.println(msgErro);  
      System.exit(0);  
   }
}
