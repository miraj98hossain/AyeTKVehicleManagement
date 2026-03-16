package com.aye.commonlib.dto;

public class RegularData {
    public enum OnlineCollectionStstus {
        R,
        P,
        C,
        A,
        N,
        F
    }

    public enum OrderStstus {
        R,
        P,
        C,
        A,
        N,
        F
    }

    public enum ScheduleStstus {
        R,
        P,
        C,
        A,
        N,
        F
    }

    public enum ContractStstus {
        R,
        P,
        C,
        A,
        N,
        F,
        FC
    }

    public enum ResourceStatus {
        N("New"),
        A("Approve"),
        C("Cancel");

        private String displayName;

        ResourceStatus(String displayName) {
            this.displayName = displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

    }
}