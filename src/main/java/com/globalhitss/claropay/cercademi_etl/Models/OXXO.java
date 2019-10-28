package com.globalhitss.claropay.cercademi_etl.Models;


/**
 * This class implements the transformation process to Commerce
 * structure from OXXO structure (bind cat_oxxo table in origin 
 * to commerce_list table in destination database).
 *
 * @author  Ricardo Bermúdez Bermudez
 * @version 1.0.0, Oct 22th, 2019.
 * @see     Commerce
 * @see     CommerceNoCoords
 */
public class OXXO implements Commerce
{
  private String concatenado    = "";
  private String calle          = "";
  private String numero         = "";
  private String colonia        = "";
  private String codigo         = "";
  private String ciudad         = "";
  private String estado         = "";
  private double latitud        = 0.0;
  private double longitud       = 0.0;

  /**
   * Constructor - Initializes the Inbursa object data.
   *
   * @param concatenado Before ID.
   * @param calle       Part of address.
   * @param numero      Part of address.
   * @param colonia     Part of address.
   * @param codigo      Part of address.
   * @param ciudad      Part of address.
   * @param estado      Part of address.
   * @param latitud     Part of coordinates.
   * @param longitud    Part of coordinates.
   */
  public OXXO(
    String concatenado,
    String calle,
    String numero,
    String colonia,
    String codigo,
    String ciudad,
    String estado,
    double latitud,
    double longitud
  ){
    this.concatenado    = concatenado;
    this.calle          = calle;
    this.numero         = numero;
    this.colonia        = colonia;
    this.codigo         = codigo;
    this.ciudad         = ciudad;
    this.estado         = estado;
    this.latitud        = latitud;
    this.longitud       = longitud;
  }

  /**
   * {@inheritDoc}
   */
  public String getSpecies()   { return "OXXO"; }

  /**
   * {@inheritDoc}
   */
  public String getClassName() { return "null"; }
 
  /**
   * {@inheritDoc}
   */
  public double getLatitude()  { return latitud; }
 
  /**
   * {@inheritDoc}
   */
  public double getLongitude() { return longitud; }

  /**
   * {@inheritDoc}
   */
  public String getPastId()    { return concatenado; }

  /**
   * {@inheritDoc}
   */
  public String getAddress()
  {
    return (calle
      + " "  + numero
      + ", " + colonia
      + ", " + codigo
      + " "  + ciudad
      + " "  + estado)
      .replaceAll("\'", "\\\\'")
      .trim()
      +".";
  }
}