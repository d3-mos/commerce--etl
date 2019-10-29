package com.globalhitss.claropay.cercademi_etl.Models;


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
    String domicilio
  ) {
    this.consecutivo      = consecutivo;
    this.domicilio        = domicilio;
    loadCoords();
  }

  /**
   * {@inheritDoc}
   */
  public String getSpecies()   { return "Inbursa"; }

  /**
   * {@inheritDoc}
   */
  public String getClassName() { return "null"; }

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