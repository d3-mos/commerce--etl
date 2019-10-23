package com.globalhitss.claropay.cercademi_etl.DataBase;

import com.globalhitss.claropay.cercademi_etl.AppServices.AppProperties;


/** */
public class DestinationConnection extends ConnectionAbstract
{

  /** */
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