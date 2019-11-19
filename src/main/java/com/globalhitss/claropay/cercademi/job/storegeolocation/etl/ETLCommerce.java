package com.globalhitss.claropay.cercademi.job.storegeolocation.etl;

import java.util.LinkedList;

import com.globalhitss.claropay.cercademi.job.storegeolocation.model.Commerce;


/** 
 * {@inheritDoc}
 * 
 * It sets the load procedure custom to Commerce structure. To goal this,
 * the structure to load MUST implements the
 * {@link com.globalhitss.claropay.cercademi.job.storegeolocation.model.Commerce} interface.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 23th, 2019.
 * @see     com.globalhitss.claropay.cercademi.job.storegeolocation.model.Commerce
 * @see     ETL
 */
public abstract class ETLCommerce extends ETL<Commerce>
{
  
  /**
   * Represents the load procedure to store in new database the data transformed
   * by <i>link methods</i>.
   *
   * @param  commerceList     Commerce list to store.
   * @throws ETLLoadException If an error occur over this procedure.
   */
  @Override
  public void load(LinkedList<Commerce> commerceList)
    throws ETLLoadException
  {
    try {
      destination.startConnection();

      commerceList.forEach( commerce -> destination.tryUpdate(
        "BRAND, ADDRESS, LATITUDE, LONGITUDE, PAST_ID",
        "'" + commerce.getBrand()      + "'," +
        "'" + commerce.getAddress()    + "'," +
        "'" + commerce.getLatitude()   + "'," +
        "'" + commerce.getLongitude()  + "'," +
        "'" + commerce.getPastId()     + "'",
        "CAT_STORE_LOCATION",
        "PAST_ID='" + commerce.getPastId() + "' and " +
        "BRAND='"   + commerce.getBrand()  + "'",
        true
      ) );

      destination.closeConnection();
    }
    catch (Exception e1) { throw new ETLLoadException("", e1); }
  }
}