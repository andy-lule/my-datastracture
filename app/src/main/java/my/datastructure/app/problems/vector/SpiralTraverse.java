package my.datastructure.app.problems.vector;

import java.util.ArrayList;
import java.util.List;

public final class SpiralTraverse {

    public static List<Integer> spiralTraverse(int[][] array) {
        final List<Integer> result = new ArrayList<>(array.length * array.length);
        int fromX = 0;
        int toX = array.length - 1;
        int fromY = 0;
        int toY = array[0].length - 1;
        while (fromX <= toX && fromY <= toY) {
            traverseRectangle(array, fromX, toX, fromY, toY, result);
            fromX++;
            toX--;
            fromY++;
            toY--;
        }

        return result;
    }

    private static void traverseRectangle(int[][] array, int fromX, int toX, int fromY, int toY, List<Integer> result) {
        for (int i = fromY; i <= toY; i++) {
            result.add(array[fromX][i]);
        }

        for (int i = fromX + 1; i <= toX; i++) {
            result.add(array[i][toY]);
        }

        for (int i = toY - 1; i >= fromY; i--) {
            if (fromX == toX) {
                break;
            }
            result.add(array[toX][i]);
        }

        for (int i = toX - 1; i > fromX; i--) {
            if (fromY == toY) {
                break;
            }
            result.add(array[i][fromY]);
        }
    }
}
