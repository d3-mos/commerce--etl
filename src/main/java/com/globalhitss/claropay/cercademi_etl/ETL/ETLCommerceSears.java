package com.globalhitss.claropay.cercademi_etl.ETL;

import com.globalhitss.claropay.cercademi_etl.Models.Sears;
import com.globalhitss.claropay.cercademi_etl.Models.Commerce;
import com.globalhitss.claropay.cercademi_etl.Models.CommerceNoCoords;

import java.util.LinkedList;
import java.sql.ResultSet;


/**
 * This custom ETL, implements the extract procedure to fetch all data from
 * cat_sears table in origin database.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 23th, 2019.
 * @see     com.globalhitss.claropay.cercademi_etl.Models.Commerce
 * @see     com.globalhitss.claropay.cercademi_etl.Models.CommerceNoCoords
 * @see     com.globalhitss.claropay.cercademi_etl.Models.Sears
 * @see     ETL
 * @see     ETLCommerce
 */
public class ETLCommerceSears extends ETLCommerce
{
  
  /**
   * {@inheritDoc}
   * 
   * It's the custom extract procedure implementation to Sears objects. If
   * it's require, the procedure wait all requests of coordinates of each
   * commerce.
   */
  @Override
  public LinkedList<Commerce> extract() 
    throws ETLExtractException
  {
    LinkedList<Commerce> searsList = new LinkedList<Commerce>();
    
    try {
      source.startConnection();

      ResultSet searsRows = source.get(
        "no_tienda, direccion",
        "cat_sears"
      );

      while (searsRows.next()) {
        searsList.add( new Sears(
          searsRows.getString("no_tienda"),
          searsRows.getString("direccion")
        ) );
      }

      searsList.forEach(obj -> ((CommerceNoCoords) obj).waitingByCoords());

      source.closeConnection();
    }
    catch (Exception e0) { throw new ETLExtractException("", e0); }

    return searsList;
  }
}