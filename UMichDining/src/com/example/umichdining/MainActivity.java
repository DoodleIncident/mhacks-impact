package com.example.umichdining;

import xml_parser.GetXml;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        String menuXml = "http://housing.umich.edu/files/helper_files/js/menu2xml.php?location=";
		
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
			
			Toast toast = Toast.makeText(getApplicationContext(), website, Toast.LENGTH_SHORT);
			toast.show();
	        
	        GetXml files = new GetXml();
	        files.context = getApplicationContext();
	        TextView hello = (TextView) findViewById(R.id.hello_textbox);
	        files.helloText = hello;
	        files.execute(website);
		}
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}


