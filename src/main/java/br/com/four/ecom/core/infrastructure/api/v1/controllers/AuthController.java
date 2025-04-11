package br.com.four.ecom.core.infrastructure.api.v1.controllers;

import br.com.four.ecom.core.domains.auth.resource.GenerateToken;
import br.com.four.ecom.core.infrastructure.api.v1.request.UserDataRequest;
import br.com.four.ecom.core.infrastructure.api.v1.swaggers.AuthControllerDoc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ecom/v1")
public class AuthController implements AuthControllerDoc {

    private final GenerateToken generateToken;

    @Override
    @PostMapping("/auth")
    public String auth(@RequestBody UserDataRequest userDataRequest) {

        log.info("Generating token for user: {}", userDataRequest.getUserId());

        return generateToken.execute(userDataRequest.toInput());
    }
}
