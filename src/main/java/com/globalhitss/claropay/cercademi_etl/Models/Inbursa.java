package com.globalhitss.claropay.cercademi_etl.Models;


/** */
public class Inbursa extends CommerceNoCoords
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
    loadCoords();
  }

  public String getSpecies()   { return "Inbursa"; }

  public String getClassName() { return "null"; }

  public String getPastId() { return consecutivo; }

  public String getAddress()
  {
    return domicilio
      .replaceAll("\'", "\\\\'")
      .trim()
      +".";
  }
}