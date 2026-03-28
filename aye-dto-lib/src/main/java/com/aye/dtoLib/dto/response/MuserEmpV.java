package com.aye.dtoLib.dto.response;//package com.aye.RestfulServer.model.user;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "M_USER_EMP_V")
//public class MuserEmpV {
//
/// /	private static final long serialVersionUID = 1L;
//
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
//    @Column(name = "USER_ID", nullable = false)
//    private Long userId;
//
//    @Column(name = "USER_NAME", nullable = false)
//    private String userName;
//
//    @Column(name = "EMP_ID")
//    private Long empId;
//
//    @Column(name = "FIRST_NAME")
//    private String firstName;
//    @Column(name = "LAST_NAME")
//    private String lastName;
//
//    @Column(name = "LOOKUP_USER_ID")
//    private Long lookupUserId;
//
//    @Column(name = "SALESREP_ID")
//    private Long salesrepId;
//
//    @Column(name = "N_USER_ID")
//    private Long nUserId;
//
//    @Column(name = "ORG_ID")
//    private Long orgId;
//
//    @Column(name = "NOT_IN_APPROVAL_PROCESS")
//    private Long notInApprovalProcess;
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public Long getEmpId() {
//        return empId;
//    }
//
//    public void setEmpId(Long empId) {
//        this.empId = empId;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public Long getLookupUserId() {
//        return lookupUserId;
//    }
//
//    public void setLookupUserId(Long lookupUserId) {
//        this.lookupUserId = lookupUserId;
//    }
//
//    public Long getSalesrepId() {
//        return salesrepId;
//    }
//
//    public void setSalesrepId(Long salesrepId) {
//        this.salesrepId = salesrepId;
//    }
//
//    public Long getnUserId() {
//        return nUserId;
//    }
//
//    public void setnUserId(Long nUserId) {
//        this.nUserId = nUserId;
//    }
//
//    public Long getOrgId() {
//        return orgId;
//    }
//
//    public void setOrgId(Long orgId) {
//        this.orgId = orgId;
//    }
//
//    public Long getNotInApprovalProcess() {
//        return notInApprovalProcess;
//    }
//
//    public void setNotInApprovalProcess(Long notInApprovalProcess) {
//        this.notInApprovalProcess = notInApprovalProcess;
//    }
//}
