package br.com.four.ecom.core.infrastructure.api.v1.request;

import br.com.four.ecom.core.domains.auth.input.UserDataInput;
import lombok.Data;

@Data
public class UserDataRequest {
    private String userId;
    private RoleNameEnum roleName;

    public UserDataInput toInput() {
        return UserDataInput.builder()
                .userId(userId)
                .roleName(roleName.toDomain())
                .build();
    }
}
