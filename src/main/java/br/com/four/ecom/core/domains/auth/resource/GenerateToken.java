package br.com.four.ecom.core.domains.auth.resource;

import br.com.four.ecom.core.domains.auth.input.UserDataInput;

public interface GenerateToken {
    String execute(UserDataInput userDataInput);
}
