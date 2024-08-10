package com.studentservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollectionExample {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("keyuri");
		list.add("yashvi");
		list.add("jainil");
		System.out.println(list);
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("no", "1");
		map.put("name", "keyuri");
		System.out.println(map);
		
		
	}
}
