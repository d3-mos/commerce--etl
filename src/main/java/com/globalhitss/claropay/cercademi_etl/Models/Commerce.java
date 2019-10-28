package com.globalhitss.claropay.cercademi_etl.Models;

/**
 * This class describe the commerce interface to retrieve the data values of a
 * commerce to store all in destination database into commerce_list table. On the
 * other hand, this class defines the <i>link methods</i> between the old
 * model and the new model.
 *
 * Moreover, each implementation can be considered an ORM implementation over
 * original structure.
 *
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 21th, 2019.
 */
public interface Commerce
{
  /**
   * Link method - This method is use to retrieve the commerce species such as
   * "OXXO", "Inbursa", "Sears", etc.
   */
  public String getSpecies();

  /**
   * Link method - This method is use to retrieve the commerce class such as
   * "ACEPTA_PAGO", "RETIRAR", etc.
   */
  public String getClassName();

  /**
   * Link method - This method is use to retrieves the commerce address builded
   * from original data.
   */
  public String getAddress();

  /**
   * Link method - This method is use to retrieve latest ID of current commerce,
   * ie, the ID used in original table to identify the current commerce.
   */
  public String getPastId();

  /**
   * Link method - This method is use to retrieve the latitude of commerce.
   */
  public double getLatitude();
  
  /**
   * Link method - This method is use to retrieve the longitude of commerce.
   */
  public double getLongitude();
}