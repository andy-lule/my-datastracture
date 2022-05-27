package my.datastructure.app.problems.string;

public final class RunLengthEncoding {

    public static String runLengthEncoding(String string) {
        final StringBuilder builder = new StringBuilder(string.length());
        int currCount = 1;
        char currChar = string.charAt(0);
        for (int i = 1; i < string.length(); i++) {
            if (currChar != string.charAt(i)) {
                buildEncode(builder, currCount, currChar);
                currChar = string.charAt(i);
                currCount = 1;
            } else {
                currCount++;
            }
        }

        buildEncode(builder, currCount, currChar);

        return builder.toString();
    }

    private static void buildEncode(StringBuilder builder, int currCount, char currChar) {
        for (int j = 0; j < currCount / 9; j++) {
            builder.append(9);
            builder.append(currChar);
        }
        if (currCount % 9 > 0) {
            builder.append(currCount % 9);
            builder.append(currChar);
        }
    }
}
