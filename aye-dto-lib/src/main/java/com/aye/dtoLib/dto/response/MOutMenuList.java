package com.aye.dtoLib.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MOutMenuList {

    private String autoNumber;


    private String approvalPath;


    private String colorSchema;


    private String colorSchemaApp;


    private String colorSchemaButton;


    private Boolean passwordChanged;


    private List<OutMenuMain> outMenuList;

    public MOutMenuList() {


    }


    public MOutMenuList(String autoNumber, String approvalPath, String colorSchma, Boolean passwordChange,//BigDecimal passwordChange ,
                        String ColorSchemaApp, String ColorSchemaButton, List<OutMenuMain> outMenuList) {
        this.setOutMenuList(outMenuList);
        this.setAutoNumber(autoNumber);
        this.setApprovalPath(approvalPath);
        this.setColorSchema(colorSchma);
        this.setColorSchemaApp(ColorSchemaApp);
        this.setColorSchemaButton(ColorSchemaButton);
        this.setPasswordChanged(passwordChange);

    }


}
