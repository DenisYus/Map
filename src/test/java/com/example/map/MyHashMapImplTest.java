package com.example.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapImplTest {

    @Test
    void put() {
        //given
        MyHashMap<String, Integer> map = new MyHashMapImpl<>();
        //when
        assertTrue(map.put("One",1));
        assertTrue(map.put("Two",2));
        assertTrue(map.put("Three",3));
        //then

        assertEquals(1, map.get("One"));
        assertEquals(2, map.get("Two"));
        assertEquals(3, map.get("Three"));
    }
    @Test
    void putDuplicateKey() {
        //given
        MyHashMap<String, Integer> map = new MyHashMapImpl<>();
        //when
        map.put("One", 1);
        map.put("One", 10);
        //then
        assertNotEquals(1, map.get("One"));
        assertEquals(10, map.get("One"));
    }

    @Test
    void getNull() {
        //given
        MyHashMap<String, Integer> map = new MyHashMapImpl<>();
        //then
        assertEquals(null , map.get("One"));
    }

    @Test
    void get() {
        //given
        MyHashMap<String, Integer> map = new MyHashMapImpl<>();
        //when
        map.put("One", 1);
        //then
        assertEquals(1, map.get("One"));
    }

    @Test
    void remove() {
        //given
        MyHashMap<String, Integer> map = new MyHashMapImpl<>();
        //when
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 2);
        //then
        assertTrue(map.remove("One"));
        assertNull(map.get("One"));
        assertEquals(2, map.size());
    }
    @Test
    void removeNullKey(){
        //given
        MyHashMap<String, Integer> map = new MyHashMapImpl<>();
        //when
        map.put("One", 1);
        //
        assertFalse(map.remove("Two"));
    }



    @Test
    void sizeNull() {
        //given
        MyHashMap<String, Integer> map = new MyHashMapImpl<>();
        //then
        assertEquals(0, map.size());
    }
    @Test
    void sizePut() {
        //given
        MyHashMap<String, Integer> map = new MyHashMapImpl<>();
        //when
        map.put("One", 1);
        map.put("Two", 2);
        //then
        assertEquals(2, map.size());
    }
    @Test
    void sizeRemove() {
        //given
        MyHashMap<String, Integer> map = new MyHashMapImpl<>();
        //when
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.remove("Two");
        //then
        assertEquals(2, map.size());
    }
}