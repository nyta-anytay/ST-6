package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    
    private Player player;
    
    @BeforeEach
    public void setUp() {
        player = new Player();
    }
    
    @Test
    public void testPlayerCreation() {
        assertNotNull(player);
    }
    
    @Test
    public void testPlayerSymbol() {
        player.symbol = 'X';
        assertEquals('X', player.symbol);
        
        player.symbol = 'O';
        assertEquals('O', player.symbol);
    }
    
    @Test
    public void testPlayerMove() {
        player.move = 5;
        assertEquals(5, player.move);
    }
    
    @Test
    public void testPlayerSelected() {
        player.selected = true;
        assertTrue(player.selected);
        
        player.selected = false;
        assertFalse(player.selected);
    }
    
    @Test
    public void testPlayerWin() {
        player.win = true;
        assertTrue(player.win);
        
        player.win = false;
        assertFalse(player.win);
    }
}
