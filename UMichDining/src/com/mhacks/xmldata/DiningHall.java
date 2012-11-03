package com.mhacks.xmldata;

import java.util.ArrayList;

public class DiningHall 
{
	public String name;
	String hours;
	String address;
	String contacts;
	ArrayList<Menu> menus;
	
	public DiningHall(String name, String hours, String address, String contacts, ArrayList<Menu> menus) {
		this.name = name;
		this.hours = hours;
		this.address = address;
		this.contacts = contacts;
		this.menus = menus;
	}
}
