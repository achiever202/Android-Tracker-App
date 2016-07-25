package adarsh.awesomeapps.androidtracker;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.internal.lo;
import com.google.android.gms.location.LocationListener;

public class LocationTracker implements android.location.LocationListener
{
	Context context;
	boolean isGPSEnabled;
	boolean isNetworkEnabled;
	Location location;
	LocationManager locationManager;
	
	public LocationTracker(Context ctx)
	{
		context = ctx;
		isGPSEnabled = false;
		isNetworkEnabled = false;
	}
	/*
	Method: Fetches the location of the device.
	Checks whether the GPS and network is enabled.
	Tries to get the location from the network provider first. 
	If it is not feasible then go for GPS
	*/
	public Location getLocation()
	{
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		
		isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		
		if(!isGPSEnabled && !isNetworkEnabled)
		{
			Toast.makeText(context, "No network found.", Toast.LENGTH_SHORT).show();
			return null;
		}
		// Calls the Google Api for getting the location from
		// network provider
		if(isNetworkEnabled)
		{
			locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    (long)1000*60,
                    (float)10, this);
			location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		
		// Calls the Google Api for getting the location from the GPS
		else
		{
			locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    (long)1000*60,
                    (float)10, this);
			location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		}
		
		return location;
	}
	
	@Override
    public void onLocationChanged(Location location)
	{
		
    }
	
	@Override
	public void onProviderEnabled(String s)
	{
		
	}
	
	@Override
	public void onProviderDisabled(String s)
	{
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
