package br.com.four.ecom.core.domains.auth.usecases;

import br.com.four.ecom.core.domains.auth.input.UserDataInput;
import br.com.four.ecom.core.domains.auth.models.UserDataModel;
import br.com.four.ecom.core.domains.auth.ports.AuthPort;
import br.com.four.ecom.core.domains.auth.ports.DatabasePort;
import br.com.four.ecom.core.domains.auth.resource.GenerateToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenerateTokenImpl implements GenerateToken {

    private final AuthPort authPort;
    private final DatabasePort databasePort;

    @Override
    public String execute(UserDataInput userDataInput) {
        UserDataModel userInfo = databasePort.getUserInfoById(userDataInput.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return authPort.buildToken(userInfo);
    }
}
