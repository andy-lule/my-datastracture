package my.datastructure.app.linkedlist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MyLinkedListTest {

    @Test
    public void pushFront_whenLinkedListHasData_thenVerifyNodeHasCorrectedOrder() {
        final MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.pushFront(10);
        linkedList.pushFront(20);
        linkedList.pushFront(30);
        final List<Integer> listValues = linkedList.toListValues();

        assertThat(linkedList.getSize()).isEqualTo(3);
        assertThat(listValues.size()).isEqualTo(3);
        assertThat(listValues.get(0)).isEqualTo(30);
        assertThat(listValues.get(1)).isEqualTo(20);
        assertThat(listValues.get(2)).isEqualTo(10);
    }

    @Test
    public void popBack_whenLinkedListHasData_ThenVerifyCorrectlyTail() {
        final MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.pushBack(10);
        linkedList.pushBack(20);
        linkedList.pushBack(30);
        linkedList.popBack();
        linkedList.popBack();

        assertThat(linkedList.getSize()).isEqualTo(1);
        assertThat(linkedList.getTailValue()).isEqualTo(10);
    }

    @Test
    public void insert_WhenIndexAtLast_ThenVerifyValueAtIndexCorrectly() {
        final MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.pushBack(10);
        linkedList.pushBack(20);
        linkedList.pushBack(30);
        linkedList.insert(2, 40);

        assertThat(linkedList.getTailValue()).isEqualTo(30);
        assertThat(linkedList.findValueAt(2)).isEqualTo(40);
    }

    @Test
    public void insert_WhenIndexAtMiddle_ThenVerifyValueAtIndexCorrectly() {
        final MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.pushBack(10);
        linkedList.pushBack(20);
        linkedList.pushBack(30);
        linkedList.pushBack(90);
        linkedList.pushBack(20);
        linkedList.pushBack(150);
        linkedList.insert(3, 40);

        assertThat(linkedList.getTailValue()).isEqualTo(150);
        assertThat(linkedList.findValueAt(3)).isEqualTo(40);
    }

    @Test
    public void removeAt_WhenIndexAtLast_ThenVerifyTailCorrectly() {
        final MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.pushBack(10);
        linkedList.pushBack(20);
        linkedList.pushBack(80);
        linkedList.removeAt(linkedList.getSize() - 1);

        assertThat(linkedList.getSize()).isEqualTo(2);
        assertThat(linkedList.getHeadValue()).isEqualTo(10);
        assertThat(linkedList.getTailValue()).isEqualTo(20);
    }

    @Test
    public void removeAt_WhenIndexAtFirst_ThenVerifyTailCorrectly() {
        final MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.pushBack(10);
        linkedList.pushBack(20);
        linkedList.pushBack(80);
        linkedList.removeAt(0);

        assertThat(linkedList.getSize()).isEqualTo(2);
        assertThat(linkedList.getHeadValue()).isEqualTo(20);
        assertThat(linkedList.getTailValue()).isEqualTo(80);
    }

    @ParameterizedTest
    @MethodSource("provideReverseTestData")
    public void reverse_whenInput_ThenReverseCorrectly(List<Integer> input, List<Integer> output) {
        final MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        for (Integer i : input) {
            linkedList.pushBack(i);
        }
        linkedList.reverse();
        final List<Integer> actualOutput = linkedList.toListValues();
        assertThat(output).containsExactlyElementsOf(actualOutput);
    }

    @Test
    public void remove_whenFoundValue_thenVerifyLinkedListCorrectly() {
        final MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.pushBack(50);
        linkedList.pushBack(11);
        linkedList.pushBack(85);
        linkedList.pushBack(33);
        linkedList.remove(85);

        assertThat(linkedList.getSize()).isEqualTo(3);
        assertThat(linkedList.toListValues()).containsExactlyElementsOf(List.of(50, 11, 33));
    }

    @Test
    public void remove_whenFoundValueAtLastItem_thenVerifyLinkedListCorrectly() {
        final MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.pushBack(50);
        linkedList.pushBack(11);
        linkedList.pushBack(85);
        linkedList.pushBack(33);
        linkedList.remove(33);

        assertThat(linkedList.getSize()).isEqualTo(3);
        assertThat(linkedList.toListValues()).containsExactlyElementsOf(List.of(50, 11, 85));
    }

    private static Stream<Arguments> provideReverseTestData() {
        return Stream.of(
                arguments(List.of(1, 2, 3, 4, 5, 6), List.of(6, 5, 4, 3, 2, 1)),
                arguments(List.of(11, 22, 33, 44, 89, 7, 6), List.of(6, 7, 89, 44, 33, 22, 11))
        );
    }
}
