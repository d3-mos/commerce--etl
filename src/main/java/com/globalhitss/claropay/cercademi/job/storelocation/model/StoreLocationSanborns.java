package com.globalhitss.claropay.cercademi.job.storelocation.model;


/**
 * This class implements the transformation process to Commerce
 * structure from Sanborns structure (bind cat_sanborns table in origin 
 * to commerce_list table in destination database).
 *
 * @author  Ricardo Bermúdez Bermudez
 * @version 1.0.0, Oct 22th, 2019.
 * @see     StoreLocation
 * @see     StoreLocationNoCoords
 */
public class StoreLocationSanborns extends StoreLocationNoCoords
{
  private String no_sucursal         = "";
  private String calle_y_numero      = "";
  private String colonia             = "";
  private String cp                  = "";
  private String localidad_municipio = "";
  private String estado              = "";
  private int brand;

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
  public StoreLocationSanborns(
    String no_sucursal,
    String calle_y_numero,
    String colonia,
    String cp,
    String localidad_municipio,
    String estado,
    int brand
  ){
    this.no_sucursal         = no_sucursal;
    this.calle_y_numero      = calle_y_numero;
    this.colonia             = colonia;
    this.cp                  = cp;
    this.localidad_municipio = localidad_municipio;
    this.estado              = estado;
    this.brand = brand;
    loadCoords();
  }

  /**
   * {@inheritDoc}
   */
  public int getBrand() { return this.brand; }

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