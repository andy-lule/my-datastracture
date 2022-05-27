package my.datastructure.app.problems.string;

public final class URLify {

    public static String urlify(String input) {
        int spaceCountAtTheEnd = 0;
        for (int j = input.length() - 1; j >= 0; j--) {
            if (input.charAt(j) == ' ') {
                spaceCountAtTheEnd++;
            } else {
                break;
            }
        }

        int lengthToSpaceCount = input.length() - spaceCountAtTheEnd;
        char[] chars = new char[lengthToSpaceCount];
        for (int i = 0; i < lengthToSpaceCount; i++) {
            char c = input.charAt(i);
            if (c == ' ') {
                chars[i] = '%';
            } else {
                chars[i] = c;
            }
        }

        return new String(chars);
    }
}
