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

package com.safeway.app.emju.dao.connector;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;
import com.datastax.driver.mapping.MappingManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.safeway.app.emju.dao.exception.ConnectionException;
import com.safeway.app.emju.helper.ValidationHelper;
import com.safeway.app.emju.logging.Logger;
import com.safeway.app.emju.logging.LoggerFactory;

import play.Configuration;
import play.inject.ApplicationLifecycle;
import play.libs.F;

/* ***************************************************************************
 * NAME         : CassandraConnector.java
 *
 * SYSTEM       : emju-common
 *
 * AUTHOR       : Anshu Kumar
 *
 * REVISION HISTORY
 *
 * Revision 0.0.0.0 Sep 9, 2015 akuma27
 * Revision 0.0.0.1 Sep 24, 2015 mniet05
 *
 * Initial creation for emju-common
 * Addition of Method for accessor handling
 ***************************************************************************/

/**
 * @author akuma27
 */
@Singleton
public class CassandraConnector {

    private final static Logger LOGGER = LoggerFactory.getLogger(CassandraConnector.class);

    private static final String KEYSPACE_NAME = "emju";
    private static final String CASSANDRA_NODES = "cassandra.nodes";
    private static final String CASSANDRA_PORT = "cassandra.port";
    private static final String CASSANDRA_SECURED = "cassandra.secured";
    private static final String CASSANDRA_USER = "cassandra.user";
    private static final String CASSANDRA_PASSWORD = "cassandra.password";

    private Cluster cluster;
    private Session session;
    
    private MappingManager manager;
    
    private Map<String, PreparedStatement> statementCache = new ConcurrentHashMap<>();
    private Map<String, Object> accessorCache = new ConcurrentHashMap<>();



    @Inject
    public CassandraConnector(final ApplicationLifecycle lifecycle, final Configuration configuration) {
        LOGGER.debug("inside CassandraConnector()");
        String hosts = configuration.getString(CASSANDRA_NODES);
        String port = configuration.getString(CASSANDRA_PORT);
        LOGGER.debug("CASSANDRA_NODES=" + hosts);
        connect(hosts, port, configuration);

        lifecycle.addStopHook(() -> {
            shutdown();
            return F.Promise.pure(null);
        });

    }

    private void connect(final String hosts, final String port, final Configuration configuration) {
        try {
            LOGGER.debug("Entering connect()...");
            String[] hostList = hosts.split(",");

            Builder builder = Cluster.builder().addContactPoints(hostList)
            								   .withQueryOptions(new QueryOptions().setConsistencyLevel(ConsistencyLevel.LOCAL_ONE))
            								   .withLoadBalancingPolicy(new TokenAwarePolicy(new DCAwareRoundRobinPolicy()));
            
            if (ValidationHelper.isNumber(port)) {
                builder = builder.withPort(Integer.parseInt(port));
            }

            boolean isSecured = configuration.getBoolean(CASSANDRA_SECURED, Boolean.FALSE);
            if (isSecured) {
                String userId = configuration.getString(CASSANDRA_USER);
                String password = configuration.getString(CASSANDRA_PASSWORD);
                builder.withCredentials(userId, password);
            }

            /*
            PoolingOptions poolingOptions = new PoolingOptions();
            
            poolingOptions
            .setMaxRequestsPerConnection(HostDistance.LOCAL, 32768)
            .setMaxRequestsPerConnection(HostDistance.REMOTE, 2000)
            
            .setCoreConnectionsPerHost(HostDistance.LOCAL,  50)
            .setMaxConnectionsPerHost( HostDistance.LOCAL, 256)
            .setCoreConnectionsPerHost(HostDistance.REMOTE, 50)
            .setMaxConnectionsPerHost( HostDistance.REMOTE, 256);
            
            
            cluster = builder.withPoolingOptions(poolingOptions).build();
            */
            cluster = builder.build();
            session = cluster.connect(KEYSPACE_NAME);
            
            manager = new MappingManager(session);
            
            LOGGER.info("Acquired sesson for keyspace, " + KEYSPACE_NAME);
        }
        catch (Exception e) {
            LOGGER.error("Cassandra connection error", e);
        }
    }

    public Session getSession() {
        if (session == null) {
            throw new ConnectionException();
        }
        return session;
    }

    public Cluster getCluster() {
        return cluster;
    }
    
    public MappingManager getManager() {
		return manager;
	}

    public MappingManager getMappingManager(){
        return manager;
    }

    @SuppressWarnings("unchecked")
	public <T> T getAccessor(Class<T> clazz) {
    	
    	String className = clazz.getName();
    	
		Object object = accessorCache.get(className);
    	
		if(object == null){
			object = getManager().createAccessor(clazz);
		    accessorCache.put(className, object);
		}
		
		return (T) object;
	}
    
    public BoundStatement getStatement(String cql, Session session) {
        PreparedStatement ps = statementCache.get(cql);
        // no statement cached, create one and cache it now.
        if (ps == null) {
            ps = session.prepare(cql);
            ps.setConsistencyLevel(ConsistencyLevel.LOCAL_ONE);
            statementCache.put(cql, ps);
        }
        return ps.bind();
    }
    
    private void shutdown() {
        LOGGER.info("Closing connections...");
        session.close();
        cluster.close();
    }
}
