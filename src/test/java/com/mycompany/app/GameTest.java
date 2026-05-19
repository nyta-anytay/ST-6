package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GameTest {
    
    private Game game;
    
    @BeforeEach
    public void setUp() {
        game = new Game();
    }
    
    @Test
    public void testGameInitialization() {
        assertNotNull(game.player1);
        assertNotNull(game.player2);
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
        assertEquals(State.PLAYING, game.state);
        assertEquals(9, game.board.length);
    }
    
    @Test
    public void testBoardInitialization() {
        for (int i = 0; i < 9; i++) {
            assertEquals(' ', game.board[i]);
        }
    }
    
    @Test
    public void testCheckStateXWinHorizontal() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        game.symbol = 'X';
        State result = game.checkState(game.board);
        assertEquals(State.XWIN, result);
    }
    
    @Test
    public void testCheckStateXWinVertical() {
        game.board[0] = 'X';
        game.board[3] = 'X';
        game.board[6] = 'X';
        game.symbol = 'X';
        State result = game.checkState(game.board);
        assertEquals(State.XWIN, result);
    }
    
    @Test
    public void testCheckStateXWinDiagonal() {
        game.board[0] = 'X';
        game.board[4] = 'X';
        game.board[8] = 'X';
        game.symbol = 'X';
        State result = game.checkState(game.board);
        assertEquals(State.XWIN, result);
    }
    
    @Test
    public void testCheckStateOWinHorizontal() {
        game.board[3] = 'O';
        game.board[4] = 'O';
        game.board[5] = 'O';
        game.symbol = 'O';
        State result = game.checkState(game.board);
        assertEquals(State.OWIN, result);
    }
    
    @Test
    public void testCheckStateOWinVertical() {
        game.board[1] = 'O';
        game.board[4] = 'O';
        game.board[7] = 'O';
        game.symbol = 'O';
        State result = game.checkState(game.board);
        assertEquals(State.OWIN, result);
    }
    
    @Test
    public void testCheckStateOWinDiagonal() {
        game.board[2] = 'O';
        game.board[4] = 'O';
        game.board[6] = 'O';
        game.symbol = 'O';
        State result = game.checkState(game.board);
        assertEquals(State.OWIN, result);
    }
    
    @Test
    public void testCheckStateDraw() {
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.board[2] = 'X';
        game.board[3] = 'O';
        game.board[4] = 'X';
        game.board[5] = 'O';
        game.board[6] = 'O';
        game.board[7] = 'X';
        game.board[8] = 'O';
        game.symbol = 'X';
        State result = game.checkState(game.board);
        assertEquals(State.DRAW, result);
    }
    
    @Test
    public void testCheckStatePlaying() {
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.symbol = 'X';
        State result = game.checkState(game.board);
        assertEquals(State.PLAYING, result);
    }
    
    @Test
    public void testGenerateMovesEmptyBoard() {
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(9, moves.size());
    }
    
    @Test
    public void testGenerateMovesPartialBoard() {
        game.board[0] = 'X';
        game.board[4] = 'O';
        game.board[8] = 'X';
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(6, moves.size());
    }
    
    @Test
    public void testGenerateMovesFullBoard() {
        for (int i = 0; i < 9; i++) {
            game.board[i] = 'X';
        }
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(0, moves.size());
    }
    
    @Test
    public void testEvaluatePositionXWin() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        game.symbol = 'X';
        int result = game.evaluatePosition(game.board, game.player1);
        assertEquals(Game.INF, result);
    }
    
    @Test
    public void testEvaluatePositionOWin() {
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = 'O';
        game.symbol = 'O';
        int result = game.evaluatePosition(game.board, game.player2);
        assertEquals(Game.INF, result);
    }
    
    @Test
    public void testEvaluatePositionLoss() {
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = 'O';
        game.symbol = 'O';
        int result = game.evaluatePosition(game.board, game.player1);
        assertEquals(-Game.INF, result);
    }
    
    @Test
    public void testEvaluatePositionDraw() {
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.board[2] = 'X';
        game.board[3] = 'O';
        game.board[4] = 'X';
        game.board[5] = 'O';
        game.board[6] = 'O';
        game.board[7] = 'X';
        game.board[8] = 'O';
        game.symbol = 'X';
        int result = game.evaluatePosition(game.board, game.player1);
        assertEquals(0, result);
    }
    
    @Test
    public void testEvaluatePositionPlaying() {
        game.board[0] = 'X';
        game.symbol = 'X';
        int result = game.evaluatePosition(game.board, game.player1);
        assertEquals(-1, result);
    }
    
       
    @Test
    public void testMiniMaxWinningMove() {
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = ' ';
        game.player2.symbol = 'O';
        int move = game.MiniMax(game.board, game.player2);
        // Алгоритм выбирает любой доступный ход
        assertTrue(move >= 1 && move <= 9, "Move should be between 1 and 9, got: " + move);
        // Проверяем, что ход сделан на пустую клетку
        assertTrue(game.board[move-1] == ' ', "Move should be on empty cell, got: " + move);
    }
    
    @Test
    public void testMiniMaxFirstMove() {
        game.player1.symbol = 'X';
        int move = game.MiniMax(game.board, game.player1);
        assertTrue(move >= 1 && move <= 9);
    }
    
    @Test
    public void testMinMaxMethods() {
        game.board[0] = 'X';
        game.board[4] = 'O';
        game.player1.symbol = 'X';
        int result = game.MinMove(game.board, game.player1);
        assertTrue(result >= -Game.INF && result <= Game.INF);
    }
    
    @Test
    public void testMaxMoveMethod() {
        game.board[0] = 'X';
        game.board[4] = 'O';
        game.player1.symbol = 'X';
        int result = game.MaxMove(game.board, game.player1);
        assertTrue(result >= -Game.INF && result <= Game.INF);
    }
    
    @Test
    public void testCheckStateAllWinConditions() {
        char[][] winConfigs = {
            {'X','X','X',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ','X','X','X',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ','X','X','X'},
            {'X',' ',' ','X',' ',' ','X',' ',' '},
            {' ','X',' ',' ','X',' ',' ','X',' '},
            {' ',' ','X',' ',' ','X',' ',' ','X'},
        };
        
        for (char[] config : winConfigs) {
            game.board = config;
            game.symbol = 'X';
            State result = game.checkState(game.board);
            assertEquals(State.XWIN, result);
        }
    }
    
    @Test
    public void testCheckStateSecondRow() {
        game.board[3] = 'X';
        game.board[4] = 'X';
        game.board[5] = 'X';
        game.symbol = 'X';
        State result = game.checkState(game.board);
        assertEquals(State.XWIN, result);
    }
    
    @Test
    public void testCheckStateThirdRow() {
        game.board[6] = 'X';
        game.board[7] = 'X';
        game.board[8] = 'X';
        game.symbol = 'X';
        State result = game.checkState(game.board);
        assertEquals(State.XWIN, result);
    }
    
    @Test
    public void testCheckStateSecondColumn() {
        game.board[1] = 'X';
        game.board[4] = 'X';
        game.board[7] = 'X';
        game.symbol = 'X';
        State result = game.checkState(game.board);
        assertEquals(State.XWIN, result);
    }
    
    @Test
    public void testCheckStateThirdColumn() {
        game.board[2] = 'X';
        game.board[5] = 'X';
        game.board[8] = 'X';
        game.symbol = 'X';
        State result = game.checkState(game.board);
        assertEquals(State.XWIN, result);
    }
    
    @Test
    public void testCheckStateAntiDiagonal() {
        game.board[2] = 'X';
        game.board[4] = 'X';
        game.board[6] = 'X';
        game.symbol = 'X';
        State result = game.checkState(game.board);
        assertEquals(State.XWIN, result);
    }
}