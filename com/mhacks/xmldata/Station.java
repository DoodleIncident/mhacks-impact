package com.mhacks.xmldata;

import java.util.ArrayList;

public class Station {
	String name;
	ArrayList<Course> courses;
	
	public Station(String name, ArrayList<Course> courses) {
		this.name = name;
		this.courses = courses;
	}
}
