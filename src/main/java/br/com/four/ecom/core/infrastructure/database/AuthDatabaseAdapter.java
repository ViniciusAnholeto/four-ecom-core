package br.com.four.ecom.core.infrastructure.database;

import br.com.four.ecom.core.domains.auth.enums.RoleNameEnum;
import br.com.four.ecom.core.domains.auth.models.UserDataModel;
import br.com.four.ecom.core.domains.auth.ports.DatabasePort;
import br.com.four.ecom.core.infrastructure.database.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthDatabaseAdapter implements DatabasePort {

    private final UsersRepository usersRepository;

    @Override
    public Optional<UserDataModel> getUserInfoById(String id) {
        log.info("Fetching user info for ID: {}", id);

        return usersRepository.findById(id)
                .map(user -> UserDataModel.builder()
                        .userId(user.getUserId())
                        .roleName(RoleNameEnum.valueOf(user.getRoleName()))
                        .build());
    }
}
