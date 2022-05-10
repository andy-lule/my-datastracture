package my.datastructure.app.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MyArrayUtilsTest {

    @Test
    public void copyArray_whenCopiedFromIndex0_thenTwoArraySequenceEqual() {
        final int[] array1 = new int[]{1, 2, 3, 4, 9, 12};
        final int[] destArray = new int[6];
        MyArrayUtils.copyArray(array1, 0, destArray, 0, 6);

        assertThat(array1).containsSequence(destArray);
    }

    @Test
    public void copyArray_whenCopiedFromOtherIndex_thenTwoArraySequenceEqual() {
        final int[] array1 = new int[]{1, 2, 3, 4, 9, 12};
        final int[] destArray = new int[4];
        MyArrayUtils.copyArray(array1, 2, destArray, 0, 2);

        assertThat(destArray).containsSequence(3, 4, 0, 0);
    }
}
