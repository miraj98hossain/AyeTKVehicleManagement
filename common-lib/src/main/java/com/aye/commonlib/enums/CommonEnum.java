package com.aye.commonlib.enums;

import lombok.Getter;

public class CommonEnum {
    @Getter
    public enum UserType {
        CUS, EMP, HOEMP;
    }

    @Getter
    public enum RoleTypes {

        WEB, ANDROID
    }

    @Getter
    public enum MenuLevel {

        SYS,
        SETUP,
        TRNS;
    }

    @Getter
    public enum OrgType {
        BG("Business Group"),
        OU("Opareting Unit"),
        IO("Inventory Organization");


        private final String displayName;

        OrgType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    @Getter
    public enum TrnsType {
        OM, PO, HRMS, VHM;
    }
}
