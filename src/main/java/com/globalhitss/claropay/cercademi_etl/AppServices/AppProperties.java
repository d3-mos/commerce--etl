package com.globalhitss.claropay.cercademi_etl.AppServices;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/** */
public class AppProperties
{
  private static Properties envProps = null;

  /** */
  public static void load() throws Exception
  {
    envProps = new Properties();
    
    try {
      envProps.load(
        AppProperties.class
        .getClassLoader()
        .getResourceAsStream("config.properties")
      );
    } catch (IOException e) {
      throw new Exception("Unable to load properties file.", e);
    }
  }

  /** */
  public static String get(String key)
  {
    return envProps.getProperty(key, "");
  }
}