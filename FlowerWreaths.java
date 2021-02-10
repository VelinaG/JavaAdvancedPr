import com.sun.source.tree.IfTree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class FlowerWreaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] firstLine = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();

        int[] secondLine = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> liliesStack = new ArrayDeque<>();
        ArrayDeque<Integer> rosesQueue = new ArrayDeque<>();

        int wreathsCount = 0;

        for (int value : firstLine) {
            liliesStack.push(value);
        }

        for (int value : secondLine) {
            rosesQueue.offer(value);
        }

        int leftOverFlowers = 0;


        while (liliesStack.size() > 0){

            int currentSum = 0;

            currentSum += liliesStack.pop();
            currentSum += rosesQueue.poll();

            if (currentSum == 15) {
                wreathsCount++;

            } else if (currentSum >= 15) {
                int afterSum = checkIfYouCanMakeWreath(currentSum);
                if (afterSum == 15) {
                    wreathsCount++;
                } else {
                    leftOverFlowers += afterSum;
                }

            } else {
                leftOverFlowers += currentSum;
            }

        }

        wreathsCount += makeMoreWreaths(leftOverFlowers);

        if (wreathsCount >= 5){
            System.out.println(String.format("You made it, you are going to the competition with %d wreaths!", wreathsCount));
        } else {
            System.out.println(String.format("You didn't make it, you need %d wreaths more!", 5 - wreathsCount));
        }
    }

    private static int makeMoreWreaths(int flowers) {
        int wreathCount = 0;
        while (flowers > 15){
            flowers -= 15;
            wreathCount++;
        }

        return wreathCount;
    }

    private static int checkIfYouCanMakeWreath(int currentSum) {
        int afterTakeAwayTwo = currentSum - 2;
        while (afterTakeAwayTwo > 15) {
            afterTakeAwayTwo -= 2;
        }

        return afterTakeAwayTwo;
    }
}
