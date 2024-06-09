package com.nicolas.helpdesk.domain.enums;

public enum Profile {

    ADMIN(0, "ROLE_ADMIN"), CLIENT(1, "ROLE_CLIENT"), TECHNICIAN(2, "ROLE_TECHNICIAN");

    private Integer code;
    private String description;

    Profile(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Profile toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Profile x : Profile.values()) {
            if (cod.equals(x.getCode())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid profile!");
    }

}
