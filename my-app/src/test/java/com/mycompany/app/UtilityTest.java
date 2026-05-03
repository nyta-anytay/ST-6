package com.mycompany.app;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class UtilityTest {
    
    @Test
    public void testPrintCharArray() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        char[] board = {'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X'};
        Utility.print(board);
        
        String output = outContent.toString();
        assertTrue(output.contains("X"));
        assertTrue(output.contains("O"));
        
        System.setOut(System.out);
    }
    
    @Test
    public void testPrintIntArray() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        int[] board = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Utility.print(board);
        
        String output = outContent.toString();
        assertTrue(output.contains("1"));
        assertTrue(output.contains("9"));
        
        System.setOut(System.out);
    }
    
    @Test
    public void testPrintArrayList() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(1);
        moves.add(2);
        moves.add(3);
        Utility.print(moves);
        
        String output = outContent.toString();
        assertTrue(output.contains("1"));
        assertTrue(output.contains("2"));
        assertTrue(output.contains("3"));
        
        System.setOut(System.out);
    }
}
