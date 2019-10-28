package com.globalhitss.claropay.cercademi_etl.ETL;

import com.globalhitss.claropay.cercademi_etl.Models.Sanborns;
import com.globalhitss.claropay.cercademi_etl.Models.Commerce;
import com.globalhitss.claropay.cercademi_etl.Models.CommerceNoCoords;

import java.util.LinkedList;
import java.sql.ResultSet;


/**
 * This custom ETL, implements the extract procedure to fetch all data from
 * cat_sanborns table in origin database.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 23th, 2019.
 * @see     com.globalhitss.claropay.cercademi_etl.Models.Commerce
 * @see     com.globalhitss.claropay.cercademi_etl.Models.CommerceNoCoords
 * @see     com.globalhitss.claropay.cercademi_etl.Models.Sanborns
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

      ResultSet sanbornsRows = source.get(
        "no_sucursal, calle_y_numero, colonia, cp, localidad_municipio, estado",
        "cat_sanborns"
      );

      while (sanbornsRows.next()) {
        sanbornsList.add(new Sanborns(
          sanbornsRows.getString("no_sucursal"),
          sanbornsRows.getString("calle_y_numero"),
          sanbornsRows.getString("colonia"),
          sanbornsRows.getString("cp"),
          sanbornsRows.getString("localidad_municipio"),
          sanbornsRows.getString("estado")
        ));
      }

      sanbornsList.forEach(obj -> ((CommerceNoCoords) obj).waitingByCoords());
      
      source.closeConnection();
    }
    catch (Exception e0) { throw new ETLExtractException("", e0); }

    return sanbornsList;
  }
}