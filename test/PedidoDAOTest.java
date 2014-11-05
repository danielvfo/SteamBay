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
import steambay.dao.PedidoDAO;
import steambay.entity.Pedido;

/**
 *
 * @author Daniel, Thales e Gabriel
 */
public class PedidoDAOTest {

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
    public void InserirValidoTest() {
        int idPedAnt;
        Pedido tPedido = new Pedido(2, 35.00);
        PedidoDAO pedidoDAO = new PedidoDAO();
        
        idPedAnt = pedidoDAO.buscarPedido();
        
        pedidoDAO.inserePedido(tPedido);
        pedidoDAO.insereJogoPedido(2, pedidoDAO.buscarPedido());
        
        assertFalse(idPedAnt == pedidoDAO.buscarPedido());
        
        pedidoDAO.apagar(pedidoDAO.buscarPedido());
    }
}
