package org.example.myhashmap;

public class MyHashMap implements MyMap {
    private static class Node {
        private final int key;
        private long value;
        private boolean deleted = false;

        public Node(Integer key, Long value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private double loadFactor;
    private int capacity;
    Node[] table;
    private int size;

    public MyHashMap() {
        loadFactor = DEFAULT_LOAD_FACTOR;
        capacity = DEFAULT_CAPACITY;
        table = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyHashMap(int capacity, double loadFactor) {
        this.loadFactor = loadFactor;
        this.capacity = capacity;
        this.table = new Node[capacity];
        this.size = 0;
    }

    private int indexFor(int key) {
        return Math.abs(hash(key) % table.length);
    }

    private void resize() {
        size = 0;
        capacity = capacity * 2;
        Node[] oldTable = table;
        table = new Node[capacity];
        transfer(oldTable);
    }

    public void transfer(Node[] oldTable) {
        for (Node node : oldTable) {
            if (node != null) {
                put(node.key, node.value);
            }
        }
    }

    @Override
    public void put(int key, long value) {
        if (size >= capacity * loadFactor) {
            resize();
        }
        int index = indexFor(key);
        if (table[index] == null) {
            table[index] = new Node(key, value);
            size++;
            return;
        }
        while (table[index] != null) {
            if (key == table[index].key) {
                table[index].value = value;
                return;
            }
            index++;
            if (index == capacity) {
                index = 0;
            }
        }
        table[index] = new Node(key, value);
        size++;
    }

    private  int hash(int key) {
        return key ^ (key >>> 16);
    }

    @Override
    public long get(int key) {
        int index = indexFor(key);
        while (table[index] != null) {
            if (key == table[index].key && !table[index].deleted) {
                return table[index].value;
            }
            index++;
            if (index == capacity) {
                index = 0;
            }
        }
        return 0;
    }

    @Override
    public int size() {
        return size;
    }
    
}

