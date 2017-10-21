package com.github.chen0040.zookeeper.server.components;


import com.github.chen0040.zookeeper.server.services.VersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
/**
 * Created by xschen on 8/4/2017.
 */
@Component
public class Loader implements ApplicationListener<ApplicationReadyEvent> {

   @Autowired
   private VersionService versionService;

   private Process process = null;

   private static final Logger logger = LoggerFactory.getLogger(Loader.class);

   @Override public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
      logger.info("profile: {}", versionService.getProfileString());


   }

   @PreDestroy
   public void stopBeforeDestroy(){

   }
}
