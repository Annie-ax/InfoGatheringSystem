package com.hbut.info.igsServer.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by Administrator on 2016/4/12.
 */
public class BaseConfig {
    public static final String CONF_PROPERTIES  = "config.properties";
    public static Configuration conf;

    static {
        try {
            conf = new PropertiesConfiguration(CONF_PROPERTIES);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
