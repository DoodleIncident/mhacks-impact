package com.mhacks.xmldata;

public class Nutrition {
	public String calories;
	public String caloriesFromFat;
	public String totalFat;
	public String saturatedFat;
	public String transFat;
	public String cholesterol;
	public String sodium;
	public String totalCarbohydrate;
	public String dietaryFiber;
	public String sugars;
	public String protein;
	public String vitaminA;
	public String vitaminC;
	public String calcium;
	public String iron;
	
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
