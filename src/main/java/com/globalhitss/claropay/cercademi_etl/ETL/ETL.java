package com.globalhitss.claropay.cercademi_etl.ETL;

import com.globalhitss.claropay.cercademi_etl.DataBase.ConnectionAbstract;
import com.globalhitss.claropay.cercademi_etl.DataBase.DestinationConnection;
import com.globalhitss.claropay.cercademi_etl.DataBase.SourceConnection;

import java.util.LinkedList;
import java.lang.Runnable;

/**
 * Represents the errors in fetch data process (extract).
 *
 * @author Ricardo Bermúdez Bermúdez
 * @since  Oct 22th, 2019.
 */
class ETLExtractException extends Exception 
{
  public ETLExtractException(String message, Exception cause)
  {
    super(message, cause);
  }
}

/**
 * Represents the errors in push data process (push or load process).
 *
 * @author Ricardo Bermúdez Bermúdez
 * @since  Oct 22th, 2019.
 */
class ETLLoadException extends Exception 
{
  public ETLLoadException(String message, Exception cause)
  {
    super(message, cause);
  }
}

/**
 * This class is an abstract of the ETL process. The below class defines the ETL
 * execution process and interfaces to perform this, in other words, it defines
 * the extract and load interfaces. The transformation process is defined by
 * <TransformInterface> that content the bind between old model and new model.
 * 
 * @author Ricardo Bermúdez Bermúdez
 * @since  Oct 22th, 2019.
 */
public abstract class ETL<TransformInterface> implements Runnable
{
  public  ConnectionAbstract source      = null;
  public  ConnectionAbstract destination = null;
  private String etlClassName = "";
    
  /** 
   * Initialize source and destination connection.
   */
  public ETL()
  {
    source       = new SourceConnection();
    destination  = new DestinationConnection();
    etlClassName = getClass().getSimpleName();
  }

  /** 
   * General process to run a ETL process.
   */
  @Override
  public void run()
  {
    System.out.println(etlClassName + ": START");

    try {
      LinkedList<TransformInterface> transformObjectList = extract();
      System.out.println(etlClassName + " - Extract completed!");
      load(transformObjectList);
      System.out.println(etlClassName + " - Charge completed!");
    } 
    catch (ETLExtractException e0) {
      System.out.println(etlClassName + " - Errors with data extraction.");
      e0.printStackTrace();
    }
    catch (ETLLoadException e2) {
      System.out.println(etlClassName + " - Errors with data push process.");
      e2.printStackTrace();
    }

    System.out.println(etlClassName + ": END");
  }
  
  /**
   * Retreive data from source connection.
   */
  abstract public LinkedList<TransformInterface> extract()
    throws ETLExtractException;

  /**
   * Push data over destination store.
   */
  abstract public void load(LinkedList<TransformInterface> transformObjectList)
    throws ETLLoadException;
}