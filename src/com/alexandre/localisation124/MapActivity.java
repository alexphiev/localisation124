package com.alexandre.localisation124;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.Context;

public class MapActivity extends Activity {

	private GoogleMap googlemap;
	private float V_latitude = 0;
	private float V_longitude = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		creationMap();
		addMarkerToMap();
		
		// Accès au service de localization
	    LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
	  
	    LocationListener listener = new LocationListener(){

			@Override
			public void onLocationChanged(Location location) {		// Appelée à chaque changement
					
					V_latitude = (float) location.getLatitude();
					V_longitude = (float) location.getLongitude();
					if ((V_latitude !=0) && (V_longitude!=0)){	
					// N'affiche pas de cursuer si on ne reçoit pas de données.
					addMarkerToMap();}
			}

			@Override
			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub
				
			}
				
	    };
	    manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10,listener);
	}

	private void addMarkerToMap() {
		// Partie geocoder pour récupérer et afficher une adresse grâce aux coord.
		Geocoder geocoder = new Geocoder(this, Locale.getDefault());
		String resultat=null;
		 try {
             List<Address> list = geocoder.getFromLocation(
                     V_latitude, V_longitude, 1);
             if (list != null && list.size() > 0) {
                 Address address = list.get(0);
                 resultat = address.getAddressLine(0) + ", " + address.getLocality() + ", " + address.getCountryName();
             }
         } catch (IOException e) {
         }
		
		LatLng position = new LatLng(V_latitude,V_longitude);
		googlemap.clear();
		googlemap.addMarker(new MarkerOptions()
					.title("Tu es ici")				//Titre du marker
					.position(position)				//position du marker
					.snippet(resultat)				// Text complémentaire au marker
					);
	}

	private void creationMap() {
		googlemap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		googlemap.setMyLocationEnabled(true);		//affichage du point bleu de précision
	}

}

