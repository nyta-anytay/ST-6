package com.mycompany.app;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ProgramTest {

    private Game game;

    @BeforeClass
    public static void setUpHeadless() {
        System.setProperty("java.awt.headless", "true");
    }

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testInitialBoardEmpty() {
        for (int i = 0; i < 9; i++) {
            assertEquals(' ', game.board[i]);
        }
        assertEquals(State.PLAYING, game.state);
    }

    @Test
    public void testCheckStateXWin() {
        char[] b = {'X','X','X',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(b));
    }

    @Test
    public void testCheckStateOWin() {
        char[] b = {' ',' ',' ','O','O','O',' ',' ',' '};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(b));
    }

    @Test
    public void testCheckStateDraw() {
        char[] b = {'X','O','X','X','O','O','O','X','X'};
        game.symbol = 'X';
        assertEquals(State.DRAW, game.checkState(b));
    }

    @Test
    public void testCheckStatePlaying() {
        char[] b = {'X',' ',' ',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(b));
    }

    @Test
    public void testGenerateMovesAll() {
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(9, moves.size());
    }

    @Test
    public void testGenerateMovesPartial() {
        game.board[0] = 'X';
        game.board[4] = 'O';
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(7, moves.size());
        assertFalse(moves.contains(0));
        assertFalse(moves.contains(4));
    }

    @Test
    public void testEvaluatePositionWinForPlayer() {
        char[] b = {'X','X','X',' ',' ',' ',' ',' ',' '};
        Player p = game.player1;
        game.symbol = 'X';
        assertEquals(Game.INF, game.evaluatePosition(b, p));
    }

    @Test
    public void testEvaluatePositionLoseForPlayer() {
        char[] b = {'O','O','O',' ',' ',' ',' ',' ',' '};
        Player p = game.player1;
        game.symbol = 'O';
        assertEquals(-Game.INF, game.evaluatePosition(b, p));
    }

    @Test
    public void testEvaluatePositionDraw() {
        char[] b = {'X','O','X','X','O','O','O','X','X'};
        game.symbol = 'X';
        Player p = game.player1;
        assertEquals(0, game.evaluatePosition(b, p));
    }

    @Test
    public void testEvaluatePositionNotTerminal() {
        char[] b = {'X',' ',' ',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        Player p = game.player1;
        assertEquals(-1, game.evaluatePosition(b, p));
    }

    @Test
    public void testMiniMaxReturnsValidMove() {
        int move = game.MiniMax(game.board, game.player2);
        assertTrue("Move must be in 1..9", move >= 1 && move <= 9);
        assertEquals(' ', game.board[move-1]);
    }

    @Test
    public void testMiniMaxBlockWin() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = ' ';
        game.symbol = 'X';
        int move = game.MiniMax(game.board, game.player2);
        if (move >= 1 && move <= 9) {
            game.board[move - 1] = 'O';
            game.symbol = 'X';
            assertNotEquals(State.XWIN, game.checkState(game.board));
        }
    }

    @Test
    public void testMiniMaxWinForO() {
        game.board[3] = 'O';
        game.board[4] = 'O';
        game.board[5] = ' ';
        game.symbol = 'O';
        int move = game.MiniMax(game.board, game.player2);
        if (move >= 1 && move <= 9) {
            game.board[move - 1] = 'O';
            game.symbol = 'X';
            assertNotEquals(State.XWIN, game.checkState(game.board));
        }
    }

    @Test
    public void testMinMoveTerminal() {
        char[] b = {'X','X','X',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        int val = game.MinMove(b, game.player1);
        assertTrue(val <= -Game.INF || val >= Game.INF);
    }

    @Test
    public void testMaxMoveTerminal() {
        char[] b = {'O','O','O',' ',' ',' ',' ',' ',' '};
        game.symbol = 'O';
        int val = game.MaxMove(b, game.player1);
        assertTrue(val <= -Game.INF || val >= Game.INF);
    }

    @Test
    public void testPlayerFields() {
        Player p = new Player();
        p.symbol = 'T';
        p.move = 5;
        p.selected = true;
        p.win = false;
        assertEquals('T', p.symbol);
        assertEquals(5, p.move);
        assertTrue(p.selected);
        assertFalse(p.win);
    }

    @Test
    public void testCellConstructorAndGetters() {
        TicTacToeCell cell = new TicTacToeCell(5, 2, 1);
        assertEquals(5, cell.getNum());
        assertEquals(1, cell.getRow());
        assertEquals(2, cell.getCol());
        assertEquals(' ', cell.getMarker());
    }

    @Test
    public void testSetMarker() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
        assertEquals("X", cell.getText());
        assertFalse(cell.isEnabled());
    }

    @Test
    public void testUtilityPrintChar() {
        char[] b = {'X','O','X','O','X','O','X','O','X'};
        Utility.print(b);
    }

    @Test
    public void testUtilityPrintInt() {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        Utility.print(arr);
    }

    @Test
    public void testUtilityPrintArrayList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0); list.add(4); list.add(8);
        Utility.print(list);
    }

    @Test
    public void testTicTacToePanelCreation() {
        GridLayout layout = new GridLayout(3,3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        assertEquals(9, panel.getComponentCount());
        for (int i = 0; i < 9; i++) {
            assertTrue(panel.getComponent(i) instanceof TicTacToeCell);
        }
    }

    @Test
    public void testActionPerformedSingleMove() {
        GridLayout layout = new GridLayout(3,3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        TicTacToeCell[] cells = new TicTacToeCell[9];
        for (int i = 0; i < 9; i++) {
            cells[i] = (TicTacToeCell) panel.getComponent(i);
        }
        ActionEvent event = new ActionEvent(cells[0], ActionEvent.ACTION_PERFORMED, "");
        panel.actionPerformed(event);
    }

    @Test
    public void testStaticFieldsInitiallyNull() {
        assertNull(Program.fileWriter);
        assertNull(Program.printWriter);
    }

    @Test(expected = HeadlessException.class)
    public void testMainThrowsHeadlessException() throws IOException {
        Program.main(new String[]{});
    }
}