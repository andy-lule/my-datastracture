package my.datastructure.app.problems.string;

public final class CaesarCypherEncryptor {

    public static String caesarCypherEncryptor(String str, int key) {
        final String alphabetChars = "abcdefghijklmnopqrstuvwxyz";
        final char[] chars = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            int normalizedIndex = (alphabetChars.indexOf(str.charAt(i)) + key) % 26;
            chars[i] = alphabetChars.charAt(normalizedIndex);
        }

        return new String(chars);
    }
}
