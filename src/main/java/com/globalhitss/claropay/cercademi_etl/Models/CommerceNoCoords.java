package com.globalhitss.claropay.cercademi_etl.Models;

import com.globalhitss.claropay.cercademi_etl.WebServices.GoogleGeoCoding;

import java.util.concurrent.CompletableFuture;


/**
 * This class implements the Commerce interface to commerce without coordinates.
 * It retrieves the coordinates from address retrieve method and using the Google
 * Geocode service.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 21th, 2019
 * @see     Commerce
 * @see     com.globalhitss.claropay.cercademi_etl.webservices.GoogleGeoCoding
 */
public abstract class CommerceNoCoords implements Commerce
{
  private double lat = 0.0;
  private double lng = 0.0;
  private CompletableFuture<Void> promiseCoords = null;

  /**
   * This method run a Geocoding request, when the request is complete set latitud
   * and longitude of the current commerce.
   */
  public void loadCoords()
  {
    promiseCoords = new GoogleGeoCoding()
    .getCoordinates(getAddress())
    .thenAccept( coords -> {
      lat = coords.lat;
      lng = coords.lng;
    });
  }

  /**
   * {@inheritDoc}
   */
  public double getLatitude()  { return lat; }
  
  /**
   * {@inheritDoc}
   */
  public double getLongitude() { return lng; }

  /**
   * This method is use to give an external interface to wait until geocoding request
   * made by {@link #loadCoords()} will be complete.
   */
  public void waitingByCoords() { 
    if (promiseCoords!=null) promiseCoords.join();
  }
}