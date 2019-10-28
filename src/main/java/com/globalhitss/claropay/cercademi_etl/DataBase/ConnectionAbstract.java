package com.globalhitss.claropay.cercademi_etl.DataBase;

import java.lang.ClassNotFoundException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;


/**
 * 
 */
public abstract class ConnectionAbstract
{
  Connection    currentConnection = null;
  PreparedStatement lastStatement = null;

  String timeManage     = "useUnicode=true"
                        + "&useJDBCCompliantTimezoneShift=true"
                        + "&useLegacyDatetimeCode=false"
                        + "&serverTimezone=UTC";
  
  String databaseUrl    = "";
  String databaseUser   = "";
  String databasePass   = "";
  String databaseDriver = "";
  
  /** */
  public ConnectionAbstract(
    String databaseDriver,
    String databaseUrl,
    String databaseUser,
    String databasePass
  ) {
    this.databaseDriver = databaseDriver;
    this.databaseUrl    = databaseUrl;
    this.databaseUser   = databaseUser;
    this.databasePass   = databasePass;
  }

  /** */
  public void startConnection()
    throws Exception, SQLException
  {
    Class.forName(databaseDriver);

    currentConnection = DriverManager.getConnection(
      databaseUrl + "?" + timeManage,
      databaseUser,
      databasePass
    );
  }

  /** */
  public void closeConnection()
    throws Exception, SQLException
  {
    if (currentConnection!=null) currentConnection.close();
    if (lastStatement!=null) lastStatement.close();
  }

  /** */
  public ResultSet get(String fields, String table) 
    throws SQLException
  {
    lastStatement = currentConnection.prepareStatement(
      "select " + fields + " from " + table
    );
    
    return lastStatement.executeQuery();
  }

  /** */
  public void tryInsert(String fields, String values, String table)
  {
    try{
      lastStatement = currentConnection.prepareStatement(
        "insert into " + table + "(" + fields + ") values (" + values + ")"
      );
      lastStatement.execute();
      lastStatement.close();
    }
    catch(SQLIntegrityConstraintViolationException e) {}
    catch(java.sql.SQLException e1) {}
    
    lastStatement = null;
  }
}