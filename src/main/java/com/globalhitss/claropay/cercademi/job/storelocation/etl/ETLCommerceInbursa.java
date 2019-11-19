package com.globalhitss.claropay.cercademi.job.storelocation.etl;

import java.util.LinkedList;

import com.globalhitss.claropay.cercademi.job.storelocation.model.StoreLocation;
import com.globalhitss.claropay.cercademi.job.storelocation.model.StoreLocationNoCoords;
import com.globalhitss.claropay.cercademi.job.storelocation.model.StoreLocationInbursa;

import java.sql.ResultSet;


/**
 * This custom ETL, implements the extract procedure to fetch all data from
 * cat_emisores table in origin database.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 23th, 2019.
 * @see     com.globalhitss.claropay.cercademi.job.storelocation.model.StoreLocation
 * @see     com.globalhitss.claropay.cercademi.job.storelocation.model.StoreLocationNoCoords
 * @see     com.globalhitss.claropay.cercademi.job.storelocation.model.StoreLocationInbursa
 * @see     ETL
 * @see     ETLCommerce
 */
public class ETLCommerceInbursa extends ETLCommerce
{

  /**
   * {@inheritDoc}
   * 
   * It's the custom extract procedure implementation to Inbursa objects. If
   * it's require, the procedure wait all requests of coordinates of each
   * commerce.
   */
  @Override
  public LinkedList<StoreLocation> extract()
    throws ETLExtractException
  {
    LinkedList<StoreLocation> inbursaList = new LinkedList<StoreLocation>();
    
    try {
      source.startConnection();
      destination.startConnection();

      ResultSet inbursaRows = source.getUpdatedRows(
        "consecutivo, estado, ciudad_municipio, domicilio",
        "cat_emisores",
        "last_change_ts"
      );
      
      ResultSet brandToken = destination.get(
        "STORE_ID",
        "CAT_STORE",
        "STORE='Inbursa'"
      );
      brandToken.next();
      
      while (inbursaRows.next()) {
        inbursaList.add( new StoreLocationInbursa(
          inbursaRows.getString("consecutivo"),
          inbursaRows.getString("domicilio"),
          brandToken.getInt("STORE_ID")
        ) );
      }

      inbursaList.forEach(obj -> ((StoreLocationNoCoords) obj).waitingByCoords());

      source.closeConnection();
      destination.closeConnection();
    }
    catch (Exception e) { throw new ETLExtractException("", e); }

    return inbursaList;
  }
}