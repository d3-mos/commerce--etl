package com.globalhitss.claropay.cercademi_etl.Models;

import com.globalhitss.claropay.cercademi_etl.webservices.GoogleGeoCoding;

import com.google.maps.model.LatLng;
import java.util.concurrent.CompletableFuture;


/** */
public abstract class CommerceNoCoords implements Commerce
{
  private double lat = 0.0;
  private double lng = 0.0;
  private CompletableFuture<Void> promiseCoords = null;

  public void loadCoords()
  {
    promiseCoords = new GoogleGeoCoding()
    .getCoordinates(getAddress())
    .thenAccept( coords -> {
      lat = coords.lat;
      lng = coords.lng;
    });
  }

  public double getLatitude()  { return lat; }
  
  public double getLongitude() { return lng; }

  public void waitingByCoords() { promiseCoords.join(); }
}