//package com.aye.backendservice.config;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@ConfigurationProperties(prefix = "spring.datasource.ebs.jpa")
//@Configuration
//@Getter
//@Setter
//public class EbsJpaProperties {
//    private boolean showSql = false;
//    private boolean generateDdl = false;
//    private Hibernate hibernate = new Hibernate();
//    private Map<String, String> properties = new HashMap<>();
//
//    @Getter
//    @Setter
//    public static class Hibernate {
//        private String ddlAuto = "validate";
//        private Naming naming = new Naming();
//
//        @Getter
//        @Setter
//        public static class Naming {
//            private String physicalStrategy;
//        }
//    }
//}
