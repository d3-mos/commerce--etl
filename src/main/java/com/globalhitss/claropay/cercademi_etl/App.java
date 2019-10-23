package com.globalhitss.claropay.cercademi_etl;

import com.globalhitss.claropay.cercademi_etl.ETL.ETLCommerceSanborns;
import com.globalhitss.claropay.cercademi_etl.ETL.ETLCommerceInbursa;
import com.globalhitss.claropay.cercademi_etl.ETL.ETLCommerceOXXO;
import com.globalhitss.claropay.cercademi_etl.ETL.ETLCommerceSears;
import com.globalhitss.claropay.cercademi_etl.ETL.ETLCommerceTelcel;
import com.globalhitss.claropay.cercademi_etl.AppServices.AppProperties;

import java.lang.Thread;


/**
 * Represents the main process of ETL.
 * 
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0
 * @since   Oct 23th, 2019.
 */
public class App 
{
    /**
     * Run all ETL process of all models required.
     * 
     * @param  String[] args arguments passed from command line execution.
     * @return void 
     */
    public static void main( String[] args )
    {
      try {
        AppProperties.load();

        new Thread( new ETLCommerceOXXO()     ).start();
        new Thread( new ETLCommerceInbursa()  ).start();
        new Thread( new ETLCommerceSanborns() ).start();
        new Thread( new ETLCommerceSears()    ).start();
        new Thread( new ETLCommerceTelcel()   ).start();
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
}
