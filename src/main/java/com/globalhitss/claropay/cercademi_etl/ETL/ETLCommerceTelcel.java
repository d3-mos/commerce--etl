package com.globalhitss.claropay.cercademi_etl.ETL;

import com.globalhitss.claropay.cercademi_etl.Models.Telcel;
import com.globalhitss.claropay.cercademi_etl.Models.Commerce;

import java.util.LinkedList;
import java.sql.ResultSet;

public class ETLCommerceTelcel extends ETLCommerce
{
  /** */
  @Override
  public LinkedList<Commerce> extract() 
    throws ETLExtractException
  {
    LinkedList telcelList = new LinkedList<Telcel>();
    ResultSet  telcelRows = null;
    
    try {
      source.startConnection();

      telcelRows = source.get(
        "id_corresponsal, estado, ciudad, delegacion,"
       +"colonia, calle, numero, cp",
        "cat_telcel"
      );

      while (telcelRows.next()) {
        telcelList.add( new Telcel(
          telcelRows.getString("id_corresponsal"),
          telcelRows.getString("estado"),
          telcelRows.getString("ciudad"),
          telcelRows.getString("delegacion"),
          telcelRows.getString("colonia"),
          telcelRows.getString("calle"),
          telcelRows.getString("numero"),
          telcelRows.getString("cp")
        ) );
      }

      source.closeConnection();
    }
    catch (Exception e0) { throw new ETLExtractException("", e0); }

    return telcelList;
  }    
}