/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import steambay.entity.Fornecedor;
import steambay.dao.FornecedorDAO;

/**
 *
 * @author Thales
 */
public class FornecedorDAOTest {
    
    public FornecedorDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void insereFornecedorValido(){ // Nome, Razão Social, CNPJ, Telefone, Logradouro, Número, Complemento(opcional), CEP, Cidade, UF
        Fornecedor tFornecedor = new Fornecedor("Square Enix", "SQEX", "12.345.678/9012-34", "+55(43)9915-4675", "Av. Higienópolis", "105", "86300-000", "Londrina", "PR");
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorDAO.insere(tFornecedor);
        assertEquals(tFornecedor.getNome(),fornecedorDAO.buscar("12.345.678/9012-34").getNome());
        fornecedorDAO.apagar("12.345.678/9012-34");
        }
    
    @Test
    public void insereFornecedorInvalido(){ // Tenta inserir fornecedor com CNPJ vazio
        Fornecedor tFornecedor = new Fornecedor("Square Enix", "SQEX", "", "+55(43)9915-4675", "Av. Higienópolis", "105", "86300-000", "Londrina", "PR");
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorDAO.insere(tFornecedor);
        assertEquals(null,fornecedorDAO.buscar("").getNome());
    }
    
    @Test
    public void insereFornecedorCNPJInvalido(){ // Tenta inserir fornecedor com CNPJ no formato errado
        Fornecedor tFornecedor = new Fornecedor("Square Enix", "SQEX", "12345678901234", "+55(43)9915-4675", "Av. Higienópolis", "105", "86300-000", "Londrina", "PR");
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorDAO.insere(tFornecedor);
        assertEquals(null,fornecedorDAO.buscar("").getNome());
    }
    
    @Test
    public void atualizaFornecedor(){ // Verifica se atualiza o fornecedor
        Fornecedor tFornecedor = new Fornecedor("Square Enix", "SQEX", "12.345.678/9012-45", "+55(43)9915-4675", "Av. Higienópolis", "105", "86300-000", "Londrina", "PR");
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorDAO.insere(tFornecedor);
        tFornecedor.setNome("Square Soft");
        fornecedorDAO.atualizar(tFornecedor);
        assertEquals("Square Soft",fornecedorDAO.buscar("12.345.678/9012-45").getNome());
        fornecedorDAO.apagar("12.345.678/9012-45");
    }
    
    @Test
    public void removeFornecedor(){ // Verifica se remove o fornecedor
        Fornecedor tFornecedor = new Fornecedor("Square Enix", "SQEX", "12.345.678/9012-79", "+55(43)9915-4675", "Av. Higienópolis", "105", "86300-000", "Londrina", "PR");
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorDAO.insere(tFornecedor);
        assertEquals(tFornecedor.getNome(),fornecedorDAO.buscar("12.345.678/9012-79").getNome());
        fornecedorDAO.apagar("12.345.678/9012-79");
        assertEquals(null,fornecedorDAO.buscar("12.345.678/9012-79").getNome());
    }
}
