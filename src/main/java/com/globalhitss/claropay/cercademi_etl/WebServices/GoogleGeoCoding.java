package com.globalhitss.claropay.cercademi_etl.webservices;

import com.globalhitss.claropay.cercademi_etl.AppServices.AppProperties;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;


/**
 * Check the documentation in:
 *   https://github.com/googlemaps/google-maps-services-java
 *   https://developers.google.com/maps/documentation/geocoding/intro 
 */
public class GoogleGeoCoding
{
    public LatLng getCoordinates(String address)
        throws Exception
    {
      GeoApiContext context = new GeoApiContext.Builder()
          .apiKey(AppProperties.get("keys.google_api_key"))
          .build();
      GeocodingResult[] results =  GeocodingApi
          .geocode(context,address)
          .await();
      
      if (results.length==0) {
        System.out.println(address);
      }

      return results[0].geometry.location;
    }
}