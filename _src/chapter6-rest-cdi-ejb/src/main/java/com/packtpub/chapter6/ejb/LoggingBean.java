package com.packtpub.chapter6.ejb;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

@Stateless
public class LoggingBean {

    private Logger logger = Logger.getLogger(getClass());

    public void log() {
        logger.info("Logging during creation of a singleton bean");
    }

}
