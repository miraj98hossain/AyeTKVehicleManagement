package com.mhdev.mobileservice.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServerConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        Connector httpConnector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        httpConnector.setPort(8082); // Your desired HTTP port
        factory.addAdditionalTomcatConnectors(httpConnector);
    }
}
