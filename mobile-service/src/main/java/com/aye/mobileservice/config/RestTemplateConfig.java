package com.aye.mobileservice.config;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@Configuration
public class RestTemplateConfig {

    @Value("${server.ssl.trust-store}")
    private Resource trustStore;
    @Value("${server.ssl.trust-store-password}")
    private String trustStorePassword;
    @Value("${server.ssl.key-store}")
    private Resource keyStore;
    @Value("${server.ssl.key-store-password}")
    private String keyStorePassword;

    @Bean
    public RestTemplate restTemplateWithTrustStore(RestTemplateBuilder builder) throws Exception {
        SSLContext sslContext = SSLContexts.custom()
                .loadKeyMaterial(keyStore.getURL(), keyStorePassword.toCharArray(), keyStorePassword.toCharArray())
                .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
                .build();

        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(socketFactory)   // ✅ now works
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return builder.requestFactory(() -> requestFactory).build();
    }
}
