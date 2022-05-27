package my.datastructure.app.problems.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class StringIsUniqueCharacter {

    /*
     * This algorithm takes:
     * Time complexity: O(N)
     * Space complexity: O(N)
     * */
    public static boolean isUniqueWithMap(String input) {
        final Map<Character, Boolean> charStorage = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            final char aChar = input.charAt(i);
            if (charStorage.getOrDefault(aChar, false)) {
                return false;
            }
            charStorage.put(aChar, true);
        }
        return true;
    }

    /*
     * This algorithm takes:
     * Time complexity: O(N * logN)
     * Space complexity: O(N)
     * */
    public static boolean isUniqueWithoutDataStructure(String input) {
        final char[] sortedChars = input.toCharArray();
        Arrays.sort(sortedChars);
        for (int i = 0; i < sortedChars.length - 1; i++) {
            if (sortedChars[i] == sortedChars[i + 1]) {
                return false;
            }
        }

        return true;
    }
}
