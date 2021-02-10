import java.util.Scanner;

public class Bees {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[n][n];

        int beeRow = -1;
        int beeCol = -1;

        for (int row = 0; row < n; row++) {
            String info = scanner.nextLine();
            for (int col = 0; col < n; col++) {
                matrix[row][col] = info.charAt(col);
                if (info.charAt(col) == 'B'){
                    beeRow = row;
                    beeCol = col;
                }
            }
        }




    }
}
