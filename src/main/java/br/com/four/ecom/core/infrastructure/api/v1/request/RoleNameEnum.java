package br.com.four.ecom.core.infrastructure.api.v1.request;

public enum RoleNameEnum {
    ADMIN,
    USER,
    GUEST;

    public br.com.four.ecom.core.domains.auth.enums.RoleNameEnum toDomain() {
        return br.com.four.ecom.core.domains.auth.enums.RoleNameEnum.valueOf(this.name());
    }
}
