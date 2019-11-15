package com.globalhitss.claropay.cercademi_etl.ETL;

import com.globalhitss.claropay.cercademi_etl.Models.Commerce;

import java.util.LinkedList;


/** 
 * {@inheritDoc}
 * 
 * It sets the load procedure custom to Commerce structure. To goal this,
 * the structure to load MUST implements the
 * {@link com.globalhitss.claropay.cercademi_etl.Models.Commerce} interface.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 23th, 2019.
 * @see     com.globalhitss.claropay.cercademi_etl.Models.Commerce
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

      commerceList.forEach( commerce -> destination.tryInsert(
          "species, class_name, address, lat, lng, past_id",
          "'" + commerce.getSpecies()    + "'," +
          " " + commerce.getClassName()  + "," +
          "'" + commerce.getAddress()    + "'," +
          "'" + commerce.getLatitude()   + "'," +
          "'" + commerce.getLongitude()  + "'," +
          "'" + commerce.getPastId()     + "'",
          "commerce_list"
      ) );

      destination.closeConnection();
    }
    catch (Exception e1) { throw new ETLLoadException("", e1); }
  }
}