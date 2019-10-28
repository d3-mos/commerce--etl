package com.globalhitss.claropay.cercademi_etl.ETL;

import com.globalhitss.claropay.cercademi_etl.Models.OXXO;
import com.globalhitss.claropay.cercademi_etl.Models.Commerce;

import java.util.LinkedList;
import java.sql.ResultSet;


/**
 * This custom ETL, implements the extract procedure to fetch all data from
 * cat_oxxo table in origin database.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 23th, 2019.
 * @see     com.globalhitss.claropay.cercademi_etl.Models.Commerce
 * @see     com.globalhitss.claropay.cercademi_etl.Models.OXXO
 * @see     ETL
 * @see     ETLCommerce
 */
public class ETLCommerceOXXO extends ETLCommerce
{

  /**
   * {@inheritDoc}
   * 
   * It's the custom extract procedure implementation to OXXO objects.
   */
  @Override
  public LinkedList<Commerce> extract() 
    throws ETLExtractException
  { 
    LinkedList oxxoList = new LinkedList<OXXO>();
    ResultSet  oxxoRows = null;
    
    try {
      source.startConnection();

      oxxoRows = source.get(
        "concatenado, calle, numero, colonia, codigo, "
       +"ciudad, estado, "
       +"latitud, longitud",
        "cat_oxxo"
      );

      while (oxxoRows.next()) {
        oxxoList.add( new OXXO(
          oxxoRows.getString("concatenado"),
          oxxoRows.getString("calle"),
          oxxoRows.getString("numero"),
          oxxoRows.getString("colonia"),
          oxxoRows.getString("codigo"),
          oxxoRows.getString("ciudad"),
          oxxoRows.getString("estado"),
          oxxoRows.getDouble("latitud"),
          oxxoRows.getDouble("longitud")
        ) );
      }

      source.closeConnection();
    }
    catch (Exception e0) { throw new ETLExtractException("", e0); }

    return oxxoList;
  }
}