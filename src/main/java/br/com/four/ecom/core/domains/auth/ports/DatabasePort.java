package br.com.four.ecom.core.domains.auth.ports;

import br.com.four.ecom.core.domains.auth.models.UserDataModel;

import java.util.Optional;

public interface DatabasePort {
    Optional<UserDataModel> getUserInfoById(String id);
}
