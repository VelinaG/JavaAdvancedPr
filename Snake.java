import java.util.Scanner;

public class Snake {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];
        int snakeRow = -1;
        int snakeCol = -1;
        int[] firstBurrowIndexes = {-1, -1};
        int[] secondBurrowIndexes = {-1, -1};

        for (int row = 0; row < size; row++) {
            String info = scanner.nextLine();
            for (int col = 0; col < info.length(); col++) {
                matrix[row][col] = info.charAt(col);
                if (info.charAt(col) == 'S') {
                    snakeRow = row;
                    snakeCol = col;
                } else if (info.charAt(col) == 'B') {
                    if (firstBurrowIndexes[0] == -1) {
                        firstBurrowIndexes[0] = row;
                        firstBurrowIndexes[1] = col;
                    } else {
                        secondBurrowIndexes[0] = row;
                        secondBurrowIndexes[1] = col;
                    }
                }
            }
        }

        int foodQuantity = 0;
        boolean snakeIsOut = false;

        while (foodQuantity < 10) {
            String command = scanner.nextLine();

            switch (command) {
                case "up":
                    int upSnake = snakeRow - 1;
                    snakeIsOut = checkIfSnakeIsOut(matrix, upSnake, snakeCol);
                    if (snakeIsOut) {
                        matrix[snakeRow][snakeCol] = '.';
                        break;
                    } else if (matrix[upSnake][snakeCol] == '-' || (matrix[upSnake][snakeCol] == '*')) {
                        matrix[snakeRow][snakeCol] = '.';
                        matrix[upSnake][snakeCol] = 'S';
                        if (matrix[upSnake][snakeCol] == '*') {
                            foodQuantity++;
                        }
                        snakeRow = upSnake;
                    } else if (matrix[upSnake][snakeCol] == 'B') {
                        matrix[snakeRow][snakeCol] = '.';
                        if (upSnake == firstBurrowIndexes[0] && snakeCol == firstBurrowIndexes[1]){
                            matrix[upSnake][snakeCol] = '.';
                            snakeRow = secondBurrowIndexes[0];
                            snakeCol = secondBurrowIndexes[1];
                        } else {
                            matrix[upSnake][snakeCol] = '.';
                            snakeRow = firstBurrowIndexes[0];
                            snakeCol = firstBurrowIndexes[1];
                        }
                        matrix[upSnake][snakeCol] = '.';
                        matrix[snakeRow][snakeCol] = 'S';
                    }
                    break;

                case "down":
                    int downSnake = snakeRow + 1;
                    snakeIsOut = checkIfSnakeIsOut(matrix, downSnake, snakeCol);
                    if (snakeIsOut) {
                        matrix[snakeRow][snakeCol] = '.';
                        break;
                    } else if (matrix[downSnake][snakeCol] == '-' || (matrix[downSnake][snakeCol] == '*')) {
                        matrix[snakeRow][snakeCol] = '.';
                        matrix[downSnake][snakeCol] = 'S';
                        if (matrix[downSnake][snakeCol] == '*') {
                            foodQuantity++;
                        }
                        snakeRow = downSnake;
                    } else if (matrix[downSnake][snakeCol] == 'B') {
                        matrix[snakeRow][snakeCol] = '.';
                        if (downSnake == firstBurrowIndexes[0] && snakeCol == firstBurrowIndexes[1]) {
                            matrix[downSnake][snakeCol] = '.';
                            snakeRow = secondBurrowIndexes[0];
                            snakeCol = secondBurrowIndexes[1];
                        } else {
                            matrix[downSnake][snakeCol] = '.';
                            snakeRow = firstBurrowIndexes[0];
                            snakeCol = firstBurrowIndexes[1];
                        }
                        matrix[downSnake][snakeCol] = '.';
                        matrix[snakeRow][snakeCol] = 'S';
                    }
                        break;

                case "left":
                    int leftSnake = snakeCol - 1;
                    snakeIsOut = checkIfSnakeIsOut(matrix, snakeRow, leftSnake);
                    if (snakeIsOut) {
                        matrix[snakeRow][snakeCol] = '.';
                        break;
                    } else if (matrix[snakeRow][leftSnake] == '-' || (matrix[snakeRow][leftSnake] == '*')) {
                        matrix[snakeRow][snakeCol] = '.';
                        matrix[snakeRow][leftSnake] = 'S';
                        if (matrix[snakeRow][leftSnake] == '*') {
                            foodQuantity++;
                        }
                        snakeCol = leftSnake;
                    } else if (matrix[snakeRow][leftSnake] == 'B') {
                        matrix[snakeRow][snakeCol] = '.';
                        if (snakeRow == firstBurrowIndexes[0] && leftSnake == firstBurrowIndexes[1]) {
                            matrix[snakeRow][leftSnake] = '.';
                            snakeRow = secondBurrowIndexes[0];
                            snakeCol = secondBurrowIndexes[1];
                        } else {
                            matrix[snakeRow][leftSnake] = '.';
                            snakeRow = firstBurrowIndexes[0];
                            snakeCol = firstBurrowIndexes[1];
                        }
                        matrix[snakeRow][leftSnake] = '.';
                        matrix[snakeRow][snakeCol] = 'S';
                    }
                    break;

                case "right":
                    int rightSnake = snakeCol + 1;
                    snakeIsOut = checkIfSnakeIsOut(matrix, snakeRow, rightSnake);
                    if (snakeIsOut) {
                        matrix[snakeRow][snakeCol] = '.';
                        break;
                    } else if (matrix[snakeRow][rightSnake] == '-' || (matrix[snakeRow][rightSnake] == '*')) {
                        matrix[snakeRow][snakeCol] = '.';
                        matrix[snakeRow][rightSnake] = 'S';
                        if (matrix[snakeRow][rightSnake] == '*') {
                            foodQuantity++;
                        }
                        snakeCol = rightSnake;
                    } else if (matrix[snakeRow][rightSnake] == 'B') {
                        matrix[snakeRow][snakeCol] = '.';
                        if (snakeRow == firstBurrowIndexes[0] && rightSnake == firstBurrowIndexes[1]) {
                            matrix[snakeRow][rightSnake] = '.';
                            snakeRow = secondBurrowIndexes[0];
                            snakeCol = secondBurrowIndexes[1];
                        } else {
                            matrix[snakeRow][rightSnake] = '.';
                            snakeRow = firstBurrowIndexes[0];
                            snakeCol = firstBurrowIndexes[1];
                        }
                        matrix[snakeRow][rightSnake] = '.';
                        matrix[snakeRow][snakeCol] = 'S';
                    }

                    break;
            }
            if (snakeIsOut){
                break;
            }
        }


        if (snakeIsOut){
            System.out.println("Game over!");
        } else {
            System.out.println("You won! You fed the snake.");
        }

        System.out.println(String.format("Food eaten: %d", foodQuantity));


        printMatrix(matrix);

    }

    private static boolean checkIfSnakeIsOut(char[][] matrix, int snakeRow, int snakeCol) {
        return snakeRow < 0 || snakeRow >= matrix.length || snakeCol < 0 || snakeCol >= matrix.length;
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
