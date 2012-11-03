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
import org.xmlpull.v1.XmlPullParserException;

import com.mhacks.xmldata.DiningHall;
import com.mhacks.xmldata.DiningHallXmlParser;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class GetXml extends AsyncTask {
	private HttpEntity entity;
	
	public Context context;
	
	public enum halls { barbour, east, north, south, west, markley, marketplace, twigs, bursley }
	
	

	@Override
	protected HttpResponse doInBackground(Object... argv) {		
		//this.context = (Context) argv[1];
		
		HttpClient httpClient = new DefaultHttpClient();
    	HttpGet httpget = new HttpGet((String) argv[0]);
    	HttpResponse response = null;
    	try {
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
	    	if (entity != null) {
	    			Log.i("GET RESPONSE", "Got something!");
	    			Log.i("xml_data", ((DiningHall) new DiningHallXmlParser().parse(entity.getContent())).name);
	    			
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
    	
		return response;
	}
	
	protected DiningHall onPostExecute(HttpResponse response) {
		entity = response.getEntity();
		try {
			Log.i("xml_data", ((DiningHall) new DiningHallXmlParser().parse(entity.getContent())).name);
			Toast toast = Toast.makeText(context, ((DiningHall) new DiningHallXmlParser().parse(entity.getContent())).name, Toast.LENGTH_SHORT);
			toast.show();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			Toast.makeText(context, ((DiningHall) new DiningHallXmlParser().parse(entity.getContent())).name, Toast.LENGTH_SHORT).show();
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (XmlPullParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;
		
	}
}
