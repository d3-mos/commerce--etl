package com.globalhitss.claropay.cercademi.job.storegeolocation.etl;

import java.util.LinkedList;

import com.globalhitss.claropay.cercademi.job.storegeolocation.model.Commerce;
import com.globalhitss.claropay.cercademi.job.storegeolocation.model.CommerceNoCoords;
import com.globalhitss.claropay.cercademi.job.storegeolocation.model.Sanborns;

import java.sql.ResultSet;


/**
 * This custom ETL, implements the extract procedure to fetch all data from
 * cat_sanborns table in origin database.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 23th, 2019.
 * @see     com.globalhitss.claropay.cercademi.job.storegeolocation.model.Commerce
 * @see     com.globalhitss.claropay.cercademi.job.storegeolocation.model.CommerceNoCoords
 * @see     com.globalhitss.claropay.cercademi.job.storegeolocation.model.Sanborns
 * @see     ETL
 * @see     ETLCommerce
 */
public class ETLCommerceSanborns extends ETLCommerce
{
  
  /**
   * {@inheritDoc}
   * 
   * It's the custom extract procedure implementation to Sanborns objects. If
   * it's require, the procedure wait all requests of coordinates of each
   * commerce.
   */
  @Override
  public LinkedList<Commerce> extract() 
    throws ETLExtractException
  { 
    LinkedList<Commerce> sanbornsList = new LinkedList<Commerce>();
    
    try {
      source.startConnection();
      destination.startConnection();

      ResultSet sanbornsRows = source.getUpdatedRows(
        "no_sucursal, calle_y_numero, colonia, cp, localidad_municipio, estado",
        "cat_sanborns",
        "last_change_ts"
      );
      ResultSet brandToken = destination.get(
        "STORE_ID",
        "CAT_STORE",
        "STORE='Sears'"
      );
      brandToken.next();

      while (sanbornsRows.next()) {
        sanbornsList.add(new Sanborns(
          sanbornsRows.getString("no_sucursal"),
          sanbornsRows.getString("calle_y_numero"),
          sanbornsRows.getString("colonia"),
          sanbornsRows.getString("cp"),
          sanbornsRows.getString("localidad_municipio"),
          sanbornsRows.getString("estado"),
          brandToken.getInt("STORE_ID")
        ));
      }

      sanbornsList.forEach(obj -> ((CommerceNoCoords) obj).waitingByCoords());
      
      source.closeConnection();
      destination.closeConnection();
    }
    catch (Exception e0) { throw new ETLExtractException("", e0); }

    return sanbornsList;
  }
}