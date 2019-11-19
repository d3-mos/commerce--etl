package com.globalhitss.claropay.cercademi.job.storegeolocation.model;


/**
 * This class implements the transformation process to Commerce
 * structure from Telcel structure (bind cat_telcel table in origin 
 * to commerce_list table in destination database).
 *
 * @author  Ricardo Bermúdez Bermudez
 * @version 1.0.0, Oct 22th, 2019.
 * @see     Commerce
 * @see     CommerceNoCoords
 */
public class Telcel extends CommerceNoCoords
{
  private String id_corresponsal = "";
  private String estado          = "";
  private String ciudad          = "";
  private String colonia         = "";
  private String calle           = "";
  private String numero          = "";
  private String cp              = "";
  private int brand;
  
  /**
   * Constructor - Initializes the Telcel object data. Moreover, it starts the
   * process to retrieve the coordinates.
   *
   * @param id_corresponsal Before ID.
   * @param estado          Part of address.
   * @param ciudad          Part of address.
   * @param delegacion      Part of address.
   * @param colonia         Part of address.
   * @param calle           Part of address.
   * @param numero          Part of address.
   * @param cp              Part of address.
   */
  public Telcel (
    String id_corresponsal,
    String estado,
    String ciudad,
    String colonia,
    String calle,
    String numero,
    String cp,
    int brand
  ) {
    this.id_corresponsal = id_corresponsal;
    this.estado = estado;
    this.ciudad = ciudad;
    this.colonia = colonia;
    this.calle = calle;
    this.numero = numero;
    this.cp = cp;
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
  public String getPastId()    { return id_corresponsal; }

  /**
   * {@inheritDoc}
   */
  public String getAddress()
  {
    return (calle
      + " "  + numero
      + ", " + colonia
      + ", " + cp
      + " "  + ciudad
      + " "  + estado)
      .replaceAll("\'", "\\\\'")
      .trim()
      +".";
  }
}