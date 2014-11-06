/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steambay.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import steambay.entity.Fornecedor;

/**
 *
 * @author Daniel, Thales e Gabriel
 */
public class FornecedorDAO {
    
    Conexao conexao = new Conexao();
    
    public void apagar(String cnpj){
        conexao.conectar();
        try{
            conexao.getComando()
                .executeUpdate("DELETE FROM Fornecedor WHERE cnpj = '" + cnpj + "';");
            JOptionPane.showMessageDialog(null, "Fornecedor de CNPJ = " + cnpj + " removido com sucesso!");
        }
        catch(SQLException e){
            conexao.imprimeErro("Erro ao apagar Fornecedor", e.getMessage());
        }
        finally{
            conexao.fechar();
        }
    }

    public Vector<Fornecedor> buscarTodos() {  
        conexao.conectar();  
        Vector<Fornecedor> resultados = new Vector<Fornecedor>();  
        ResultSet rs;  
        try {  
            rs = conexao.getComando().executeQuery("SELECT * FROM Fornecedor");  
            while (rs.next()) {  
                Fornecedor temp = new Fornecedor();  
            // pega todos os atributos do Fornecedor  
                temp.setNome(rs.getString("nome"));
                temp.setRazao_social(rs.getString("razao_social"));
                temp.setCnpj(rs.getString("cnpj"));
                temp.setTelefone(rs.getString("telefone"));
                temp.setLogradouro(rs.getString("logradouro"));
                temp.setNumero(rs.getString("numero"));
                temp.setComplemento(rs.getString("complemento"));
                temp.setCep(rs.getString("cep"));
                temp.setCidade(rs.getString("cidade"));
                temp.setUf(rs.getString("uf"));
                resultados.add(temp);  
            }  
            return resultados;  
        } catch (SQLException e) {  
            conexao.imprimeErro("Erro ao buscar fornecedores", e.getMessage());  
            return null;  
        }  
    }

    public void atualizar(Fornecedor fornecedor) {  
        if((JOptionPane.showConfirmDialog(null, "Deseja realmente alterar os dados do Fornecedor de CNPJ: " + fornecedor.getCnpj() + "?")) == 0)
            if((!fornecedor.getCnpj().isEmpty()) && (!fornecedor.getNome().isEmpty()) && (!fornecedor.getRazao_social().isEmpty()) && (!fornecedor.getTelefone().isEmpty()) && (!fornecedor.getLogradouro().isEmpty()) && (!fornecedor.getNumero().isEmpty()) && (!fornecedor.getCep().isEmpty()) && (!fornecedor.getCidade().isEmpty()) && (!fornecedor.getUf().isEmpty())){
                conexao.conectar();
                String com = "UPDATE Fornecedor SET nome = '" + fornecedor.getNome()
                    + "', razao_social ='" + fornecedor.getRazao_social()
                    + "', cnpj = '" + fornecedor.getCnpj()
                    + "', telefone ='" + fornecedor.getTelefone()
                    + "', logradouro ='" + fornecedor.getLogradouro()
                    + "', numero ='" + fornecedor.getNumero()
                    + "', complemento ='" + fornecedor.getComplemento()
                    + "', cep ='" + fornecedor.getCep()
                    + "', cidade ='" + fornecedor.getCidade()
                    + "', uf ='" + fornecedor.getUf()
                    + "' WHERE  cnpj = '" + fornecedor.getCnpj() + "';";  
                JOptionPane.showMessageDialog(null, "Fornecedor de CNPJ = " + fornecedor.getCnpj() + " atualizado com sucesso!");
                try {  
                    conexao.getComando().executeUpdate(com);  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                } finally {  
                    conexao.fechar();  
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Faltam informações para atualizar o fornecedor!");
    }  
  
    public Fornecedor buscar(String cnpj) {  
        conexao.conectar(); 
        Fornecedor resultados = new Fornecedor();
        //Vector<Fornecedor> resultados = new Vector<Fornecedor>();  
        ResultSet rs;
        try {  
            rs = conexao.getComando().executeQuery("SELECT * FROM Fornecedor WHERE cnpj LIKE '"  
                + cnpj + "';");  
            while (rs.next()) {  
                Fornecedor temp = new Fornecedor();  
            // pega todos os atributos do Fornecedor  
                temp.setNome(rs.getString("nome"));
                temp.setRazao_social(rs.getString("razao_social"));
                temp.setCnpj(rs.getString("cnpj"));
                temp.setTelefone(rs.getString("telefone"));
                temp.setLogradouro(rs.getString("logradouro"));
                temp.setNumero(rs.getString("numero"));
                temp.setComplemento(rs.getString("complemento"));
                temp.setCep(rs.getString("cep"));
                temp.setCidade(rs.getString("cidade"));
                temp.setUf(rs.getString("uf")); 
                //resultados.add(temp);
                resultados = temp;
            }
            return resultados;
        } catch (SQLException e) {  
            conexao.imprimeErro("Erro ao buscar fornecedor", e.getMessage());  
            return null;  
        }  
  
    }  
  
    public void insere(Fornecedor fornecedor){  
        Pattern padrao = Pattern.compile("\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}");
        Matcher verificaCnpj = padrao.matcher(fornecedor.getCnpj());
        
        // Verifica se os campos não estão vazios
        if((!fornecedor.getCnpj().isEmpty()) && (!fornecedor.getNome().isEmpty()) && (!fornecedor.getRazao_social().isEmpty()) && (!fornecedor.getTelefone().isEmpty()) && (!fornecedor.getLogradouro().isEmpty()) && (!fornecedor.getNumero().isEmpty()) && (!fornecedor.getCep().isEmpty()) && (!fornecedor.getCidade().isEmpty()) && (!fornecedor.getUf().isEmpty())){
            if(verificaCnpj.matches()){
                conexao.conectar();
                try {
                    conexao.getComando().execute("INSERT INTO Fornecedor (nome, razao_social, cnpj, telefone, logradouro, numero, complemento, cep, cidade, uf) VALUES('" 
                        + fornecedor.getNome() + "', '"
                        + fornecedor.getRazao_social() + "', '"
                        + fornecedor.getCnpj() + "', '"
                        + fornecedor.getTelefone() + "', '"
                        + fornecedor.getLogradouro() + "', '"
                        + fornecedor.getNumero() + "', '"
                        + fornecedor.getComplemento() + "', '"
                        + fornecedor.getCep() + "', '"
                        + fornecedor.getCidade() + "', '"
                        + fornecedor.getUf() + "')");
                    JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");
                } catch (SQLException e) {
                    conexao.imprimeErro("Erro ao inserir Fornecedor", e.getMessage());  
                }finally {  
                    conexao.fechar();  
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "O CNPJ está no formato errado!");
                JOptionPane.showMessageDialog(null, "Formato esperado: XX.XXX.XXX/XXXX-XX");
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Faltam informações para cadastrar o fornecedor!");
    }
}
