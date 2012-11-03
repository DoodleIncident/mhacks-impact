package com.mhacks.xmldata;

public class MealDay 
{
	enum Month {JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER};
	enum Day {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY};
	
	MealHall[] diningHalls;
	Month currentMonth;
	Day currentDay;
	int date;
	int year;
}
