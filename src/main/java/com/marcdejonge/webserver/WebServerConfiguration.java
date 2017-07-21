package com.marcdejonge.webserver;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class WebServerConfiguration {
    @NotNull
    public static WebServerConfiguration fromFile() throws ConfigurationException {
        File configFile = new File("/etc/webserver.conf");
        if (!configFile.isFile()) {
            configFile = new File("/usr/local/etc/webserver.conf");
        }
        if (!configFile.isFile()) {
            configFile = new File("etc/webserver.conf");
        }
        if (!configFile.isFile()) {
            configFile = new File("webserver.conf");
        }
        if (!configFile.isFile()) {
            configFile = new File("src/main/resources/webserver.conf");
        }
        if (!configFile.isFile()) {
            throw new ConfigurationException("Could not find a file 'webserver.conf' anywhere");
        }

        return new WebServerConfiguration(new Configurations().properties(configFile));
    }

    private final int port;

    public WebServerConfiguration(Configuration config) {
        this.port = config.getInt("port", 8080);
    }

    public int getPort() {
        return port;
    }
}
