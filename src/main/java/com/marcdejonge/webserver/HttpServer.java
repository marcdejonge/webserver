package com.marcdejonge.webserver;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpServer {
    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) throws IOException {
        try {
            WebServerConfiguration config = WebServerConfiguration.fromFile();
            new HttpServer(config).start();
        } catch (ConfigurationException e) {
            logger.error("Invalid configuration", e);
        }
    }

    private final WebServerConfiguration config;

    public HttpServer(WebServerConfiguration config) {
        this.config = config;
    }

    private void start() {
        logger.debug("Starting on port {}", config.getPort());
    }
}
