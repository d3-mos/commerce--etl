package com.globalhitss.claropay.cercademi_etl.ETL;

import com.globalhitss.claropay.cercademi_etl.Models.Telcel;
import com.globalhitss.claropay.cercademi_etl.Models.Commerce;
import com.globalhitss.claropay.cercademi_etl.Models.CommerceNoCoords;

import java.util.LinkedList;
import java.sql.ResultSet;


/**
 * This custom ETL, implements the extract procedure to fetch all data from
 * cat_telcel table in origin database.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 23th, 2019.
 * @see     com.globalhitss.claropay.cercademi_etl.Models.Commerce
 * @see     com.globalhitss.claropay.cercademi_etl.Models.CommerceNoCoords
 * @see     com.globalhitss.claropay.cercademi_etl.Models.Telcel
 * @see     ETL
 * @see     ETLCommerce
 */
public class ETLCommerceTelcel extends ETLCommerce
{

  /**
   * {@inheritDoc}
   * 
   * It's the custom extract procedure implementation to Telcel objects. If
   * it's require, the procedure wait all requests of coordinates of each
   * commerce.
   */
  @Override
  public LinkedList<Commerce> extract() 
    throws ETLExtractException
  {
    LinkedList<Commerce> telcelList = new LinkedList<Commerce>();
    
    try {
      source.startConnection();

      ResultSet telcelRows = source.get(
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

      telcelList.forEach(obj -> ((CommerceNoCoords) obj).waitingByCoords());

      source.closeConnection();
    }
    catch (Exception e0) { throw new ETLExtractException("", e0); }

    return telcelList;
  }    
}