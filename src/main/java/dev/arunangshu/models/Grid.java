package dev.arunangshu.models;

import java.util.LinkedList;
import java.util.Queue;

public class Grid {

    private final int size;

    private final Slot[][] slots;

    private final Queue<Player> players = new LinkedList<>();

    private int filled = 0;

    private boolean isGameOver = false;

    public Grid(int size) {
        this.slots = new Slot[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                slots[i][j] = new Slot();
            }
        }
        this.size = size;
    }

    public boolean isOccupied(int row, int col) {
        return slots[row][col].isOccupied();
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isGameRunning() {
        return !isGameOver;
    }

    public void registerPlayer(Player player) {
        players.add(player);
    }

    public Player getCurrentPlayer() {
        return players.peek();
    }

    public void switchPlayer() {
        players.add(players.poll());
    }

    public boolean isFull() {
        return filled == size * size;
    }

    public boolean isWinningMove(int row, int col) {
        char symbol = slots[row][col].getSymbol();
        return checkRow(row, symbol) || checkCol(col, symbol) || checkDiagonal(symbol);
    }

    private boolean checkRow(int row, char symbol) {
        for (int i = 0; i < size; i++) {
            if (slots[row][i].getSymbol() != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCol(int col, char symbol) {
        for (int i = 0; i < size; i++) {
            if (slots[i][col].getSymbol() != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal(char symbol) {
        boolean leftDiagonal = true;
        boolean rightDiagonal = true;
        for (int i = 0; i < size; i++) {
            if (slots[i][i].getSymbol() != symbol) {
                leftDiagonal = false;
            }
            if (slots[i][size - i - 1].getSymbol() != symbol) {
                rightDiagonal = false;
            }
        }
        return leftDiagonal || rightDiagonal;
    }

    public void mark(int row, int col) {
        if (isGameOver()) {
            System.out.println("Game over. Start a new game.");
            return;
        }

        if (isOccupied(row - 1, col - 1)) {
            System.out.println("Slot already occupied. Try again.");
            return;
        }

        slots[row - 1][col - 1].setPlayer(getCurrentPlayer());
        filled++;
        print();

        if (isWinningMove(row - 1, col - 1)) {
            System.out.println("Player " + getCurrentPlayer().name() + " wins!");
            isGameOver = true;
        }

        if (isFull()) {
            System.out.println("Game over. It's a draw!");
            isGameOver = true;
        }

        switchPlayer();
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(slots[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
    }
}
