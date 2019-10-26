package com.globalhitss.claropay.cercademi_etl.webservices;

import com.globalhitss.claropay.cercademi_etl.AppServices.AppProperties;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import java.util.concurrent.CompletableFuture;

/**
 * Check the documentation in:
 *   https://github.com/googlemaps/google-maps-services-java
 *   https://developers.google.com/maps/documentation/geocoding/intro 
 */
public class GoogleGeoCoding
{
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