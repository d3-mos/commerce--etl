package com.globalhitss.claropay.cercademi.job.storegeolocation.model;


/**
 * This class implements the transformation process to Commerce
 * structure from Inbursa structure (bind cat_inbursa table in origin 
 * to commerce_list table in destination database).
 *
 * @author  Ricardo Bermúdez Bermudez
 * @version 1.0.0, Oct 22th, 2019.
 * @see     Commerce
 * @see     CommerceNoCoords
 */
public class Inbursa extends CommerceNoCoords
{
  private String consecutivo;
  private String domicilio;
  private int brand;

  /**
   * Constructor - Initializes the Inbursa object data. Moreover, it starts the
   * process to retrieve the coordinates.
   *
   * @param consecutivo      Before ID.
   * @param estado           Part of address.
   * @param ciudad_municipio Part of address.
   * @param domicilio        Part of address.
   */
  public Inbursa(
    String consecutivo,
    String domicilio,
    int brand
  ) {
    this.consecutivo = consecutivo;
    this.domicilio   = domicilio;
    this.brand       = brand;
    loadCoords();
  }

  /**
   * {@inheritDoc}
   */
  public int getBrand()   { return this.brand; }

  /**
   * {@inheritDoc}
   */
  public String getPastId() { return consecutivo; }

  /**
   * {@inheritDoc}
   */
  public String getAddress()
  {
    return domicilio
      .replaceAll("\'", "\\\\'")
      .trim()
      +".";
  }
}