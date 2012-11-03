package com.mhacks.xmldata;
import org.xmlpull.v1.XmlPullParser;


public class MenuItem 
{
	String name;
	
	String servingSize;
	int portionSize; //size in grams
	
	//boolean flags
	boolean vegan;
	boolean vegetarian;
	boolean msmart;
	boolean glutenfree;
	
	public MenuItem(String name, boolean vegan, boolean vegetarian, boolean msmart, boolean glutenfree, String serving, String portion, Nutrition nutrition) {
		this.name = name;
	}
	

}
