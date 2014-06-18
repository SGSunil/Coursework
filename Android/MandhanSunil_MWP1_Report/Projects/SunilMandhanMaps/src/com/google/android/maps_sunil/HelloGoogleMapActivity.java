package com.google.android.maps_sunil;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.MapView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class HelloGoogleMapActivity extends MapActivity  {
	Button mapButton;
	ToggleButton toggleButton;
	List<Overlay> mapOverlays = null;
	HelloItemizedOverlay itemizedoverlay = null;
	MapView mapView = null;
	EditText location = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	 	super.onCreate(savedInstanceState);
    	    setContentView(R.layout.map);

    	    mapView = (MapView) findViewById(R.id.mapview);
    	    mapView.setBuiltInZoomControls(true);
    	    
    	    mapOverlays = mapView.getOverlays();
    	    Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
    	    itemizedoverlay = new HelloItemizedOverlay(drawable, this);
    	    
    	    
    	    mapButton = (Button)findViewById(R.id.buttonmap1);
    	    toggleButton = (ToggleButton)findViewById(R.id.toggleButtonmap1);
    	    location = (EditText)findViewById(R.id.editTextmap1);
    	    
    	    mapButton.setOnClickListener(new View.OnClickListener() 
    	    {
    	    	public void onClick(View view) {
    	    		 updateStatus();
                }

            });
            
    	    toggleButton.setOnClickListener(new View.OnClickListener() 
    	    {
    	    	public void onClick(View view) {
    	    		handleToggle();
    	    	}

            });
    	    
    	   
    	    GeoPoint point = new GeoPoint(19240000,-99120000);
    	    OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
    	    
    	    GeoPoint point2 = new GeoPoint(35410000, 139460000);
    	    OverlayItem overlayitem2 = new OverlayItem(point2, "Sekai, konichiwa!", "I'm in Japan!");
    	    
    	    itemizedoverlay.addOverlay(overlayitem);
    	    itemizedoverlay.addOverlay(overlayitem2);
    	    mapOverlays.add(itemizedoverlay);
    	    
    	    mapView.invalidate();
    }
    
    private void handleToggle()
    {
    	LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	
    	if(toggleButton.isChecked())
    	{
    		Toast.makeText(HelloGoogleMapActivity.this, "GPS Receiver On", Toast.LENGTH_SHORT).show();
	        locationManager.requestLocationUpdates(
	            LocationManager.GPS_PROVIDER, 
	            0, 
	            0, 
	            locationListener);	
    	}
    	else    		
    	{
    		locationManager.removeUpdates(locationListener );
    		Toast.makeText(HelloGoogleMapActivity.this, "GPS Receiver OFF", Toast.LENGTH_SHORT).show();
    	}
    
    }
    
    private void updateStatus()
    {
   	 	Geocoder geocoder = new Geocoder(HelloGoogleMapActivity.this, Locale.getDefault());
   	 	String location_addr = location.getText().toString();
   	 	List<Address> addresses1 = null;
   	 	if(!location_addr.matches("[a-zA-Z,]*") || location_addr.isEmpty())
   	 	{
   	 		Toast.makeText(HelloGoogleMapActivity.this, "please enter in the format: city,country or city or country without space inbetween", Toast.LENGTH_SHORT).show();
   	 		return;	
   	 	}
   	 	
		try 
		{
			addresses1 = geocoder.getFromLocationName(location_addr, 1);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	 	GeoPoint point3 = new GeoPoint((int)(addresses1.get(0).getLatitude()*1000000), (int)(addresses1.get(0).getLongitude()*1000000));
	 	OverlayItem overlayitem3 = new OverlayItem(point3, "Mandhan, Sunil!", "India!");
	    
	 	itemizedoverlay.addOverlay(overlayitem3);  
	 	
	 	MapController mc = mapView.getController();
	 	mc.animateTo(point3);
	    mapView.invalidate();
    }
    
    private final LocationListener locationListener = new LocationListener()
    { 
        public void onLocationChanged(Location location) {
            if (location != null) {
                GeoPoint point = new GeoPoint(
                        (int) (location.getLatitude() * 1E6), 
                        (int) (location.getLongitude() * 1E6));
                             
                MapController mapController = mapView.getController();
                mapController.animateTo(point);
                mapController.setZoom(5);
                
                OverlayItem overlayitem = new OverlayItem(point, "Sunil Mandhan is saying Hi", "From India!");
                itemizedoverlay.addOverlay(overlayitem);  
    
                mapView.invalidate();
            }
        }
       

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}