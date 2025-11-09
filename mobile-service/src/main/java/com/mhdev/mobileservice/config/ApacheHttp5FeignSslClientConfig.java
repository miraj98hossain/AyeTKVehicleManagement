package com.mhdev.mobileservice.config;

import feign.Feign;
import feign.Retryer;
import feign.hc5.ApacheHttp5Client;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@Slf4j
@Configuration
public class ApacheHttp5FeignSslClientConfig {
    @Value("${server.ssl.trust-store}")
    private Resource trustStore;

    @Value("${server.ssl.trust-store-password}")
    private String trustStorePassword;
    @Value("${server.ssl.key-store}")
    private Resource keyStore;

    @Value("${server.ssl.key-store-password}")
    private String keyStorePassword;

    @Bean
    public Feign.Builder feignBuilder() {
        SSLContext sslContext = getSSLContext();
        final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                .setSslContext(sslContext)
                .build();

        final HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslSocketFactory)
                .build();

        return Feign.builder().retryer(Retryer.NEVER_RETRY).client(new ApacheHttp5Client(HttpClients.custom().setConnectionManager(cm).build()));
    }

    private SSLContext getSSLContext() {
        try {
            return SSLContexts.custom()
                    .loadKeyMaterial(keyStore.getURL(), keyStorePassword.toCharArray(), keyStorePassword.toCharArray())
                    .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
                    .build();
        } catch (IOException | UnrecoverableKeyException | CertificateException | NoSuchAlgorithmException |
                 KeyStoreException | KeyManagementException e) {
            log.error("Error while building SSLContext for ApacheHttp5FeignSslClient", e);
            throw new ExceptionInInitializerError("Error while building SSLContext for ApacheHttp5FeignSslClient");
        }


    }
}
