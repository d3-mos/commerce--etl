package com.globalhitss.claropay.cercademi_etl.ETL;

import  com.globalhitss.claropay.cercademi_etl.Models.Inbursa;
import  com.globalhitss.claropay.cercademi_etl.Models.Commerce;
import com.globalhitss.claropay.cercademi_etl.Models.CommerceNoCoords;

import java.util.LinkedList;
import java.sql.ResultSet;


/** */
public class ETLCommerceInbursa extends ETLCommerce
{

  /** */
  @Override
  public LinkedList<Commerce> extract()
    throws ETLExtractException
  {
    LinkedList inbursaList   = new LinkedList<Inbursa>();
    
    try {
      source.startConnection();

      ResultSet inbursaRows = source.get(
        "consecutivo, estado, ciudad_municipio, domicilio",
        "cat_emisores"
      );

      while (inbursaRows.next()) {
        inbursaList.add( new Inbursa(
          inbursaRows.getString("consecutivo"),
          inbursaRows.getString("estado"),
          inbursaRows.getString("ciudad_municipio"),
          inbursaRows.getString("domicilio")
        ) );
      }

      inbursaList.forEach(obj -> ((CommerceNoCoords) obj).waitingByCoords());

      source.closeConnection();
    }
    catch (Exception e) { throw new ETLExtractException("", e); }

    return inbursaList;
  }
}