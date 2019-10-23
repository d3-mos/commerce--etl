package com.globalhitss.claropay.cercademi_etl.DataBase;

import com.globalhitss.claropay.cercademi_etl.AppServices.AppProperties;


/** */
public class SourceConnection extends ConnectionAbstract
{

  /** */
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