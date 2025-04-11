package br.com.four.ecom.core.domains.auth.input;

import br.com.four.ecom.core.domains.auth.enums.RoleNameEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDataInput {
    private String userId;
    private RoleNameEnum roleName;
}
