package com.mhacks.umichdining;

import com.example.umichdining.R;
import com.mhacks.xmldata.DiningHall;

import xml_parser.GetXml;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView hallEntry;
	private GetXml files;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main);
 
        files = new GetXml(getApplicationContext(), (LinearLayout) findViewById(R.id.hall_list));
        files.execute();
		
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}


