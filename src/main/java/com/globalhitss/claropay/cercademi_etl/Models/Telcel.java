package com.globalhitss.claropay.cercademi_etl.Models;


public class Telcel extends CommerceNoCoords
{
  String id_corresponsal = "";
  String estado = "";
  String ciudad = "";
  String delegacion = "";
  String colonia = "";
  String calle = "";
  String numero = "";
  String cp = "";
  String municipio    = "";

  public Telcel(
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
  }

  public String getSpecies()   { return "Telcel"; }

  public String getClassName() { return "null"; }

  public String getPastId()    { return id_corresponsal; }

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