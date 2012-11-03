package com.mhacks.xmldata;

import java.util.ArrayList;

public class Station {
	public String name;
	public ArrayList<Course> courses;
	
	public Station(String name, ArrayList<Course> courses) {
		this.name = name;
		this.courses = courses;
	}
}
