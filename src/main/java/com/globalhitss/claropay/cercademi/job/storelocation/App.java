package com.globalhitss.claropay.cercademi.job.storelocation;

import java.lang.Thread;

import com.globalhitss.claropay.cercademi.job.storelocation.config.AppProperties;
import com.globalhitss.claropay.cercademi.job.storelocation.etl.ETLCommerceInbursa;
import com.globalhitss.claropay.cercademi.job.storelocation.etl.ETLCommerceOXXO;
import com.globalhitss.claropay.cercademi.job.storelocation.etl.ETLCommerceSanborns;
import com.globalhitss.claropay.cercademi.job.storelocation.etl.ETLCommerceSears;
import com.globalhitss.claropay.cercademi.job.storelocation.etl.ETLCommerceTelcel;


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
     * @param  args arguments passed from command line execution.
     * @return void 
     */
    public static void main( String[] args )
    {
      try {
        AppProperties.load();
        new Thread( new ETLCommerceInbursa()  ).start();
        new Thread( new ETLCommerceSears()    ).start();
        new Thread( new ETLCommerceTelcel()   ).start();
        new Thread( new ETLCommerceOXXO()     ).start();
        new Thread( new ETLCommerceSanborns() ).start();
      }
      catch(Exception e) {}
    }
}
