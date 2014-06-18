package com.android.demo.main_sunil;

import com.google.android.maps.GeoPoint;

import android.os.Parcel;
import android.os.Parcelable;


//Class to return geocode from map activity class
public class GeopPointPacket implements Parcelable {

	private GeoPoint geoPoint;

	public GeopPointPacket(GeoPoint point) {
		geoPoint = point;
	}

	public GeoPoint getGeoPoint() {
		return geoPoint;
	}

	public int describeContents() {
		return 0;
	}


	public static final Parcelable.Creator<GeopPointPacket> CREATOR
	= new Parcelable.Creator<GeopPointPacket>() 
	{
		public GeopPointPacket createFromParcel(Parcel in) {
			return new GeopPointPacket(in);
		}

		public GeopPointPacket[] newArray(int size) 
		{
			return new GeopPointPacket[size];
		}
	};

	private GeopPointPacket(Parcel in) {
		int lat = in.readInt();
		int lon = in.readInt();
		geoPoint = new GeoPoint(lat, lon);
	}

	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

	}


}
