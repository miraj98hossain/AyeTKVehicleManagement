package com.aye.backendservice.config;


//@Configuration
//public class RestTemplateConfig {
//
//    @Value("${server.ssl.trust-store}")
//    private Resource trustStore;
//
//    @Value("${server.ssl.trust-store-password}")
//    private String trustStorePassword;
//
//    @Bean
//    public CloseableHttpClient customHttpClient() throws IOException, GeneralSecurityException {
//
//        SSLContext sslContext = SSLContexts.custom()
//                .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
//                .build();
//
//        final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
//                .setSslContext(sslContext)
//                .build();
//
//        final HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
//                .setSSLSocketFactory(sslSocketFactory)
//                .build();
//
//        return HttpClients.custom()
//                .setConnectionManager(cm)
//                .evictExpiredConnections().build();
//    }
//
//    @Bean
//    public RestTemplate restTemplateWithTrustStore(RestTemplateBuilder builder, CloseableHttpClient customHttpClient) {
//        return builder
//                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(customHttpClient))
//                .build();
//    }
//}

