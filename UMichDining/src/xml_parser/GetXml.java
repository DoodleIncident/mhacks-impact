package xml_parser;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParserException;

import com.example.umichdining.R;
import com.mhacks.xmldata.DiningHall;
import com.mhacks.xmldata.DiningHallXmlParser;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GetXml extends AsyncTask<Void, Void, ArrayList<DiningHall>> {
	public ArrayList<DiningHall> halls = new ArrayList<DiningHall>();
	
	public enum halls { barbour, east, north, south, west, markley, marketplace, twigs, bursley }

	private Context context;
	private LinearLayout hallList;
	private TextView hallEntry;
	private DefaultHttpClient httpClient;
	private HttpGet httpget;
	private HttpResponse response;
	private HttpEntity entity;
	
	String menuXml = "http://housing.umich.edu/files/helper_files/js/menu2xml.php?location=";
	
	String[] foo;
	String[] bar;
	private DiningHall hall;
	
	
	public GetXml(Context context, LinearLayout hallList) {
		// TODO Auto-generated method stub
		this.context = context;
		this.hallList = hallList;
		this.foo = new String[9];
		this.bar = new String[9];
		foo[0] = "barbour";
		foo[1] = "east-quad";
		foo[2] = "north-quad";
		foo[3] = "south-quad";
		foo[4] = "west-quad";
		foo[5] = "markley";
		foo[6] = "marketplace";
		foo[7] = "twigs-at-oxford";
		foo[8] = "bursley";
		
		bar[0] = "BARBOUR%20DINING%20HALL";
		bar[1] = "EAST%20QUAD%20DINING%20HALL";
		bar[2] = "North%20Quad%20Dining%20Hall";
		bar[3] = "SOUTH%20QUAD%20DINING%20HALL";
		bar[4] = "WEST%20QUAD%20DINING%20HALL";
		bar[5] = "MARKLEY%20DINING%20HALL";
		bar[6] = "MARKETPLACE";
		bar[7] = "Twigs%20at%20Oxford";
		bar[8] = "BURSLEY%20DINING%20HALL";
	}


	protected ArrayList<DiningHall> doInBackground(Void...argv) {
		
		for (int i = 0; i < 9; i++)
		{
			String website = menuXml + bar[i] + "&date=today";
			Log.v("onCreate", website);
	        		
			httpClient = new DefaultHttpClient();
	    	httpget = new HttpGet(website);
	    	response = null;
	    	hall = null;
	    	try {
				response = httpClient.execute(httpget);
				entity = response.getEntity();
		    	if (entity != null) {
		    			Log.i("GET RESPONSE", "Got something!");
		    			hall = ((DiningHall) new DiningHallXmlParser().parse(entity.getContent()));
		    			Log.i("Parse", "Finished parsing");
		    			Log.i("Name", hall.name);
		    			halls.add(hall);
		    	}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return halls;
    	
	}
	
	
	@Override
	protected void onPostExecute(final ArrayList<DiningHall> halls) {
		Log.i("PostExec", "Made it to onPostExecute");
		
		for (DiningHall hall: halls)
		{
			hallEntry = new TextView(context);
			hallEntry.setId(halls.indexOf(hall)+20);
			hallEntry.setText(hall.name);
			hallEntry.setHeight(60);
			hallEntry.setClickable(true);
			hallEntry.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Toast.makeText(context, String.valueOf(halls.get(v.getId() - 20).menus.get(0).availableMeals.size()) + " menus", Toast.LENGTH_SHORT).show();
					}
			});
			hallList.addView(hallEntry);
		}
		
		return;
		
	}
}
