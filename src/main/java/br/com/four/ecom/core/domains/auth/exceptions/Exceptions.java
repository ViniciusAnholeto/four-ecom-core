package br.com.four.ecom.core.domains.auth.exceptions;

import br.com.four.ecom.core.utils.EcomException.SynchronousException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Exceptions {

    public static class UnauthorizedUserException extends SynchronousException {

        public UnauthorizedUserException(String userId, String action) {
            super("ECOM-DA-001", String.format("User id: %s doest have permission to execute %s.", userId, action));
        }
    }

}
