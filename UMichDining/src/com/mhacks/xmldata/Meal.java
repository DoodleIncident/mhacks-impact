package com.mhacks.xmldata;

import java.util.ArrayList;

public class Meal {
	public String name;
	public ArrayList<Station> stations;
	
	public Meal(String name, ArrayList<Station> station) {
		this.name = name;
		this.stations = station;
	}
}
