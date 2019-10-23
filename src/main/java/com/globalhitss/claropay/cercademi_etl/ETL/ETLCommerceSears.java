package com.globalhitss.claropay.cercademi_etl.ETL;

import com.globalhitss.claropay.cercademi_etl.Models.Sears;
import com.globalhitss.claropay.cercademi_etl.Models.Commerce;

import java.util.LinkedList;
import java.sql.ResultSet;


/** */
public class ETLCommerceSears extends ETLCommerce
{
  
  /** */
  @Override
  public LinkedList<Commerce> extract() 
    throws ETLExtractException
  {
    LinkedList searsList = new LinkedList<Sears>();
    ResultSet  searsRows = null;
    
    try {
      source.startConnection();

      searsRows = source.get(
        "no_tienda, direccion",
        "cat_sears"
      );

      while (searsRows.next()) {
        searsList.add( new Sears(
          searsRows.getString("no_tienda"),
          searsRows.getString("direccion")
        ) );
      }

      source.closeConnection();
    }
    catch (Exception e0) { throw new ETLExtractException("", e0); }

    return searsList;
  }
}