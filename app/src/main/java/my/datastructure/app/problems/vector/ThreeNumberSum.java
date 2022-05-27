package my.datastructure.app.problems.vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ThreeNumberSum {

    public static List<Integer[]> threeNumberSum(Integer[] array, int targetSum) {
        Arrays.sort(array);
        final List<Integer[]> result = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            int pLeft = i + 1;
            int pRight = array.length - 1;

            while (pLeft < pRight) {
                int sum = array[i] + array[pLeft] + array[pRight];
                if (sum == targetSum) {
                    result.add(new Integer[]{array[i], array[pLeft], array[pRight]});
                    pLeft++;
                    pRight--;
                } else if (sum < targetSum) {
                    pLeft++;
                } else {
                    pRight--;
                }
            }
        }

        return result;
    }
}


