package my.datastructure.app.hash;

import java.util.Objects;

public class MyHashTable<K, V> {

    public static class Node<K, V> {
        final K key;
        V value;
        final int hash;
        Node<K, V> next;

        public Node(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }

    static <K> int hash(K key) {
        if (key == null) {
            return 0;
        }
        int h = key.hashCode();
        return h ^ (h >>> 16);
    }

    static int normalizeHash(int hash, int tableSize) {
        return hash & tableSize - 1;
    }

    static <K, V> Node<K, V>[] newStore(int capacity) {
        //noinspection unchecked
        return new Node[capacity];
    }


    int size;
    Node<K, V>[] store;

    public int getCapacity() {
        final Node<K, V>[] s = store;
        return s == null ? 0 : s.length;
    }

    public V put(K key, V value) {
        Node<K, V>[] s = store;
        int n;
        if (s == null || s.length == 0) {
            s = resize();
        }

        n = s.length;
        final int hash = hash(key);
        final int index = normalizeHash(hash, n);
        if (s[index] == null) {
            s[index] = new Node<>(key, value, hash);
        } else {
            Node<K, V> curr = s[index];
            while (curr != null) {
                if (key != null && Objects.equals(key, curr.key)) {
                    final V oldValue = curr.value;
                    curr.value = value;
                    return oldValue;
                }
                if (curr.next == null) {
                    curr.next = new Node<>(key, value, hash);
                    break;
                }
                curr = curr.next;
            }
        }
        if (++size >= n) {
            resize();
        }
        return null;
    }

    public V get(K key) {
        final Node<K, V> foundNode = findNode(key);
        if (foundNode == null) {
            return null;
        }
        return foundNode.value;
    }

    public boolean containsKey(K key) {
        return findNode(key) != null;
    }

    public V remove(K key) {
        final Node<K, V> removedNode = removeNode(key);
        return removedNode == null ? null : removedNode.value;
    }

    Node<K, V> removeNode(K key) {
        final Node<K, V>[] s = store;
        if (s == null || s.length == 0 || size == 0) {
            return null;
        }
        final int index = normalizeHash(hash(key), s.length);
        Node<K, V> prev = null;
        Node<K, V> curr = s[index];
        while (curr != null) {
            if (key != null && Objects.equals(key, curr.key)) {
                if (prev == null) {
                    s[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                --size;
                return curr;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    Node<K, V> findNode(K key) {
        final Node<K, V>[] s = store;
        if (s == null || s.length == 0 || size == 0) {
            return null;
        }
        final int index = normalizeHash(hash(key), s.length);
        Node<K, V> curr = s[index];
        while (curr != null) {
            if (key != null && Objects.equals(key, curr.key)) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    Node<K, V>[] resize() {
        final int cap = getCapacity();
        final Node<K, V>[] s = store;
        Node<K, V>[] nextStore;
        if (cap <= 0) {
            nextStore = newStore(16);
        } else {
            nextStore = newStore(cap << 1);
        }

        store = nextStore;
        if (s == null) {
            return nextStore;
        }

        for (int i = 0; i < s.length; i++) {
            final Node<K, V> n = s[i];
            s[i] = null;
            if (n == null) {
                continue;
            }

            if (n.next == null) {
                nextStore[normalizeHash(n.hash, nextStore.length)] = n;
            } else {
                Node<K, V> curr = n;
                Node<K, V> loHead = null, loTail = null;
                Node<K, V> hiHead = null, hiTail = null;
                while (curr != null) {
                    if (normalizeHash(curr.hash, nextStore.length) == i) {
                        if (loHead == null) {
                            loHead = curr;
                        } else {
                            loTail.next = curr;
                        }
                        loTail = curr;
                    } else {
                        if (hiHead == null) {
                            hiHead = curr;
                        } else {
                            hiTail.next = curr;
                        }
                        hiTail = curr;
                    }
                    curr = curr.next;
                }

                if (loHead != null) {
                    loTail.next = null;
                    nextStore[i] = loHead;
                }
                if (hiHead != null) {
                    hiTail.next = null;
                    nextStore[normalizeHash(hiHead.hash, nextStore.length)] = hiHead;
                }
            }
        }

        return nextStore;
    }
}
