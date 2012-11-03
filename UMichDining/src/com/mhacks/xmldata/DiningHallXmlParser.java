package com.mhacks.xmldata;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;


public class DiningHallXmlParser {
	// We don't use namespaces
    private static final String ns = null;
	private ArrayList<Menu> menus;
	private ArrayList<Meal> meals;
	private ArrayList<Station> stations;
	private ArrayList<Course> courses;
	private ArrayList<MenuItem> items;
	private String iron;
	private String calcium;
	private String vitaminC;
	private String vitaminA;
	private String protein;
	private String sugars;
	private String dietaryFiber;
	private String totalCarbohydrate;
	private String sodium;
	private String cholesterol;
	private Nutrition nutrition;
	private String calories;
	private String caloriesFromFat;
	private String totalFat;
	private String saturatedFat;
	private String transFat;
	private String portion;
	private String serving;
	private String portionTitle;
	private String servingTitle;
	private String hallName;
	private String hours;
	private String address;
	private String contacts;
	private String name;
	private String mealName;
	private String stationName;
	private String courseName;
	private XmlPullParser parser;
   
    public DiningHall parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readHall(parser);
        } finally {
            in.close();
        }
    }
    
    private DiningHall readHall(XmlPullParser parser) throws XmlPullParserException, IOException {
        menus = new ArrayList<Menu>();
        hallName = null;
        hours = null;
        address = null;
        contacts = null;

        parser.require(XmlPullParser.START_TAG, ns, "dininghall");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            name = parser.getName();
            if (name.equals("name")) {
                hallName = readHallName(parser);
            } else if (name.equals("menu")) {
                menus.add(readMenu(parser));
            } else if (name.equals("hours")) {
            	hours = readHours(parser);
            } else if (name.equals("address")) {
            	address = readAddress(parser);
            } else if (name.equals("contacts")) {
            	contacts = readAddress(parser);
            } else {
                skip(parser);
            }
        }  
        return new DiningHall(hallName, hours, address, contacts, menus);
    }
    
    private String readHallName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "name");
        hallName = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return hallName;
    }
    
    private String readHours(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "hours");
        hours = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "hours");
        return hours;
    }
    
    private String readAddress(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "address");
        address = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "address");
        return address;
    }
    
    private String readContacts(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "contacts");
        contacts = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "contacts");
        return contacts;
    }
    
    private Menu readMenu(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "menu");
        meals = new ArrayList<Meal>();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            name = parser.getName();
            if (name.equals("meal")) {
                meals.add(readMeal(parser));
            } else {
                skip(parser);
            }
        }
        return new Menu(meals);
    }
    
    private Meal readMeal(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "meal");
        mealName = null;
        stations = new ArrayList<Station>();
        
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            name = parser.getName();
            if (name.equals("name")) {
                mealName = readMealName(parser);
            } else if (name.equals("station")) {
            	stations.add(readStation(parser));
            } else {
                skip(parser);
            }
        }
        return new Meal(mealName, stations);
	}
    
    private String readMealName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "name");
        mealName = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return mealName;
    }
    
    private Station readStation(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "station");
        stationName = null;
        courses = new ArrayList<Course>();
        
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("name")) {
                stationName = readStationName(parser);
            } else if (name.equals("course")) {
            	courses.add(readCourse(parser));
            } else {
                skip(parser);
            }
        }
        
        return new Station(stationName, courses);
    }
    
    private String readStationName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "name");
        name = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return name;
    }
    
    private Course readCourse(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "course");
        courseName = null;
        items = new ArrayList<MenuItem>();
        
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("name")) {
                courseName = readCourseName(parser);
            } else if (name.equals("menuitem")) {
            	items.add(readMenuItem(parser));
            } else {
                skip(parser);
            }
        }
        
        return new Course(courseName, items);
    }
    
    private String readCourseName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "name");
        String name = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return name;
    }
    
    private MenuItem readMenuItem(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "menuitem");
        serving = null;
        portion = null;
        nutrition = null;
        String itemName = readText(parser);
        boolean vegan = parser.getAttributeValue(null, "vegan") == "1";
        boolean vegetarian = parser.getAttributeValue(null, "vegetarian") == "1";
        boolean msmart = parser.getAttributeValue(null, "msmart") == "1";
        boolean glutenfree = parser.getAttributeValue(null, "glutenfree") == "1";;
        
        String name = parser.getName();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            if (name.equals("serving_size")) {
                serving = readServingSize(parser);
            } else if (name.equals("portion_size")) {
                portion = readPortionSize(parser);
            } else if (name.equals("nutrition")) {
                nutrition = readNutrition(parser);
            } else {
                skip(parser);
            }
        }
        return new MenuItem(itemName, vegan, vegetarian, msmart, glutenfree, serving, portion, nutrition);
    }
    
    private String readServingSize(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "serving_size");
        servingTitle = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "serving_size");
        return servingTitle;
    }
    
    private String readPortionSize(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "portion_size");
        portionTitle = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "portion_size");
        return portionTitle;
    }
    
    private Nutrition readNutrition(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "nutrition");
        serving = null;
        portion = null;
        nutrition = null;
        calories = parser.getAttributeValue(null, "cl");
    	caloriesFromFat = parser.getAttributeValue(null, "fc");
    	totalFat = parser.getAttributeValue(null, "f");
    	saturatedFat = parser.getAttributeValue(null, "fs");
    	transFat = parser.getAttributeValue(null, "ft");
    	cholesterol = parser.getAttributeValue(null, "ch");
    	sodium = parser.getAttributeValue(null, "so");
    	totalCarbohydrate = parser.getAttributeValue(null, "cr");
    	dietaryFiber = parser.getAttributeValue(null, "fi");
    	sugars = parser.getAttributeValue(null, "sugar");
    	protein = parser.getAttributeValue(null, "p");
    	vitaminA = parser.getAttributeValue(null, "vita");
    	vitaminC = parser.getAttributeValue(null, "vitc");
    	calcium = parser.getAttributeValue(null, "12");
    	iron = parser.getAttributeValue(null, "fe");
    	
        return new Nutrition(calories,
    	caloriesFromFat,
    	totalFat,
    	saturatedFat,
    	transFat,
    	cholesterol,
    	sodium,
    	totalCarbohydrate,
    	dietaryFiber,
    	sugars,
    	protein,
    	vitaminA,
    	vitaminC,
    	calcium,
    	iron);
    }
    
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
    
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
            case XmlPullParser.END_TAG:
                depth--;
                break;
            case XmlPullParser.START_TAG:
                depth++;
                break;
            }
        }
     }
}
