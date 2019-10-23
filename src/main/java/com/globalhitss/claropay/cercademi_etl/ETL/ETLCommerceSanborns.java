package com.globalhitss.claropay.cercademi_etl.ETL;

import com.globalhitss.claropay.cercademi_etl.Models.Sanborns;
import com.globalhitss.claropay.cercademi_etl.Models.Commerce;

import java.util.LinkedList;
import java.sql.ResultSet;


/** */
public class ETLCommerceSanborns extends ETLCommerce
{
  
  /** */
  @Override
  public LinkedList<Commerce> extract() 
    throws ETLExtractException
  { 
    LinkedList sanbornsList = new LinkedList<Sanborns>();
    ResultSet  sanbornsRows = null;
    
    try {
      source.startConnection();

      sanbornsRows = source.get(
        "no_sucursal, calle_y_numero, colonia, cp, localidad_municipio, estado",
        "cat_sanborns"
      );

      while (sanbornsRows.next()) {
        sanbornsList.add( new Sanborns(
          sanbornsRows.getString("no_sucursal"),
          sanbornsRows.getString("calle_y_numero"),
          sanbornsRows.getString("colonia"),
          sanbornsRows.getString("cp"),
          sanbornsRows.getString("localidad_municipio"),
          sanbornsRows.getString("estado")
        ) );
      }

      source.closeConnection();
    }
    catch (Exception e0) { throw new ETLExtractException("", e0); }

    return sanbornsList;
  }
}