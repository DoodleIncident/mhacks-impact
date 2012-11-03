package com.mhacks.xmldata;

import java.util.ArrayList;

public class Course {
	String name;
	ArrayList<MenuItem> items;
	
	public Course(String name, ArrayList<MenuItem> items) {
		this.name = name;
		this.items = items;
	}
}
