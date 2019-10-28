package com.globalhitss.claropay.cercademi_etl.Models;


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
  String id_corresponsal = "";
  String estado          = "";
  String ciudad          = "";
  String delegacion      = "";
  String colonia         = "";
  String calle           = "";
  String numero          = "";
  String cp              = "";
  String municipio       = "";

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
    String delegacion,
    String colonia,
    String calle,
    String numero,
    String cp
  ) {
    this.id_corresponsal = id_corresponsal;
    this.estado = estado;
    this.ciudad = ciudad;
    this.delegacion = delegacion;
    this.colonia = colonia;
    this.calle = calle;
    this.numero = numero;
    this.cp = cp;
    loadCoords();
  }

  /**
   * {@inheritDoc}
   */
  public String getSpecies()   { return "Telcel"; }

  /**
   * {@inheritDoc}
   */
  public String getClassName() { return "null"; }

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