package com.aye.dtoLib.dto.response;//package com.aye.RestfulServer.model.user;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDate;
//import java.util.Date;
//
//
//@Setter
//@Getter
//@Entity
//@Table(name = "M_USER_FORM")
//public class MuserForm {
//
//    /*
//     * PAGE_ID NUMBER, DESCRIPTION VARCHAR2(240 BYTE), PSYSICAL_NAME VARCHAR2(240
//     * BYTE), PAGE_TYPE VARCHAR2(240 BYTE), APP_PAGE_ID NUMBER, APP_ID NUMBER,
//     * SORT_ORDER NUMBER
//     */
//
//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "HIBERNATE_SEQUENCE"
//    )
//    @SequenceGenerator(
//            name = "HIBERNATE_SEQUENCE",
//            sequenceName = "HIBERNATE_SEQUENCE",
//            allocationSize = 1
//    )
//    @Column(name = "PAGE_ID")
//    private Integer id;
//    @Column(name = "DESCRIPTION")
//    private String description;
//
//    @Column(name = "PSYSICAL_NAME")
//    private String psysicalName;
//
//    @Column(name = "PAGE_TYPE")
//    private String pageType;
//    @Column(name = "APP_PAGE_ID")
//    private Integer appPageId;
//
//    @Column(name = "APP_ID")
//    private Integer appId;
//
//    @Column(name = "SORT_ORDER")
//    private Integer shortOrder;
//
//    @Column(name = "FUNCTION")
//    private String function;
//
//
//    @Column(name = "CREATED_BY")
//    private Integer createdBy;
//    @Column(name = "LAST_UPDATED_BY")
//    private Integer lastUpdateBy;
//    @Column(name = "CREATION_DATE")
/// /	@Temporal(TemporalType.DATE)
//    private LocalDate creationDate;
//    @Column(name = "LAST_UPDATE_DATE")
//    @Temporal(TemporalType.DATE)
//    private Date lastUpdateDate;
//    @Column(name = "LAST_UPDATE_LOGIN")
//    private Integer lastUpdateLogin;
//
//}
