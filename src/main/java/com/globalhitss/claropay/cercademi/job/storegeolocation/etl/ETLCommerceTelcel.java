package com.globalhitss.claropay.cercademi.job.storegeolocation.etl;

import java.util.LinkedList;

import com.globalhitss.claropay.cercademi.job.storegeolocation.model.Commerce;
import com.globalhitss.claropay.cercademi.job.storegeolocation.model.CommerceNoCoords;
import com.globalhitss.claropay.cercademi.job.storegeolocation.model.Telcel;

import java.sql.ResultSet;


/**
 * This custom ETL, implements the extract procedure to fetch all data from
 * cat_telcel table in origin database.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 23th, 2019.
 * @see     com.globalhitss.claropay.cercademi.job.storegeolocation.model.Commerce
 * @see     com.globalhitss.claropay.cercademi.job.storegeolocation.model.CommerceNoCoords
 * @see     com.globalhitss.claropay.cercademi.job.storegeolocation.model.Telcel
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
      destination.startConnection();

      ResultSet telcelRows = source.getUpdatedRows(
        "id_corresponsal,estado,ciudad,delegacion,colonia,calle,numero,cp",
        "cat_telcel",
        "last_change_ts"
      );
      
      ResultSet brandToken = destination.get(
        "STORE_ID",
        "CAT_STORE",
        "STORE='Telcel'"
      );
      brandToken.next();

      while (telcelRows.next()) {
        telcelList.add( new Telcel(
          telcelRows.getString("id_corresponsal"),
          telcelRows.getString("estado"),
          telcelRows.getString("ciudad"),
          telcelRows.getString("colonia"),
          telcelRows.getString("calle"),
          telcelRows.getString("numero"),
          telcelRows.getString("cp"),
          brandToken.getInt("STORE_ID")
        ) );
      }

      telcelList.forEach(obj -> ((CommerceNoCoords) obj).waitingByCoords());

      source.closeConnection();
      destination.closeConnection();
    }
    catch (Exception e0) { throw new ETLExtractException("", e0); }

    return telcelList;
  }    
}