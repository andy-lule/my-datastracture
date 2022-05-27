package my.datastructure.app.problems.vector;

import java.util.Arrays;

public final class SmallestDifference {

    public static int[] findSmallestDifference(int[] arrayOne, int[] arrayTwo) {
        int smallestDiff = Integer.MAX_VALUE;
        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);
        int i = 0;
        int j = 0;
        final int[] result = new int[2];
        while (i < arrayOne.length && j < arrayTwo.length) {
            final int iVal = arrayOne[i];
            final int jVal = arrayTwo[j];
            if (iVal == jVal) {
                result[0] = iVal;
                result[1] = jVal;
                return result;
            }
            if (Math.abs(iVal - jVal) < smallestDiff) {
                smallestDiff = Math.abs(iVal - jVal);
                result[0] = iVal;
                result[1] = jVal;
            }

            if (iVal < jVal) {
                i++;
            } else {
                j++;
            }
        }

        return result;
    }
}
