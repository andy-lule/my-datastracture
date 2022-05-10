package my.datastructure.app.vector;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ResizableArrayTest {

    @Test
    public void createResizableArrayWithEmptyConstructor_whenCreatedSuccessfully_thenCapacityIsZero() {
        final ResizableArray<Integer> resizableArray = new ResizableArray<>();
        assertThat(resizableArray.getCapacity()).isEqualTo(0);
    }

    @Test
    public void createResizableArray_whenCapacityIsNotValid_ThenThrownIllegalArgumentException() {
        assertThatThrownBy(() -> new ResizableArray<Integer>(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void createResizableArray_whenCapacityIsValid_ThenCreateSuccessfully() {
        final ResizableArray<Integer> resizableArray = new ResizableArray<>(10);
        assertThat(resizableArray.isEmpty()).isTrue();
    }

    @Test
    public void pushItem_whenPushSuccessfully_thenItemExistsInStorage() {
        final ResizableArray<Integer> resizableArray = new ResizableArray<>(10);
        resizableArray.push(20);
        final Integer expectedValue = resizableArray.at(0);
        assertThat(expectedValue).isEqualTo(20);
        assertThat(resizableArray.size()).isEqualTo(1);
    }

    @Test
    public void pushItem_whenCapacityIsNeededToIncrease_thenIncreaseCapacitySuccessfullyAndItemExistsInStorage() {
        final ResizableArray<Integer> resizableArray = new ResizableArray<>(1);
        resizableArray.push(20);
        resizableArray.push(10);
        assertThat(resizableArray.getCapacity()).isEqualTo(2);
    }

    @Test
    public void addItem_whenItemIsAddedSuccessfully_thenVerifyItemAtIndexCorrectly() {
        final ResizableArray<Integer> resizableArray = new ResizableArray<>(5);
        resizableArray.push(40);
        resizableArray.push(50);
        resizableArray.push(20);
        resizableArray.add(10, 1);
        assertThat(resizableArray.at(1)).isEqualTo(10);
        assertThat(resizableArray.at(2)).isEqualTo(50);
        assertThat(resizableArray.size()).isEqualTo(4);
    }

    @Test
    public void prependItem_whenResizableArrayIsEmpty_thenItemOrderIsCorrect() {
        final ResizableArray<Integer> resizableArray = new ResizableArray<>(2);
        resizableArray.prependItem(25);

        assertThat(resizableArray.at(0)).isEqualTo(25);
    }

    @Test
    public void prependItem_whenResizableArrayIsNotEmpty_thenItemOrdersAreCorrect() {
        final ResizableArray<Integer> resizableArray = new ResizableArray<>(1);
        resizableArray.push(1);
        resizableArray.push(2);
        resizableArray.push(3);
        resizableArray.push(4);
        resizableArray.prependItem(5);

        assertThat(resizableArray.at(0)).isEqualTo(5);
        assertThat(resizableArray.at(1)).isEqualTo(1);
        assertThat(resizableArray.at(2)).isEqualTo(2);
        assertThat(resizableArray.at(3)).isEqualTo(3);
        assertThat(resizableArray.at(4)).isEqualTo(4);
    }

    @Test
    public void popItem_WhenResizableArrayHasOnlyOneItem_ThenVectorIsEmpty() {
        final ResizableArray<Integer> resizableArray = new ResizableArray<>(2);
        resizableArray.push(40);
        final Integer expectedItem = resizableArray.pop();

        assertThat(expectedItem).isEqualTo(40);
        assertThat(resizableArray.isEmpty()).isTrue();
        assertThatThrownBy(() -> resizableArray.at(0)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void deleteItem_whenAtFirstPosition_ThenVerifyItemOrdersAreCorrect() {
        final ResizableArray<Integer> resizableArray = new ResizableArray<>(5);
        resizableArray.push(10);
        resizableArray.push(20);
        resizableArray.push(30);
        resizableArray.push(40);
        resizableArray.push(5);
        resizableArray.deleteItem(0);

        assertThat(resizableArray.size()).isEqualTo(4);
        assertThat(resizableArray.at(0)).isEqualTo(20);
        assertThat(resizableArray.at(1)).isEqualTo(30);
        assertThat(resizableArray.at(2)).isEqualTo(40);
        assertThat(resizableArray.at(3)).isEqualTo(5);
    }

    @Test
    public void deleteItem_whenAtLastPosition_ThenVerifyItemOrdersAreCorrect() {
        final ResizableArray<Integer> resizableArray = new ResizableArray<>(5);
        resizableArray.push(70);
        resizableArray.push(80);
        resizableArray.push(43);
        resizableArray.push(22);
        resizableArray.push(95);
        resizableArray.deleteItem(4);

        assertThat(resizableArray.size()).isEqualTo(4);
        assertThat(resizableArray.at(0)).isEqualTo(70);
        assertThat(resizableArray.at(1)).isEqualTo(80);
        assertThat(resizableArray.at(2)).isEqualTo(43);
        assertThat(resizableArray.at(3)).isEqualTo(22);
    }

    @Test
    public void deleteItem_whenAtMiddlePosition_ThenVerifyItemOrdersAreCorrect() {
        final ResizableArray<Integer> resizableArray = new ResizableArray<>(5);
        resizableArray.push(70);
        resizableArray.push(80);
        resizableArray.push(43);
        resizableArray.push(22);
        resizableArray.push(95);
        resizableArray.deleteItem(2);

        assertThat(resizableArray.size()).isEqualTo(4);
        assertThat(resizableArray.at(0)).isEqualTo(70);
        assertThat(resizableArray.at(1)).isEqualTo(80);
        assertThat(resizableArray.at(2)).isEqualTo(22);
        assertThat(resizableArray.at(3)).isEqualTo(95);
    }

    @Test
    public void removeItem_whenItemExists_thenReturnsCorrectlyIndex() {
        final ResizableArray<Integer> resizableArray = new ResizableArray<>(8);
        resizableArray.push(1233);
        resizableArray.push(8999);
        resizableArray.push(74);
        resizableArray.push(-89);
        resizableArray.push(99654);

        Integer expectedItemIndex = resizableArray.remove(74);
        assertThat(expectedItemIndex).isEqualTo(2);
        assertThat(resizableArray.size()).isEqualTo(4);
    }

    @Test
    public void removeItem_whenItemIsNotFound_thenReturnsCorrectlyIndex() {
        final ResizableArray<Integer> resizableArray = new ResizableArray<>(8);
        resizableArray.push(1233);
        resizableArray.push(8999);
        resizableArray.push(74);
        resizableArray.push(-89);
        resizableArray.push(99654);

        Integer expectedItemIndex = resizableArray.remove(9845454);
        assertThat(expectedItemIndex).isEqualTo(-1);
        assertThat(resizableArray.size()).isEqualTo(5);
    }
}
