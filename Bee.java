import java.util.Scanner;

public class Bee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[n][n];

        int beeRow = -1;
        int beeCol = -1;
        for (int row = 0; row < n; row++) {
            String input = scanner.nextLine();
            for (int col = 0; col < input.length(); col++) {
                matrix[row][col] = input.charAt(col);
                if ('B' == input.charAt(col)) {
                    beeRow = row;
                    beeCol = col;
                }
            }
        }

        int flowerCount = 0;

        String command = scanner.nextLine();

        while (!command.equals("End")) {
            boolean beeIsLost = false;
            switch (command) {
                case "up":
                    if (beeRow - 1 > -1) {
                        matrix[beeRow][beeCol] = '.';
                        int upBee = beeRow - 1;
                        if (matrix[upBee][beeCol] == 'f') {
                            flowerCount++;
                            beeRow = upBee;
                            matrix[upBee][beeCol] = 'B';
                        } else if (matrix[upBee][beeCol] == 'O') {
                            matrix[upBee][beeCol] = '.';
                            beeRow = upBee - 1;
                            if (matrix[beeRow][beeCol] == 'f') {
                                matrix[beeRow][beeCol] = 'B';
                                flowerCount++;
                            } else {
                                matrix[beeRow][beeCol] = 'B';
                            }
                        } else {
                            beeRow = upBee;
                            matrix[beeRow][beeCol] = 'B';
                        }
                    } else {
                        System.out.println("The bee got lost!");
                        matrix[beeRow][beeCol] = '.';
                        beeIsLost = true;
                        break;
                    }

                    break;

                case "down":
                    if (beeRow + 1 < matrix.length) {
                        matrix[beeRow][beeCol] = '.';
                        int downBee = beeRow + 1;
                        if (matrix[downBee][beeCol] == 'f') {
                            flowerCount++;
                            beeRow = downBee;
                            matrix[downBee][beeCol] = 'B';
                        } else if (matrix[downBee][beeCol] == 'O') {
                            matrix[downBee][beeCol] = '.';
                            beeRow = downBee + 1;
                            if (matrix[beeRow][beeCol] == 'f') {
                                matrix[beeRow][beeCol] = 'B';
                                flowerCount++;
                            } else {
                                matrix[beeRow][beeCol] = 'B';
                            }
                        } else {
                            beeRow = downBee;
                            matrix[beeRow][beeCol] = 'B';
                        }
                    } else {
                        System.out.println("The bee got lost!");
                        matrix[beeRow][beeCol] = '.';
                        beeIsLost = true;
                        break;
                    }

                    break;

                case "left":
                    if (beeCol - 1 > -1) {
                        matrix[beeRow][beeCol] = '.';
                        int leftBee = beeCol - 1;
                        if (matrix[beeRow][leftBee] == 'f') {
                            flowerCount++;
                            beeCol = leftBee;
                            matrix[beeRow][beeCol] = 'B';
                        } else if (matrix[beeRow][leftBee] == 'O') {
                            matrix[beeRow][leftBee] = '.';
                            beeCol = leftBee - 1;
                            if (matrix[beeRow][beeCol] == 'f') {
                                matrix[beeRow][beeCol] = 'B';
                                flowerCount++;
                            } else {
                                matrix[beeRow][beeCol] = 'B';
                            }
                        } else {
                            beeCol = leftBee;
                            matrix[beeRow][beeCol] = 'B';
                        }
                    } else {
                        System.out.println("The bee got lost!");
                        matrix[beeRow][beeCol] = '.';
                        beeIsLost = true;
                        break;
                    }

                    break;

                case "right":
                    if (beeCol + 1 < matrix.length) {
                        matrix[beeRow][beeCol] = '.';
                        int rightBee = beeCol + 1;
                        if (matrix[beeRow][rightBee] == 'f') {
                            flowerCount++;
                            beeCol = rightBee;
                            matrix[beeRow][beeCol] = 'B';
                        } else if (matrix[beeRow][rightBee] == 'O') {
                            matrix[beeRow][rightBee] = '.';
                            beeCol = rightBee + 1;
                            if (matrix[beeRow][beeCol] == 'f') {
                                matrix[beeRow][beeCol] = 'B';
                                flowerCount++;
                            } else {
                                matrix[beeRow][beeCol] = 'B';
                            }
                        } else {
                            beeCol = rightBee;
                            matrix[beeRow][beeCol] = 'B';
                        }
                    } else {
                        System.out.println("The bee got lost!");
                        matrix[beeRow][beeCol] = '.';
                        beeIsLost = true;
                        break;

                    }

                    break;
            }

            if (beeIsLost) {
                break;
            }
            command = scanner.nextLine();
        }

        if (flowerCount >= 5) {
            System.out.println(String.format("Great job, the bee manage to pollinate %d flowers!", flowerCount));
        } else {
            System.out.println(String.format("The bee couldn't pollinate the flowers, she needed %d flowers more", (5 - flowerCount)));
        }


        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();

        }
    }
}
