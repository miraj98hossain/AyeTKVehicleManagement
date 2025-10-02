package com.mhdev.backendservice.config;


import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.GeneralSecurityException;

//@Configuration
public class RestTemplateConfig {

    @Value("${server.ssl.trust-store}")
    private Resource trustStore;

    @Value("${server.ssl.trust-store-password}")
    private String trustStorePassword;

    @Bean
    public CloseableHttpClient customHttpClient() throws IOException, GeneralSecurityException {

        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
                .build();

        final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                .setSslContext(sslContext)
                .build();

        final HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslSocketFactory)
                .build();

        return HttpClients.custom()
                .setConnectionManager(cm)
                .evictExpiredConnections().build();
    }

    @Bean
    public RestTemplate restTemplateWithTrustStore(RestTemplateBuilder builder, CloseableHttpClient customHttpClient) {
        return builder
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(customHttpClient))
                .build();
    }
}

