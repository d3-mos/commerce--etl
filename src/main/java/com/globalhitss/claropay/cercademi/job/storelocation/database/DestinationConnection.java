package com.globalhitss.claropay.cercademi.job.storelocation.database;

import com.globalhitss.claropay.cercademi.job.storelocation.config.AppProperties;


/**
 * Implements a custom set up of ConnectionAbstract class. In ETL process, this
 * class represents the load database.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 21th, 2019.
 * @see     ConnectionAbstract
 */
public class DestinationConnection extends ConnectionAbstract
{

  /**
   * Constructor - Call the super constructor to set the database connection
   * settings. 
   */
  public DestinationConnection()
  {
    super(
      AppProperties.get("db.destination.driverClassName"),
      AppProperties.get("db.destination.url"),
      AppProperties.get("db.destination.username"),
      AppProperties.get("db.destination.password")
    );
  }
}