package com.example.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;


public class MapApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapApplication.class, args);
		MyHashMapImpl<String, String> map = new MyHashMapImpl<>();
		map.put("A", "B");

		/**map.put("A", "C");
		System.out.println(map.get("A"));
		System.out.println(map.size());
		map.remove("A");
		System.out.println(map.get("A"));
		map.put("A", "C");
		System.out.println(map.get("A"));
		map.put("DA", "dsad");
		System.out.println(map.get("DA"));
		System.out.println(map.size());
		System.out.println(map.get("A"));
		map.put("1", "1");
		System.out.println(map.get("1"));
		map.put("2", "2");
		System.out.println(map.get("2"));
		map.put("3", "3");
		System.out.println(map.get("3"));
		map.put("4", "4");
		System.out.println(map.get("4"));
		map.put("5", "5");
		 System.out.println(map.get("5"));
		**/
		map.put("1", "1");
		map.put("2", "2");
		map.put("5", "5");
		map.put("6", "6");
		map.put("7", "7");
		for (String s : map){
			System.out.println(s);
		}


	}

}
