package com.mhacks.xmldata;

import java.util.ArrayList;

public class Course {
	public String name;
	public ArrayList<MenuItem> items;
	
	public Course(String name, ArrayList<MenuItem> items) {
		this.name = name;
		this.items = items;
	}
}
