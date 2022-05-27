package my.datastructure.app.problems.string;

import java.util.HashMap;
import java.util.Map;

public final class GenerateDocument {
    public static boolean generateDocument(String characters, String document) {
        if ("".equals(document)) {
            return true;
        }
        final Map<Character, Integer> availableStore = new HashMap<>();
        for (int i = 0; i < characters.length(); i++) {
            final Character c = characters.charAt(i);
            availableStore.put(c, availableStore.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < document.length(); i++) {
            final Character d = document.charAt(i);
            final int remainCount = availableStore.getOrDefault(d, 0);
            if (remainCount == 0) {
                return false;
            }
            availableStore.put(d, remainCount - 1);
        }

        return true;
    }
}

