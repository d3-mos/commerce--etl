package com.globalhitss.claropay.cercademi_etl.Models;


/**
 * This class implements the transformation process to Commerce
 * structure from Sanborns structure (bind cat_sanborns table in origin 
 * to commerce_list table in destination database).
 *
 * @author  Ricardo Bermúdez Bermudez
 * @version 1.0.0, Oct 22th, 2019.
 * @see     Commerce
 * @see     CommerceNoCoords
 */
public class Sanborns extends CommerceNoCoords
{
  private String no_sucursal         = "";
  private String calle_y_numero      = "";
  private String colonia             = "";
  private String cp                  = "";
  private String localidad_municipio = "";
  private String estado              = "";

  /**
   * Constructor - Initializes the Sanborns object data. Moreover, it starts the
   * process to retrieve the coordinates.
   *
   * @param no_sucursal         Before ID.
   * @param calle_y_numero      Part of address.
   * @param colonia             Part of address.
   * @param cp                  Part of address.
   * @param localidad_municipio Part of address.
   * @param estado              Part of address.
   */
  public Sanborns(
    String no_sucursal,
    String calle_y_numero,
    String colonia,
    String cp,
    String localidad_municipio,
    String estado
  ){
    this.no_sucursal         = no_sucursal;
    this.calle_y_numero      = calle_y_numero;
    this.colonia             = colonia;
    this.cp                  = cp;
    this.localidad_municipio = localidad_municipio;
    this.estado              = estado;
    loadCoords();
  }

  /**
   * {@inheritDoc}
   */
  public String getSpecies()   { return "Sanborns"; }

  /**
   * {@inheritDoc}
   */
  public String getClassName() { return "null"; }

  /**
   * {@inheritDoc}
   */
  public String getPastId()    { return no_sucursal; }

  /**
   * {@inheritDoc}
   */
  public String getAddress()
  {
    return (calle_y_numero
      + ", " + colonia
      + ", " + cp
      + " "  + localidad_municipio
      + ", " + estado)
      .replaceAll("\'", "\\\\'")
      .trim()
      +".";
  }
}