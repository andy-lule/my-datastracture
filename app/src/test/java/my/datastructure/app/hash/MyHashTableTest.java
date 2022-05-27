package my.datastructure.app.hash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MyHashTableTest {

    @Test
    public void get_whenHashTableHasData_thenReturnValueCorrectly() {
        final MyHashTable<String, Integer> hashTable = new MyHashTable<>();
        hashTable.put("lalaland", 1000);
        final Integer actualValue = hashTable.get("lalaland");

        assertThat(actualValue).isEqualTo(1000);
    }

    @Test
    public void resize_whenNormalizeHashIsDifferentWithIndex_thenVerifyLinkedListIsSplit() {
        final MyHashTable<Integer, Integer> hashTable = new MyHashTable<>();
        hashTable.store = MyHashTable.newStore(16);
        hashTable.store[2] = new MyHashTable.Node<>(2, 2, MyHashTable.hash(2));
        MyHashTable.Node<Integer, Integer> curr = hashTable.store[2];
        for (Integer i : new int[]{18, 34, 50}) {
            curr.next = new MyHashTable.Node<>(i, i, MyHashTable.hash(i));
            curr = curr.next;
        }
        hashTable.size = 4;
        hashTable.resize();

        Assertions.assertAll("MyHashTable resize correctly",
                () -> assertThat(hashTable.store.length).isGreaterThan(16),
                () -> assertThat(hashTable.store[2].key).isEqualTo(2),
                () -> assertThat(hashTable.store[2].next.key).isEqualTo(34),
                () -> assertThat(hashTable.store[18].key).isEqualTo(18),
                () -> assertThat(hashTable.store[18].next.key).isEqualTo(50)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataTestForRemoveMethod")
    public void remove_withDataTest(MyHashTable<Integer, Integer> myHashTable, Integer key, Integer expectedValue) {
        final Integer actualValue = myHashTable.get(key);

        assertThat(actualValue).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> provideDataTestForRemoveMethod() {
        final MyHashTable<Integer, Integer> myHashTable = new MyHashTable<>();
        final Arguments[] argumentsArray = new Arguments[1000];
        for (int i = 0; i < argumentsArray.length; i++) {
            argumentsArray[i] = Arguments.arguments(myHashTable, i, i);
            myHashTable.put(i, i);
        }

        return Arrays.stream(argumentsArray);
    }
}
