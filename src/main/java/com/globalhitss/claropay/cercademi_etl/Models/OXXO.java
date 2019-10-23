package com.globalhitss.claropay.cercademi_etl.Models;


/** */
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

  public String getSpecies()   { return "OXXO"; }

  public String getClassName() { return "null"; }
 
  public double getLatitude()  { return latitud; }
 
  public double getLongitude() { return longitud; }

  public String getPastId()    { return concatenado; }

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