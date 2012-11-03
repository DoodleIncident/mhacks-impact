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
   
    public DiningHall parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readHall(parser);
        } finally {
            in.close();
        }
    }
    
    private DiningHall readHall(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Menu> menus = new ArrayList<Menu>();
        String hallName = null;
        String hours = null;
        String address = null;
        String contacts = null;

        parser.require(XmlPullParser.START_TAG, ns, "dininghall");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
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
        String hallName = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return hallName;
    }
    
    private String readHours(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "hours");
        String hours = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "hours");
        return hours;
    }
    
    private String readAddress(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "address");
        String address = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "address");
        return address;
    }
    
    private String readContacts(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "contacts");
        String contacts = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "contacts");
        return contacts;
    }
    
    private Menu readMenu(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "menu");
        ArrayList<Meal> meals = new ArrayList<Meal>();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("title")) {
                meals.add(readMeal(parser));
            } else {
                skip(parser);
            }
        }
        return new Menu(meals);
    }
    
    private Meal readMeal(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "meal");
        String mealName = null;
        ArrayList<Station> stations = new ArrayList<Station>();
        
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
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
        String name = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return name;
    }
    
    private Station readStation(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "station");
        String stationName = null;
        ArrayList<Course> courses = new ArrayList<Course>();
        
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
        String name = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return name;
    }
    
    private Course readCourse(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "course");
        String courseName = null;
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        
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
        String serving = null;
        String portion = null;
        Nutrition nutrition = null;
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
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "serving_size");
        return title;
    }
    
    private String readPortionSize(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "portion_size");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "portion_size");
        return title;
    }
    
    private Nutrition readNutrition(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "nutrition");
        String serving = null;
        String portion = null;
        Nutrition nutrition = null;
        String calories = parser.getAttributeValue(null, "cl");
    	String caloriesFromFat = parser.getAttributeValue(null, "fc");
    	String totalFat = parser.getAttributeValue(null, "f");
    	String saturatedFat = parser.getAttributeValue(null, "fs");
    	String transFat = parser.getAttributeValue(null, "ft");
    	String cholesterol = parser.getAttributeValue(null, "ch");
    	String sodium = parser.getAttributeValue(null, "so");
    	String totalCarbohydrate = parser.getAttributeValue(null, "cr");
    	String dietaryFiber = parser.getAttributeValue(null, "fi");
    	String sugars = parser.getAttributeValue(null, "sugar");
    	String protein = parser.getAttributeValue(null, "p");
    	String vitaminA = parser.getAttributeValue(null, "vita");
    	String vitaminC = parser.getAttributeValue(null, "vitc");
    	String calcium = parser.getAttributeValue(null, "12");
    	String iron = parser.getAttributeValue(null, "fe");
    	
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
