package com.globalhitss.claropay.cercademi.job.storegeolocation.etl;

import java.util.LinkedList;

import com.globalhitss.claropay.cercademi.job.storegeolocation.model.Commerce;
import com.globalhitss.claropay.cercademi.job.storegeolocation.model.CommerceNoCoords;
import com.globalhitss.claropay.cercademi.job.storegeolocation.model.Sears;

import java.sql.ResultSet;


/**
 * This custom ETL, implements the extract procedure to fetch all data from
 * cat_sears table in origin database.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 23th, 2019.
 * @see     com.globalhitss.claropay.cercademi.job.storegeolocation.model.Commerce
 * @see     com.globalhitss.claropay.cercademi.job.storegeolocation.model.CommerceNoCoords
 * @see     com.globalhitss.claropay.cercademi.job.storegeolocation.model.Sears
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
      destination.startConnection();

      ResultSet searsRows = source.getUpdatedRows(
        "no_tienda, direccion",
        "cat_sears",
        "last_change_ts"
      );
      ResultSet brandToken = destination.get(
        "STORE_ID",
        "CAT_STORE",
        "STORE='Sears'"
      );
      brandToken.next();
        
      while (searsRows.next()) {
        searsList.add( new Sears(
          searsRows.getString("no_tienda"),
          searsRows.getString("direccion"),
          brandToken.getInt("STORE_ID")
        ) );
      }

      searsList.forEach(obj -> ((CommerceNoCoords) obj).waitingByCoords());

      source.closeConnection();
      destination.closeConnection();
    }
    catch (Exception e0) { throw new ETLExtractException("", e0); }

    return searsList;
  }
}