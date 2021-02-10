import javax.swing.*;
import java.util.Scanner;

public class SnakeLair {

    public static int foodQuantity = 0;
    public static int[] burrowIndexes = new int[2];


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];

        int snakeRow = -1, snakeCol = -1;

        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            for (int col = 0; col < line.length(); col++) {
                matrix[row][col] = line.charAt(col);
                if (line.charAt(col) == 'S') {
                    snakeRow = row;
                    snakeCol = col;
                }
            }
        }

        boolean snakeIsOut = false;
        while (foodQuantity < 10 ) {
            String command = scanner.nextLine();

            switch (command) {
                case "up":
                    // row - 1
                    if (snakeIsOutOfBounds(snakeRow - 1, snakeCol, matrix)) {
                        snakeIsOut = true;
                        break;
                    } else {
                        if ((moveSnake(snakeRow, snakeCol, snakeRow - 1, snakeCol, matrix))) {
                            snakeRow = burrowIndexes[0];
                            snakeCol = burrowIndexes[1];
                        } else {
                            snakeRow = snakeRow - 1;
                        }
                    }
                    break;

                case "down":
                    //row + 1
                    if (snakeIsOutOfBounds(snakeRow + 1, snakeCol, matrix)) {
                        snakeIsOut = true;
                        break;
                    } else {
                        if (moveSnake(snakeRow, snakeCol, snakeRow + 1, snakeCol, matrix)) {
                            snakeRow = burrowIndexes[0];
                            snakeCol = burrowIndexes[1];
                        } else {
                            snakeRow = snakeRow + 1;
                        }
                    }
                    break;

                case "left":
                    //col - 1
                    if (snakeIsOutOfBounds(snakeRow, snakeCol - 1, matrix)) {
                        snakeIsOut = true;
                        break;
                    } else {
                        if (moveSnake(snakeRow, snakeCol, snakeRow, snakeCol - 1, matrix)) {
                            snakeRow = burrowIndexes[0];
                            snakeCol = burrowIndexes[1];
                        } else {
                            snakeCol = snakeCol - 1;
                        }
                    }

                    break;

                case "right":
                    //col + 1
                    if (snakeIsOutOfBounds(snakeRow, snakeCol + 1, matrix)) {
                        snakeIsOut = true;
                        break;
                    } else {
                        if (moveSnake(snakeRow, snakeCol, snakeRow, snakeCol + 1, matrix)) {
                            snakeRow = burrowIndexes[0];
                            snakeCol = burrowIndexes[1];
                        } else {
                            snakeCol = snakeCol + 1;
                        }
                    }

                    break;
            }
            if (snakeIsOut){
                break;
            }

        }

        if (snakeIsOut) {
            matrix[snakeRow][snakeCol] = '.';
            System.out.println("Game over!");
        } else {
            System.out.println("You won! You fed the snake.");
        }

        System.out.println("Food eaten: " + foodQuantity);


        printMatrix(matrix);
    }

    private static boolean moveSnake(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == '-' || matrix[newRow][newCol] == '*') {
            if (matrix[newRow][newCol] == '*') {
                foodQuantity++;
            }
            matrix[newRow][newCol] = 'S';
            matrix[oldRow][oldCol] = '.';
        } else if (matrix[newRow][newCol] == 'B') {
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix.length; col++) {
                    if (matrix[row][col] == 'B' && row != newRow && col != newCol) {
                        burrowIndexes[0] = row;
                        burrowIndexes[1] = col;
                        matrix[newRow][newCol] = '.';
                        matrix[oldRow][oldCol] = '.';
                        matrix[row][col] = 'S';
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean snakeIsOutOfBounds(int row, int col, char[][] matrix) {
        return (row < 0 || row >= matrix.length || col < 0 || col >= matrix.length);
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
