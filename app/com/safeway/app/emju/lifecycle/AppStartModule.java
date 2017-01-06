package com.safeway.app.emju.lifecycle;
/* **************************************************************************
 * Copyright 2015 Albertsons Safeway.
 *
 * This document/file contains proprietary data that is the property of
 * Albertsons Safeway.  Information contained herein may not be used,
 * copied or disclosed in whole or in part except as permitted by a
 * written agreement signed by an officer of Albertsons Safeway.
 *
 * Unauthorized use, copying or other reproduction of this document/file
 * is prohibited by law.
 *
 ***************************************************************************/



import com.google.inject.AbstractModule;
import com.safeway.app.emju.dao.connector.CassandraConnector;
import com.safeway.app.emju.logging.Logger;
import com.safeway.app.emju.logging.LoggerFactory;

import play.Configuration;
import play.Environment;

/* ***************************************************************************
 * NAME         : AppStartModule.java
 *
 * SYSTEM       : emju-common
 *
 * AUTHOR       : Anshu Kumar
 *
 * REVISION HISTORY
 *
 * Revision 0.0.0.0 Sep 9, 2015 akuma27
 * Initial creation for emju-common
 *
 ***************************************************************************/

/**
 *
 * @author akuma27
 */
public class AppStartModule extends AbstractModule {

    private final static Logger LOGGER = LoggerFactory.getLogger(AppStartModule.class);

    //private final Environment environment;
    private final Configuration configuration;

    public AppStartModule(Configuration configuration) {

        this.configuration = configuration;
    }
    
    @Override
    protected void configure() {
        // Eager load CassandraConnection to ensure its initialized when application starts
        bind(CassandraConnector.class).asEagerSingleton();
        
		
		LOGGER.info("Finished loading the startup classes...");
		
    }

}

