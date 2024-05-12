package dev.arunangshu;

import dev.arunangshu.models.Grid;
import dev.arunangshu.models.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(3);

        Scanner scanner = new Scanner(System.in);

        // Register player 1
        char playerOneSymbol = scanner.next().charAt(0);
        String playerOneName = scanner.next();
        grid.registerPlayer(new Player(playerOneName, playerOneSymbol));
        scanner.nextLine();

        // Register player 2
        char playerTwoSymbol = scanner.next().charAt(0);
        String playerTwoName = scanner.next();
        grid.registerPlayer(new Player(playerTwoName, playerTwoSymbol));
        scanner.nextLine();

        while (grid.isGameRunning()) {
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            String[] coordinates = input.split(" ");
            int row = Integer.parseInt(coordinates[0]);
            int col = Integer.parseInt(coordinates[1]);
            grid.mark(row, col);
        }
    }
}