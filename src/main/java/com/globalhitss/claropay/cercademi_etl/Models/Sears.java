package com.globalhitss.claropay.cercademi_etl.Models;


/** */
public class Sears extends CommerceNoCoords
{
  private String no_tienda;
  private String direccion;

  public Sears(
    String no_tienda,
    String direccion
  ) {
    this.no_tienda = no_tienda;
    this.direccion = direccion;
    loadCoords();
  }


  public String getSpecies()   { return "Sears"; }

  public String getClassName() { return "null"; }

  public String getPastId()    { return no_tienda; }

  public String getAddress()   { return direccion; }
}