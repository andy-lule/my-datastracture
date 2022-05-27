package my.datastructure.app.problems.string;

public final class StringIsPermutation {

    public static boolean isPermutation(String a1, String a2) {
        if (a1 == null) {
            throw new IllegalArgumentException("a1 cannot be nul");
        }
        if (a2 == null) {
            throw new IllegalArgumentException("a2 cannot be nul");
        }
        return isPermutationOf(a1, a2) || isPermutationOf(a2, a1);
    }

    private static boolean isPermutationOf(String input, String forComparing) {
        int i = input.length() - 1;
        for (int j = 0; j < forComparing.length(); j++) {
            final char c = forComparing.charAt(j);
            if (input.charAt(i) != c) {
                i = input.length() - 1;
            }

            if (input.charAt(i) == c) {
                i--;
                if (i < 0) {
                    return true;
                }
            }
        }

        return false;
    }
}
