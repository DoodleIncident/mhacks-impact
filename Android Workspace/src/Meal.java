
public class Meal 
{
	String name;
	
	String servingSize;
	int portionSize; //size in grams

	String[] allergens;
	
	//nutrition values
	int calories;
	int caloriesFromFat;
	int totalFat;
	int saturatedFat;
	int transFat;
	int cholesterol;
	int sodium;
	int totalCarbohydrate;
	int dietaryFiber;
	int sugars;
	int protein;
	int vitaminA;
	int vitaminC;
	int calcium;
	int iron;
	
	//boolean flags
	boolean vegan;
	boolean vegetarian;
	boolean msmart;
	boolean glutenfree;
	
	// Crap code to test git
	private Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "entry");
	    String title = null;
	    String summary = null;
	    String link = null;
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("title")) {
	            title = readTitle(parser);
	        } else if (name.equals("summary")) {
	            summary = readSummary(parser);
	        } else if (name.equals("link")) {
	            link = readLink(parser);
	        } else {
	            skip(parser);
	        }
	    }
	    return new Entry(title, summary, link);
	}

}
