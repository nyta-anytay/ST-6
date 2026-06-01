package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class ProgramTest {

    @Test
    public void testGameInitialization() {
        Game game = new Game();
        assertEquals(State.PLAYING, game.state);
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
    }

    @Test
    public void testCheckStateWinX() {
        Game game = new Game();
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X'; 
        assertEquals(State.XWIN, game.checkState(board));
    }

    @Test
    public void testCheckStateWinO() {
        Game game = new Game();
        char[] board = {'O', ' ', ' ', 'O', ' ', ' ', 'O', ' ', ' '};
        game.symbol = 'O'; 
        assertEquals(State.OWIN, game.checkState(board));
    }

    @Test
    public void testCheckStateDraw() {
        Game game = new Game();
        char[] board = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        game.symbol = 'X'; 
        assertEquals(State.DRAW, game.checkState(board));
    }

    @Test
    public void testGenerateMoves() {
        Game game = new Game();
        char[] board = {'X', 'O', ' ', ' ', 'X', 'O', ' ', ' ', 'X'};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(4, moves.size());
    }

    @Test
    public void testEvaluatePosition() {
        Game game = new Game();
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X'; 
        int eval = game.evaluatePosition(board, game.player1);
        assertEquals(Game.INF, eval);
    }

    @Test
    public void testMiniMaxBestMove() {
        Game game = new Game();
        // Даем доску с 3 пустыми клетками, чтобы запустить рекурсию MinMove и MaxMove
        char[] board = {'X', 'O', 'X', ' ', 'O', ' ', ' ', 'X', ' '};
        int move = game.MiniMax(board, game.player2);
        assertTrue(move >= 1 && move <= 9);
    }

    @Test
    public void testTicTacToeCell() {
        TicTacToeCell cell = new TicTacToeCell(5, 2, 1);
        assertEquals(5, cell.getNum());
        assertEquals(' ', cell.getMarker());
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
        assertFalse(cell.isEnabled()); 
    }

    @Test
    public void testUtilityMethods() {
        // Правильные массивы по 9 элементов, чтобы не было ошибки ArrayIndexOutOfBoundsException
        char[] cBoard = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Utility.print(cBoard);

        int[] iBoard = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Utility.print(iBoard);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        Utility.print(list);
    }

    @Test
    public void testGuiPanelSimulation() {
        // Симулируем графический интерфейс для покрытия кода TicTacToePanel
        GridLayout layout = new GridLayout(3,3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        // Симулируем клик пользователя по первой клетке
        ActionEvent ae = new ActionEvent(panel.getComponent(0), ActionEvent.ACTION_PERFORMED, "");
        panel.actionPerformed(ae);
        
        // Проверяем, что панель создалась и отреагировала на клик без ошибок
        assertNotNull(panel);
    }

    @Test
    public void testProgramMain() throws Exception {
        // Вызываем конструктор по-умолчанию для покрытия
        Program program = new Program();
        assertNotNull(program);

        // Запускаем окно (метод main)
        Program.main(new String[]{});

        // Мгновенно закрываем все открытые окна (JFrame), используя полный путь java.awt.Window
        for (java.awt.Window window : java.awt.Window.getWindows()) {
            window.dispose();
        }
    }
}