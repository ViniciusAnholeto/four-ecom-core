package br.com.four.ecom.core.infrastructure.auth;

import br.com.four.ecom.core.domains.auth.models.UserDataModel;
import br.com.four.ecom.core.domains.auth.ports.AuthPort;
import br.com.four.ecom.core.infrastructure.auth.models.TokenDataModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthAdapter implements AuthPort {

    private final AuthService authService;

    @Override
    public String buildToken(UserDataModel model) {
        log.info("Token ecom-core generated");
        return authService.buildToken(model);
    }

    @Override
    public TokenDataModel parseToken(String accessToken) {
        log.info("Token ecom-core broken");
        return authService.parseToken(accessToken);
    }

    @Override
    public Map<String, Object> parseAuthorization(String authorization) {
        log.info("Token Authorization broken");
        return authService.parseAuthorization(authorization);
    }
}
