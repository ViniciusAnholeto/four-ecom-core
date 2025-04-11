package br.com.four.ecom.core.infrastructure.auth;

import br.com.four.ecom.core.domains.auth.enums.RoleNameEnum;
import br.com.four.ecom.core.domains.auth.models.UserDataModel;
import br.com.four.ecom.core.infrastructure.auth.models.TokenDataModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String ROLE_NAME = "roleName";

    @Value("${auth.jwt-expiration-ms}")
    private Long expirationMs;
    @Value("${auth.jwt-secret}")
    private String jwtKey;

    public String buildToken(UserDataModel userData) {
        final Instant now = Instant.now();
        return JWT.create()
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(expirationMs))
                .withSubject(userData.getUserId())
                .withClaim(ROLE_NAME, userData.getRoleName().name())
                .sign(Algorithm.HMAC256(jwtKey));
    }

    public TokenDataModel parseToken(String token) {
        final DecodedJWT jwt = JWT.decode(token);
        return TokenDataModel.builder()
                .userId(jwt.getSubject())
                .roleName(RoleNameEnum.valueOf(jwt.getClaim(ROLE_NAME).asString()))
                .build();
    }

    public Map<String, Object> parseAuthorization(String authorization) {
        final DecodedJWT jwt = JWT.decode(authorization.replaceFirst("Bearer ", ""));
        return jwt.getClaim("frwk").asMap();
    }
}
