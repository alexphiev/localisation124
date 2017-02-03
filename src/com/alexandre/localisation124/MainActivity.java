package com.alexandre.localisation124;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button bouton = null;
	Button boutonMap = null;
	TextView altitude = null;
	TextView latitude = null;
	TextView longitude = null;
	CheckBox temps_reel = null;
	
	private float V_altitude = 0;
	private float V_latitude = 0;
	private float V_longitude = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Association des view à un nom pour manipulations java
	    boutonMap = (Button)findViewById(R.id.button2);
	    bouton = (Button)findViewById(R.id.button1);
	    altitude = (TextView)findViewById(R.id.textView4);
	    latitude = (TextView)findViewById(R.id.textView5);
	    longitude = (TextView)findViewById(R.id.textView6);
	    temps_reel = (CheckBox)findViewById(R.id.checkBox1);
	    //Mise en place de listeners
	    bouton.setOnClickListener(l_bouton);
		boutonMap.setOnClickListener(l_boutonMap);
        
		// Accès au service de localization
	    LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
	  
	    LocationListener listener = new LocationListener(){

			@Override
			public void onLocationChanged(Location location) {		// Appelée à chaque changement
					
				if (temps_reel.isChecked()){			// Actualisation constante des coordonnées
					  
					V_altitude = (float) location.getAltitude();
					V_latitude = (float) location.getLatitude();
					V_longitude = (float) location.getLongitude();
					
					altitude.setText(String.valueOf(V_altitude));
					latitude.setText(String.valueOf(V_latitude));
					longitude.setText(String.valueOf(V_longitude));}
				
				else{									// Attente d'appuie sur bouton pour actualisation
					
					V_altitude = (float) location.getAltitude();
					V_latitude = (float) location.getLatitude();
					V_longitude = (float) location.getLongitude();}
				
			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStatusChanged(String provider, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub
				
			}
	    	
	    };
	    manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0,listener);
	}


	 private OnClickListener l_boutonMap = new OnClickListener() {
	  	  @Override
	  	  public void onClick(View v){
	  		 
	  		  Intent intent = new Intent(MainActivity.this,MapActivity.class);
	  		  startActivity(intent);
	  	  }
	    
	    };
	
	  private OnClickListener l_bouton = new OnClickListener() {
	    	  @Override
	    	  public void onClick(View v){
	    		 
	    			altitude.setText(String.valueOf(V_altitude));
	    			latitude.setText(String.valueOf(V_latitude));
	    			longitude.setText(String.valueOf(V_longitude));
	    			
	    			
	    	  }
	      
	      };

}
