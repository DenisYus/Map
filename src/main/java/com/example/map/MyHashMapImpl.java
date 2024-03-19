package com.example.map;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MyHashMapImpl<K, V> implements MyHashMap<K, V> {
    private Node<K,V>[] hashTable;
    private int size = 0;
    private float threshold;
    private final float LOAD_FACTOR = 0.75f;
    private final int BASE_INITIAL_CAPACITY = 16;
    public MyHashMapImpl() {
        hashTable = new Node[BASE_INITIAL_CAPACITY ];
        threshold = hashTable.length * LOAD_FACTOR;
    }

    @Override
    public boolean put(K key, V value) {
        if (size + 1 >= threshold){
            threshold *= 2;
            arrayDoubling();
        }
        Node<K,V> newNode = new Node<>(key, value);
        int index = hash(key);
        if (hashTable[index] == null){
            return simpleAdd(index, newNode);
        }
        List<Node<K,V>> nodeList = hashTable[index].getNodes();
        for (Node<K,V> node : nodeList){
            if (keyExistButValueNew(node, newNode, value) || collision(node, newNode, nodeList)){
                return true;
            }
        }
        if (!hashTable[index].getNodes().isEmpty()){
            hashTable[index].getNodes().add(newNode);
            return true;
        }
        return false;
    }
    private boolean simpleAdd(int index, Node<K,V> newNode){
        hashTable[index] = new Node<>(null, null);
        hashTable[index].getNodes().add(newNode);
        size++;
        return true;
    }
    private boolean keyExistButValueNew(final Node<K,V> nodeFromList,
                                        final Node<K,V> newNode,final V value){
        if (newNode.getKey().equals(nodeFromList.getKey()) &&
                !newNode.getValue().equals(nodeFromList.getValue())){
            nodeFromList.setValue(value);
            return true;
        }
        return false;
    }
    private boolean collision(final Node<K,V> nodeFromList,
                               final Node<K,V> newNode,
                               final List<Node<K,V>>  nodes){
        if (newNode.hashCode() == nodeFromList.hashCode() &&
        !Objects.equals(newNode.key, nodeFromList.key) &&
        !Objects.equals(newNode.value, nodeFromList.value)){
            nodes.add(newNode);
            size++;
            return  true;
        }
        return false;
    }
    private void arrayDoubling(){
        Node<K,V>[] oldHashTable = hashTable;
        hashTable = new Node[oldHashTable.length *2];
        size = 0;
        for (Node<K,V> node : oldHashTable){
            if (node!= null){
                for (Node<K,V> n: node.getNodes()){
                    put(n.key, n.value);
                }
            }
        }
    }

    @Override
    public boolean remove(K key) {
        int index  = hash(key);
        if (hashTable[index] == null){
            return false;
        }
        if (hashTable[index].getNodes().size()==1){
            hashTable[index] = null;
            size--;
            return true;
        }
        List<Node<K,V>> nodeList = hashTable[index].getNodes();
        for (Node<K,V> node : nodeList){
            if (key.equals(node.getKey())){
                nodeList.remove(node);
                size--;
                return true;
            }
        }
        return false;
    }
    private int hash(K key){
        int hash = 31;
        hash = hash *17 + key.hashCode();
        return  hash % hashTable.length;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        if (index<hashTable.length && hashTable[index] != null){
            List<Node<K,V>> list = hashTable[index].getNodes();
            for (Node<K,V> node : list){
                if (key.equals(node.getKey())){
                    return node.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int counterArray = -1;
            int valueCounter = 0;
            Iterator<Node<K,V>> subIterator = null;

            @Override
            public boolean hasNext() {
                if (valueCounter == size) {
                    return false;
                }
                if (subIterator == null || !subIterator.hasNext()){
                    if (moveToNext()){
                        subIterator = hashTable[counterArray].getNodes().iterator();
                    } else {
                        return false;
                    }
                }
                return subIterator.hasNext();
            }
            private boolean moveToNext(){
                counterArray++;
                while (counterArray < hashTable.length && hashTable[counterArray] == null){
                    counterArray++;
                }
                return counterArray < hashTable.length && hashTable[counterArray] != null;
            }

            @Override
            public V next() {
                valueCounter++;
                return subIterator.next().getValue();
            }
        };
    }


    private class Node<K, V>{
        private List<Node<K, V>> nodes;
        private int hash;
        private K key;
        private V value;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            nodes = new LinkedList<Node<K,V>>();
        }
        private int hash(){
           return  hashCode() % hashTable.length;
        }

        public K getKey() {
            return key;
        }

        public List<Node<K, V>> getNodes() {
            return nodes;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(hash, node.hashCode()) && Objects.equals(key, node.key) && Objects.equals(value, node.value);
        }
        @Override
        public int hashCode() {
            hash = 31;
            hash = hash * 17 + key.hashCode();
            return hash;
        }
    }
}
