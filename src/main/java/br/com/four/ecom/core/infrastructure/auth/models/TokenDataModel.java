package br.com.four.ecom.core.infrastructure.auth.models;

import br.com.four.ecom.core.domains.auth.enums.RoleNameEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDataModel {
  private String userId;
  private RoleNameEnum roleName;
}
