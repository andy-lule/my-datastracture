package my.datastructure.app.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class MyLinkedList<T> {

    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T findValueAt(int index) {
        if (head == null) {
            return null;
        }
        int i = 0;
        Node<T> curr = head;
        while (curr != null) {
            if (i == index) {
                return curr.getValue();
            }
            curr = curr.getNext();
            i++;
        }
        return null;
    }

    public void pushFront(T value) {
        if (head == null) {
            head = new Node<>(value, null);
            tail = head;
        } else {
            head = new Node<>(value, head);
        }
        size++;
    }

    public boolean popFront() {
        if (head == null) {
            return false;
        }
        head = head.getNext();
        if (head == null || head.getNext() == null) {
            tail = head;
        }
        size--;
        return true;
    }

    public void pushBack(T value) {
        if (head == null) {
            head = new Node<>(value, null);
            tail = head;
        } else {
            tail.setNext(new Node<>(value, null));
            tail = tail.getNext();
        }
        size++;
    }

    public boolean popBack() {
        if (head == null) {
            return false;
        }

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node<T> prev = head;
            Node<T> curr = prev.getNext();
            while (curr != null && curr.getNext() != null) {
                prev = prev.getNext();
                curr = curr.getNext();
            }

            prev.setNext(null);
            tail = prev;
        }

        size--;
        return true;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public T getHeadValue() {
        final Node<T> currHead = head;
        return currHead == null ? null : currHead.getValue();
    }

    public T getTailValue() {
        final Node<T> currTail = tail;
        return currTail == null ? null : currTail.getValue();
    }

    public void insert(int index, T value) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(index);
        }
        if (index == 0) {
            pushFront(value);
            return;
        }
        int i = 1;
        Node<T> prev = head;
        Node<T> curr = head.getNext();
        while (curr != null) {
            if (index == i) {
                final Node<T> nodeAtIndex = new Node<>(value, curr);
                prev.setNext(nodeAtIndex);
                break;
            }
            prev = prev.getNext();
            curr = curr.getNext();
            i++;
        }
        size++;
    }

    public void removeAt(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(index);
        }

        if (index == 0) {
            popFront();
            return;
        }

        int i = 1;
        Node<T> prev = head;
        Node<T> curr = head.getNext();
        while (curr != null) {
            if (i == index) {
                prev.setNext(curr.getNext());
                if (prev.getNext() == null) {
                    tail = prev;
                }
                break;
            }
            prev = prev.getNext();
            curr = curr.getNext();
            i++;
        }
        size--;
    }

    public void reverse() {
        if (head == null || head == tail) {
            return;
        }
        Node<T> newHead = null;
        Node<T> newTail = head;
        Node<T> curr = head;
        while (curr != null) {
            final Node<T> prevHead = newHead;
            newHead = curr;
            curr = curr.getNext();
            newHead.setNext(prevHead);
        }
        newTail.setNext(null);
        head = newHead;
        tail = newTail;
    }

    public boolean remove(T value) {
        if (head == null) {
            return false;
        }
        if (head == tail) {
            if (head.getValue().equals(value)) {
                head = null;
                tail = null;
                size--;
                return true;
            }
            return false;
        }
        boolean success = false;
        Node<T> prev = null;
        Node<T> curr = head;
        while (curr != null) {
            if (curr.getValue().equals(value)) {
                if (curr == head) {
                    head = curr.getNext();
                }

                if (curr == tail) {
                    tail = prev;
                }

                if (prev != null) {
                    prev.setNext(curr.getNext());
                }
                size--;
                success = true;
                break;
            }
            prev = curr;
            curr = curr.getNext();
        }

        return success;
    }

    public List<T> toListValues() {
        List<T> list = new ArrayList<>();
        Node<T> curr = head;
        while (curr != null) {
            list.add(curr.getValue());
            curr = curr.getNext();
        }
        return list;
    }

    public static class Node<T> {
        private final T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
