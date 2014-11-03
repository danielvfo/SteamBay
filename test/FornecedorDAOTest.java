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
    public void insereFornecedor(){
        Fornecedor tFornecedor = new Fornecedor("DaGaTh","DANGABTHA","1891.548.5-7","489489","rua paranagua","1058","86300-000","londrina","pr");
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorDAO.insere(tFornecedor);
        assertEquals(tFornecedor.getNome(),fornecedorDAO.buscar("1891.548.5-7").getNome());
        fornecedorDAO.apagar("1891.548.5-7");
        }
}
