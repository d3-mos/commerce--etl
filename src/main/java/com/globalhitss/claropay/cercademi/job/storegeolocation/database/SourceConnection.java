package com.globalhitss.claropay.cercademi.job.storegeolocation.database;

import com.globalhitss.claropay.cercademi.job.storegeolocation.config.AppProperties;


/**
 * Implements a custom set up of ConnectionAbstract class. In ETL process, this
 * class represents the initial database, where store the original data.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 21th, 2019.
 * @see     ConnectionAbstract
 */
public class SourceConnection extends ConnectionAbstract
{

  /**
   * Constructor - Call the super constructor to set the database connection
   * settings. 
   */
  public SourceConnection()
  {
    super(
      AppProperties.get("db.source.driverClassName"),
      AppProperties.get("db.source.url"),
      AppProperties.get("db.source.username"),
      AppProperties.get("db.source.password")
    );
  }
} 