package com.globalhitss.claropay.cercademi_etl.ETL;

import  com.globalhitss.claropay.cercademi_etl.Models.Inbursa;
import  com.globalhitss.claropay.cercademi_etl.Models.Commerce;
import com.globalhitss.claropay.cercademi_etl.Models.CommerceNoCoords;

import java.util.LinkedList;
import java.sql.ResultSet;


/**
 * This custom ETL, implements the extract procedure to fetch all data from
 * cat_emisores table in origin database.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 23th, 2019.
 * @see     com.globalhitss.claropay.cercademi_etl.Models.Commerce
 * @see     com.globalhitss.claropay.cercademi_etl.Models.CommerceNoCoords
 * @see     com.globalhitss.claropay.cercademi_etl.Models.Inbursa
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
  public LinkedList<Commerce> extract()
    throws ETLExtractException
  {
    LinkedList<Commerce> inbursaList = new LinkedList<Commerce>();
    
    try {
      source.startConnection();

      ResultSet inbursaRows = source.get(
        "consecutivo, estado, ciudad_municipio, domicilio",
        "cat_emisores"
      );

      while (inbursaRows.next()) {
        inbursaList.add( new Inbursa(
          inbursaRows.getString("consecutivo"),
          inbursaRows.getString("domicilio")
        ) );
      }

      inbursaList.forEach(obj -> ((CommerceNoCoords) obj).waitingByCoords());

      source.closeConnection();
    }
    catch (Exception e) { throw new ETLExtractException("", e); }

    return inbursaList;
  }
}