package com.mhacks.xmldata;

public class Nutrition {
	String calories;
	String caloriesFromFat;
	String totalFat;
	String saturatedFat;
	String transFat;
	String cholesterol;
	String sodium;
	String totalCarbohydrate;
	String dietaryFiber;
	String sugars;
	String protein;
	String vitaminA;
	String vitaminC;
	String calcium;
	String iron;
	
	public Nutrition(String calories, String caloriesFromFat, String totalFat,
	                  String saturatedFat, String transFat, String cholesterol,
	                  String sodium, String totalCarbohydrate, String dietaryFiber,
	                  String sugars, String protein, String vitaminA, String vitaminC,
	                  String calcium, String iron) {
		this.calories=calories;
		this.caloriesFromFat=caloriesFromFat;
		this.totalFat=totalFat;
		this.saturatedFat=saturatedFat;
		this.transFat=transFat;
		this.cholesterol=cholesterol;
		this.sodium=sodium;
		this.totalCarbohydrate=totalCarbohydrate;
		this.dietaryFiber=dietaryFiber;
		this.sugars=sugars;
		this.protein=protein;
		this.vitaminA=vitaminA;
		this.vitaminC=vitaminC;
		this.calcium=calcium;
		this.iron=iron;
	}
}
