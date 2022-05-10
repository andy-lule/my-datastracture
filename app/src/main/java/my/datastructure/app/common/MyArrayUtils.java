package my.datastructure.app.common;

import java.lang.reflect.Array;

public final class MyArrayUtils {

    public static void copyArray(Object src, int srcIndex, Object dest, int destIndex, int length) {
        if (src == null || !src.getClass().isArray()) {
            throw new IllegalArgumentException("src must be array");
        }
        if (dest == null || !dest.getClass().isArray()) {
            throw new IllegalArgumentException("dest must be array");
        }
        if (arrayIndexNotValid(src, srcIndex) || arrayIndexNotValid(dest, destIndex) || length <= 0) {
            return;
        }

        Object currSrc = src;
        if (currSrc == dest) {
            currSrc = new Object[Array.getLength(src)];
            for (int i = 0; i < Array.getLength(src); i++) {
                Array.set(currSrc, i, Array.get(src, i));
            }
        }

        for (int i = 0; i < length; i++) {
            Array.set(dest, i + destIndex, Array.get(currSrc, i + srcIndex));
        }
    }

    private static boolean arrayIndexNotValid(Object array, int index) {
        return index < 0 || index >= Array.getLength(array);
    }
}
