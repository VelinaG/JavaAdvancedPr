import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Bombs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> bombEffectQueue = new ArrayDeque<>();
        ArrayDeque<Integer> casingStack = new ArrayDeque<>();

        int[] input = Arrays.stream(scanner.nextLine().split("\\, "))
                .mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < input.length; i++) {
            bombEffectQueue.offer(input[i]);
        }

        input = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < input.length; i++) {
            casingStack.push(input[i]);
        }

        int daturaBombsCount = 0; //40
        int cherryBombsCount = 0;  //60
        int smokeDecoyBombsCount = 0; //120
        boolean enoughBombs = false;


        while (!bombEffectQueue.isEmpty() && !casingStack.isEmpty()) {

            int sum = bombEffectQueue.peek() + casingStack.peek();

            if (sum == 40 || sum == 60 || sum == 120) {
                bombEffectQueue.poll();
                casingStack.pop();
                switch (sum){
                    case 40:
                        daturaBombsCount++;
                        break;

                    case 60:
                        cherryBombsCount++;
                        break;

                    case 120:
                        smokeDecoyBombsCount++;
                        break;
                }
                if (daturaBombsCount > 2 && cherryBombsCount > 2 && smokeDecoyBombsCount > 2){
                    enoughBombs = true;
                }
            } else {
                int current = casingStack.pop() - 5;
                casingStack.push(current);
            }
            if (enoughBombs){
                break;
            }
        }

        if (enoughBombs){
            System.out.println("Bene! You have successfully filled the bomb pouch!");
        } else {
            System.out.println("You don't have enough materials to fill the bomb pouch.");
        }

        if (bombEffectQueue.isEmpty()){
            System.out.println("Bomb Effects: empty");
        } else {
            System.out.print("Bomb Effects: ");
            while (bombEffectQueue.size() > 1){
                System.out.print(bombEffectQueue.poll() + ", ");
            }
            System.out.print(bombEffectQueue.poll());
            System.out.println();

        }

        if (casingStack.isEmpty()){
            System.out.println("Bomb Casings: empty");
        } else {
            System.out.print("Bomb Casings: ");
            while (casingStack.size() > 1){
                System.out.print(casingStack.pollLast() + ", ");
            }
            System.out.print(casingStack.pollLast());
            System.out.println();
        }

        System.out.println(String.format("Cherry Bombs: %d%nDatura Bombs: %d%nSmoke Decoy Bombs: %d",
                cherryBombsCount, daturaBombsCount, smokeDecoyBombsCount));
    }
}
