package com.product.system.entity;

/**
 * Created by Sonikb on 21.08.2017.
 */
public enum UserRoleType {
    USER("USER"),
    ADMIN("ADMIN");

    private String UserRoleType;

    UserRoleType(String userRoleType) {
        UserRoleType = userRoleType;
    }

    public String getUserRoleType() {
        return UserRoleType;
    }
}
