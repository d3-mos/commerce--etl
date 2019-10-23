package com.globalhitss.claropay.cercademi_etl.Models;


/** */
public class Inbursa implements Commerce
{
  private String consecutivo;
  private String estado;
  private String ciudad_municipio;
  private String domicilio;

  public Inbursa(
    String consecutivo,
    String estado,
    String ciudad_municipio,
    String domicilio
  ) {
    this.consecutivo      = consecutivo;
    this.estado           = estado;
    this.ciudad_municipio = ciudad_municipio;
    this.domicilio        = domicilio;
  }

  public String getSpecies()   { return "Inbursa"; }

  public String getClassName() { return "null"; }

  public double getLatitude()  { return 0.0; }
 
  public double getLongitude() { return 0.0; }

  public String getPastId() { return consecutivo; }

  public String getAddress()
  {
    return domicilio
      .replaceAll("\'", "\\\\'")
      .trim()
      +".";
  }
}