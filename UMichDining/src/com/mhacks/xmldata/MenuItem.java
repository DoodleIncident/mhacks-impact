package com.mhacks.xmldata;
import org.xmlpull.v1.XmlPullParser;


public class MenuItem 
{
	public String name;
	
	public String servingSize;
	public int portionSize; //size in grams
	
	//boolean flags
	public boolean vegan;
	public boolean vegetarian;
	public boolean msmart;
	public boolean glutenfree;
	
	public MenuItem(String name, boolean vegan, boolean vegetarian, boolean msmart, boolean glutenfree, String serving, String portion, Nutrition nutrition) {
		this.name = name;
	}
	

}
