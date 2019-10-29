package com.globalhitss.claropay.cercademi_etl.WebServices;

import com.globalhitss.claropay.cercademi_etl.AppServices.AppProperties;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import java.util.concurrent.CompletableFuture;

/**
 * This class implements an abstraction of Google Maps Client procedures.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 25th, 2019.
 * @see     <a href="https://github.com/googlemaps/google-maps-services-java">Google Maps Java Client Library</a>
 * @see     <a href="https://developers.google.com/maps/documentation/geocoding/intro">Geocoding documentation</a>
 */
public class GoogleGeoCoding
{

  /**
   * This method implements as asynchronous process call to geocoding service.
   * The google geocoding service returns the coordinates of an address.
   *
   * @param  address String of address
   * @return CompletableFuture object with geocoding service request.
   * @see    com.google.maps.GeocodingApi
   * @see    com.google.maps.LatLng
   */
  public CompletableFuture<LatLng> getCoordinates(String address)
  {
    return CompletableFuture.supplyAsync(() -> {
      try {
        GeoApiContext context = new GeoApiContext.Builder()
          .apiKey(AppProperties.get("keys.google_api_key"))
          .build();
        
        GeocodingResult[] results =  GeocodingApi
          .geocode(context,address)
          .await();
        
        return results[0].geometry.location;
      }
      catch (Exception e) { return new LatLng(0,0); }
    });
  }
}