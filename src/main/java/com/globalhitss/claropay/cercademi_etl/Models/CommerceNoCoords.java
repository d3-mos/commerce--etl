package com.globalhitss.claropay.cercademi_etl.Models;

import com.globalhitss.claropay.cercademi_etl.webservices.GoogleGeoCoding;

import com.google.maps.model.LatLng;


/** */
public abstract class CommerceNoCoords implements Commerce
{
  private double lat = 0.0;
  private double lng = 0.0;

  public void loadCoords()
  {
    try {
      LatLng coords = new GoogleGeoCoding().getCoordinates(getAddress());
      lat = coords.lat;
      lng = coords.lng;
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  public double getLatitude()  { return lat; }
  
  public double getLongitude() { return lng; }
}