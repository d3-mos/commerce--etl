package com.globalhitss.claropay.cercademi_etl.Models;


/** */
public class Sanborns extends CommerceNoCoords
{
  private String no_sucursal         = "";
  private String calle_y_numero      = "";
  private String colonia             = "";
  private String cp                  = "";
  private String localidad_municipio = "";
  private String estado              = "";

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

  public String getSpecies()   { return "Sanborns"; }

  public String getClassName() { return "null"; }

  public String getPastId()    { return no_sucursal; }

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