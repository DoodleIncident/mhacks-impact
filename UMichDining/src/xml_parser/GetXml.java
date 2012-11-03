package xml_parser;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class GetXml extends AsyncTask {
	private HttpResponse response;
	
	public enum halls { barbour, east, north, south, west, markley, marketplace, twigs, bursley }
	
	
	protected void onPostExecute(HttpResponse response) {
		// TODO: something useful
	}

	@Override
	protected HttpResponse doInBackground(Object... params) {
		String menuXml = "http://housing.umich.edu/files/helper_files/js/menu2xml.php?html=webmenu&location=";
		
		String[] foo = new String[9];
		String[] bar = new String[9];
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
				
		for (int i = 0; i < 9; i++)
		{
			String website = menuXml + bar[i] + "&date=today";
			Log.v("onCreate", website);
			HttpClient httpClient = new DefaultHttpClient();
	    	HttpGet httpget = new HttpGet(website);
	    	HttpResponse response;
	    	try {
				response = httpClient.execute(httpget);
				HttpEntity entity = response.getEntity();
		    	if (entity != null) {
		    			Log.i("GET RESPONSE", "Got something!");
		    	}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
//	    	try {
//				httpget.setURI(new URI(website));
//			} catch (URISyntaxException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//	    	try {
//				response = httpClient.execute(httpget);
//			} catch (ClientProtocolException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	    	
		}
		return response;
	}
}
