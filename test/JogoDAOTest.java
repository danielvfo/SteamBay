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

import steambay.dao.JogoDAO;
import steambay.entity.Jogo;

/**
 *
 * @author Daniel, Thales e Gabriel
 */
public class JogoDAOTest {
    
    public JogoDAOTest() {
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
    public void InserirValidoTest() {
        Jogo tJogo = new Jogo("Call of Duty", false, 0, "25 GB", 200, "blablabla...", "Jogo FPS militar.", 1);
        JogoDAO jogoDAO = new JogoDAO();
        jogoDAO.insere(tJogo);
        assertEquals(1, jogoDAO.buscar(tJogo.getNome()).size());
        jogoDAO.apagar(tJogo.getNome());
    }
    
    @Test
    public void ApagarValidoTest() {
        Jogo tJogo = new Jogo("God of War", true, 20, "8 GB", 80, "blablabla...", "Ação frenética!! YEAH!", 1);
        JogoDAO jogoDAO = new JogoDAO();
        jogoDAO.insere(tJogo);
        jogoDAO.apagar(tJogo.getNome());
        assertEquals(0, jogoDAO.buscar(tJogo.getNome()).size());
    }
    
    @Test
    public void AtualizarValidoTest() {
        Jogo tJogo = new Jogo("Gears of War", false, 0, "7 GB", 80, "blablabla...", "Jogo TPS militar de ficção científica.", 1);
        JogoDAO jogoDAO = new JogoDAO();
        jogoDAO.insere(tJogo);
        Jogo tJogo2 = new Jogo("Gears of War", true, 10, "7 GB", 250, "blablabla...", "Jogo TPS militar de ficção científica.", 1);
        jogoDAO.atualizar(tJogo2);
        assertTrue(jogoDAO.buscar(tJogo.getNome()).get(0).isTipo());
        jogoDAO.apagar(tJogo2.getNome());
    }

    @Test
    public void ApagarInvalidoTest() {
        Jogo tJogo = new Jogo("Doom", false, 0, "256 KB", 10, "blablabla...", "Jogo FPS ficção científica.", 1);
        JogoDAO jogoDAO = new JogoDAO();
        jogoDAO.insere(tJogo);
        jogoDAO.apagar("Não é Doom");
        assertEquals(1, jogoDAO.buscar(tJogo.getNome()).size());
        jogoDAO.apagar(tJogo.getNome());
    }
    
    @Test
    public void AtualizarInvalidoTest1() {
        Jogo tJogo = new Jogo("Klonoa", false, 0, "500 MB", 30, "blablabla...", "Jogo shooter ficção científica.", 1);
        JogoDAO jogoDAO = new JogoDAO();
        jogoDAO.insere(tJogo);
        tJogo.setTamanho("");
        jogoDAO.atualizar(tJogo);
        assertEquals("500 MB", jogoDAO.buscar(tJogo.getNome()).get(0).getTamanho());
        jogoDAO.apagar(tJogo.getNome());
    }
    
    @Test
    public void AtualizarInvalidoTest2() {
        Jogo tJogo = new Jogo("Klonoa", false, 0, "500 MB", 30, "blablabla...", "Jogo shooter ficção científica.", 1);
        JogoDAO jogoDAO = new JogoDAO();
        jogoDAO.insere(tJogo);
        tJogo.setNome("");
        jogoDAO.atualizar(tJogo);
        tJogo.setNome("Klonoa");
        assertFalse(jogoDAO.buscar(tJogo.getNome()).get(0).getNome().isEmpty());        
        jogoDAO.apagar(tJogo.getNome());
   }
    
    @Test
    public void InserirInvalidoTest1() {
        Jogo tJogo = new Jogo("", false, 0, "500 MB", 30, "blablabla...", "Jogo...", 1);
        JogoDAO jogoDAO = new JogoDAO();
        jogoDAO.insere(tJogo);
        assertEquals(0, jogoDAO.buscar(tJogo.getNome()).size());
    }
    
    @Test
    public void InserirInvalidoTest2() {
        Jogo tJogo = new Jogo("Fable", true, 13, "", 50, "blablabla...", "Jogo RPG de ação medieval.", 1);
        JogoDAO jogoDAO = new JogoDAO();
        jogoDAO.insere(tJogo);
        assertEquals(0, jogoDAO.buscar(tJogo.getNome()).size());
    }
    
    @Test
    public void InserirInvalidoTest3() {
        Jogo tJogo = new Jogo("Tomb Raider", true, 13, "9 GB", 120, "blablabla...", "Jogo de aventura e ação.", 1);
        JogoDAO jogoDAO = new JogoDAO();
        jogoDAO.insere(tJogo);
        Jogo tJogo2 = new Jogo("Tomb Raider", false, 0, "9 GB", 75, "blablabla...", "Jogo de aventura e ação.", 1);
        jogoDAO.insere(tJogo2);
        assertEquals(1, jogoDAO.buscar(tJogo.getNome()).size());
        jogoDAO.apagar(tJogo.getNome());
    }
    
    @Test
    public void InserirInvalidoTest4() {
        Jogo tJogo = new Jogo("Killzone", true, 5, "2 GB", 35, "blablabla...", "Jogo FPS de ficção científica.", 0);
        JogoDAO jogoDAO = new JogoDAO();
        jogoDAO.insere(tJogo);
        assertEquals(0, jogoDAO.buscar(tJogo.getNome()).size());
    }
}