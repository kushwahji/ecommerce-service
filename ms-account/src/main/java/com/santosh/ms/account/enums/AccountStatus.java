package com.santosh.ms.account.enums;


/**
 * @author santosh.kushwah
 * @since 12-12-2021
 * 
 */
public enum AccountStatus {

    ACTIVE(true), INACTIVE(false),BLOCKED(false);

    public boolean isValue() {
        return value;
    }

    private final boolean value;
    AccountStatus (boolean value) {
        this.value = value;
    }
}
