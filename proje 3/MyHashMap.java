
package com.mycompany.graph1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 5000000;
    public Entry<K, V>[] buckets;
    private int size;

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashMap(int capacity) {
        this.buckets = new Entry[capacity];
        this.size = 0;
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new Entry<>(key, value);
        } else {
            Entry<K, V> current = buckets[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next = new Entry<>(key, value);
        }
        size++;
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void remove(K key) {
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];
        Entry<K, V> prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

    public void putIfAbsent(K key, V value) {
        if (get(key) == null) {
            put(key, value);
        }
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        return value != null ? value : defaultValue;
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public Iterable<K> keySet() {
        return () -> new Iterator<K>() {
            private int index = 0;
            private int count = 0;

            @Override
            public boolean hasNext() {
                while (index < buckets.length && (buckets[index] == null || count == 0)) {
                    index++;
                    count = 0;
                }
                return index < buckets.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (buckets[index] == null || count == 0) {
                    index++;
                    count = 0;
                }
                count++;
                return buckets[index].key;
            }
        };
    }
public Iterable<Entry<K, V>> entries() {
    return () -> new Iterator<Entry<K, V>>() {
        private int index = 0;
        private Entry<K, V> current = null;

        @Override
        public boolean hasNext() {
            if (current != null && current.next != null) {
                return true;
            }
            while (index < buckets.length && buckets[index] == null) {
                index++;
            }
            return index < buckets.length;
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (current == null || current.next == null) {
                current = buckets[index++];
            } else {
                current = current.next;
            }
            return current;
        }
    };
}

    public Iterable<V> values() {
        return () -> new Iterator<V>() {
            private int index = 0;
            private Entry<K, V> currentEntry = null;

            @Override
            public boolean hasNext() {
                if (currentEntry != null && currentEntry.next != null) {
                    return true;
                }
                while (index < buckets.length && buckets[index] == null) {
                    index++;
                }
                return index < buckets.length;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (currentEntry == null || currentEntry.next == null) {
                    currentEntry = buckets[index++];
                } else {
                    currentEntry = currentEntry.next;
                }
                return currentEntry.value;
            }
        };
    }

    public int getIndex(K key) {
        int index = getBucketIndex(key);

        Entry<K, V> current = buckets[index];
        int count = 0;

        while (current != null) {
            if (current.key.equals(key)) {
                return index * DEFAULT_CAPACITY + count;
            }
            count++;
            current = current.next;
        }

        return -1; // If the key is not found in the hashmap
    }

    public static class Entry<K, V> {

        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

}
