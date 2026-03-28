package com.aye.dtoLib.dto.response.report;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class RptTarVSacvDtlResDto {


    private Integer id;


    private Integer orgId;


    private String itmFamily;


    private String itmClass;


    private Integer empId;


    private Integer custAcctId;


    private String partyName;


    private Integer nationalId;


    private String national;


    private Integer divisionId;


    private String division;


    private Integer zoneId;


    private String zone;


    private Integer areaId;


    private String area;


    private String month;


    private Integer year;


    private BigDecimal acviv;


    private BigDecimal yearlyTarget;


    private BigDecimal monthlyTarget;


    private String hirchyType;


    private Integer prsnHierarchy;


    private String hierarchyValue;


    private String dataType;


    private String keyVal;


    public RptTarVSacvDtlResDto(Integer id, Integer orgId, String itmFamily, String itmClass, Integer empId,
                                Integer custAcctId, String partyName, Integer nationalId, String national, Integer divisionId,
                                String division, Integer zoneId, String zone, Integer areaId, String area, String month, Integer year,
                                BigDecimal acviv, BigDecimal yearlyTarget, BigDecimal monthlyTarget, String hirchyType,
                                Integer prsnHierarchy, String hierarchyValue, String dataType, String keyVal) {
        this.id = id;
        this.orgId = orgId;
        this.itmFamily = itmFamily;
        this.itmClass = itmClass;
        this.empId = empId;
        this.custAcctId = custAcctId;
        this.partyName = partyName;
        this.nationalId = nationalId;
        this.national = national;
        this.divisionId = divisionId;
        this.division = division;
        this.zoneId = zoneId;
        this.zone = zone;
        this.areaId = areaId;
        this.area = area;
        this.month = month;
        this.year = year;
        this.acviv = acviv;
        this.yearlyTarget = yearlyTarget;
        this.monthlyTarget = monthlyTarget;
        this.hirchyType = hirchyType;
        this.prsnHierarchy = prsnHierarchy;
        this.hierarchyValue = hierarchyValue;
        this.dataType = dataType;
        this.keyVal = keyVal;

    }

}
