package com.github.chen0040.zookeeper.server.components;


import com.github.chen0040.zookeeper.server.services.VersionService;
import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Properties;


/**
 * Created by xschen on 8/4/2017.
 */
@Component
public class Loader implements ApplicationListener<ApplicationReadyEvent> {

   @Autowired
   private VersionService versionService;

   private Process process = null;

   private static final Logger logger = LoggerFactory.getLogger(Loader.class);

   private ZooKeeperServerMain zooKeeperServer;

   @Override public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
      logger.info("profile: {}", versionService.getProfileString());
      logger.info("starting zookeeper ...");

      Properties startupProperties = new Properties();
      startupProperties.setProperty("dataDir", "/tmp");
      startupProperties.setProperty("clientPort", "2181");

      QuorumPeerConfig quorumConfiguration = new QuorumPeerConfig();
      try {
         quorumConfiguration.parseProperties(startupProperties);
      } catch(Exception e) {
         throw new RuntimeException(e);
      }

      zooKeeperServer = new ZooKeeperServerMain();
      final ServerConfig configuration = new ServerConfig();
      configuration.readFrom(quorumConfiguration);

      new Thread() {
         public void run() {
            try {
               zooKeeperServer.runFromConfig(configuration);
               logger.info("zookeeper started!");
            } catch (IOException e) {
               logger.error("ZooKeeper Failed", e);
            }
         }
      }.start();
   }

   @PreDestroy
   public void stopBeforeDestroy(){

   }
}
