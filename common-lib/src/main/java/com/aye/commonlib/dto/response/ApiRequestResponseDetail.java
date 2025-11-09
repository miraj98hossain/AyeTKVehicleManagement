package com.aye.commonlib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@Builder
public class ApiRequestResponseDetail {

    private String objectTag;
    private ObjectType objectType;
    private Object object;
    private String mapperClass;


    public enum ObjectType {
        A("Array List"), O("Object"), P("Parameter"), PD("Pageable Data");
        final String displayField;

        ObjectType(String displayField) {
            this.displayField = displayField;
        }
    }


}
