package my.datastructure.app.vector;

import my.datastructure.app.common.MyArrayUtils;

public class ResizableArray<T> {

    private static final int CAPACITY_RATE_FOR_INCREASING = 2;
    private static final int CAPACITY_RATE_FOR_DECREASING = 2;

    private T[] storage;
    private int currentSize = 0;

    public ResizableArray() {
        this(0);
    }

    public ResizableArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity cannot be less than 0");
        }
        //noinspection unchecked
        storage = (T[]) new Object[capacity];
    }

    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T at(int index) {
        throwIfIndexIsNotValid(index);
        return storage[index];
    }

    public void push(T item) {
        increaseCapacityIfNeed();
        storage[currentSize] = item;
        ++currentSize;
    }

    public void add(T item, int index) {
        throwIfIndexIsNotValid(index);
        increaseCapacityIfNeed();
        MyArrayUtils.copyArray(storage, index, storage, index + 1, size() - (index + 1));
        storage[index] = item;
        ++currentSize;
    }

    public void prependItem(T item) {
        increaseCapacityIfNeed();
        if (!isEmpty()) {
            MyArrayUtils.copyArray(storage, 0, storage, 1, size());
        }
        storage[0] = item;
        ++currentSize;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("ResizableArray is empty");
        }
        final int size = size();
        final T item = storage[size - 1];
        storage[size - 1] = null;
        --currentSize;
        decreaseCapacityIfNeed();
        return item;
    }

    public void deleteItem(int index) {
        final int size = size();
        throwIfIndexIsNotValid(index);
        if (index == size - 1) {
            storage[size - 1] = null;
        } else {
            MyArrayUtils.copyArray(storage, 0, storage, 0, index);
            MyArrayUtils.copyArray(storage, index + 1, storage, index, size - (index + 1));
        }
        --currentSize;
        decreaseCapacityIfNeed();
    }

    public int remove(T item) {
        final int foundItemIndex = findItem(item);
        if (foundItemIndex >= 0) {
            deleteItem(foundItemIndex);
        }

        return foundItemIndex;
    }

    public int findItem(T item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }

        Integer findingItemIndex = null;
        for (int i = 0; i < size(); i++) {
            T currItem = storage[i];
            if (item.equals(currItem)) {
                findingItemIndex = i;
                break;
            }
        }

        return findingItemIndex == null ? -1 : findingItemIndex;
    }

    private void increaseCapacityIfNeed() {
        if (needToIncreaseCapacity()) {
            resizeStorage(getCapacity() * CAPACITY_RATE_FOR_INCREASING);
        }
    }

    private void resizeStorage(int capacity) {
        @SuppressWarnings("unchecked") final T[] newStorage = (T[]) new Object[capacity];
        MyArrayUtils.copyArray(storage, 0, newStorage, 0, size());
        storage = newStorage;
    }

    private void decreaseCapacityIfNeed() {
        final int size = size();
        final int capacity = getCapacity();
        final int capacityThreshold = CAPACITY_RATE_FOR_DECREASING * 2;
        final boolean needToDecreaseCapacity = capacity >= capacityThreshold && size <= (capacity / capacityThreshold);
        if (needToDecreaseCapacity) {
            resizeStorage(capacity * CAPACITY_RATE_FOR_DECREASING);
        }
    }

    private boolean needToIncreaseCapacity() {
        return currentSize == getCapacity();
    }

    private void throwIfIndexIsNotValid(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    int getCapacity() {
        return storage.length;
    }
}
