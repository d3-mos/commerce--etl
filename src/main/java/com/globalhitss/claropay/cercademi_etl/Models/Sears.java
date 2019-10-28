package com.globalhitss.claropay.cercademi_etl.Models;


/**
 * This class implements the transformation process to Commerce
 * structure from Sears structure (bind cat_sears table in origin 
 * to commerce_list table in destination database).
 *
 * @author  Ricardo Bermúdez Bermudez
 * @version 1.0.0, Oct 22th, 2019.
 * @see     Commerce
 * @see     CommerceNoCoords
 */
public class Sears extends CommerceNoCoords
{
  private String no_tienda;
  private String direccion;

  /**
   * Constructor - Initializes the Sanborns object data. Moreover, it starts the
   * process to retrieve the coordinates.
   *
   * @param no_sucursal    Before ID.
   * @param calle_y_numero Location address.
   */
  public Sears(
    String no_tienda,
    String direccion
  ) {
    this.no_tienda = no_tienda;
    this.direccion = direccion;
    loadCoords();
  }


  /**
   * {@inheritDoc}
   */
  public String getSpecies()   { return "Sears"; }

  /**
   * {@inheritDoc}
   */
  public String getClassName() { return "null"; }

  /**
   * {@inheritDoc}
   */
  public String getPastId()    { return no_tienda; }

  /**
   * {@inheritDoc}
   */
  public String getAddress()   { return direccion; }
}