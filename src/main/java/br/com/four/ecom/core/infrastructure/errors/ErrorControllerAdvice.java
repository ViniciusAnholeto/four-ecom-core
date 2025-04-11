package br.com.four.ecom.core.infrastructure.errors;

import br.com.four.ecom.core.domains.auth.exceptions.Exceptions.UnauthorizedUserException;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions;
import br.com.four.ecom.core.domains.products.exceptions.Exceptions.ProductNotFoundException;
import br.com.four.ecom.core.infrastructure.errors.models.ErrorDataModel;
import br.com.four.ecom.core.infrastructure.errors.models.SynchronousErrorModel;
import br.com.four.ecom.core.utils.EcomException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintDeclarationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    SynchronousErrorModel handleProductNotFound(final ProductNotFoundException ex,
                               final HttpServletRequest request) {
        final SynchronousErrorModel error = SynchronousErrorModel.builder().code(ex.getCode())
                .path(request.getServletPath()).httpStatus(HttpStatus.NOT_FOUND.toString())
                .timestamp(LocalDateTime.now()).message(ex.getMessage()).build();

        log.error("Product Not Found: '{}'", ExceptionUtils.getStackTrace(ex));

        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exceptions.OrderNotFoundException.class)
    SynchronousErrorModel handleOrderNotFound(final ProductNotFoundException ex,
                               final HttpServletRequest request) {
        final SynchronousErrorModel error = SynchronousErrorModel.builder().code(ex.getCode())
                .path(request.getServletPath()).httpStatus(HttpStatus.NOT_FOUND.toString())
                .timestamp(LocalDateTime.now()).message(ex.getMessage()).build();

        log.error("Order Not Found: '{}'", ExceptionUtils.getStackTrace(ex));

        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exceptions.OrderNotFoundForCustomerIdException.class)
    SynchronousErrorModel handleOrderNotFoundForCustomer(final ProductNotFoundException ex,
                               final HttpServletRequest request) {
        final SynchronousErrorModel error = SynchronousErrorModel.builder().code(ex.getCode())
                .path(request.getServletPath()).httpStatus(HttpStatus.NOT_FOUND.toString())
                .timestamp(LocalDateTime.now()).message(ex.getMessage()).build();

        log.error("Order Not Found for customer: '{}'", ExceptionUtils.getStackTrace(ex));

        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exceptions.ProductNotFoundException.class)
    SynchronousErrorModel handleNotFoundForOrder(final ProductNotFoundException ex,
                               final HttpServletRequest request) {
        final SynchronousErrorModel error = SynchronousErrorModel.builder().code(ex.getCode())
                .path(request.getServletPath()).httpStatus(HttpStatus.NOT_FOUND.toString())
                .timestamp(LocalDateTime.now()).message(ex.getMessage()).build();

        log.error("Product Not Found for Order: '{}'", ExceptionUtils.getStackTrace(ex));

        return error;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedUserException.class)
    SynchronousErrorModel handleUserUnauthorized(final ProductNotFoundException ex,
                               final HttpServletRequest request) {
        final SynchronousErrorModel error = SynchronousErrorModel.builder().code(ex.getCode())
                .path(request.getServletPath()).httpStatus(HttpStatus.UNAUTHORIZED.toString())
                .timestamp(LocalDateTime.now()).message(ex.getMessage()).build();

        log.error("User unauthorized: '{}'", ExceptionUtils.getStackTrace(ex));

        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    SynchronousErrorModel handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                       final HttpServletRequest request) {
        final Set<ErrorDataModel> errors = ex
                .getBindingResult().getFieldErrors().stream().map(error -> ErrorDataModel.builder()
                        .field(error.getField()).message(error.getDefaultMessage()).build())
                .collect(Collectors.toSet());

        final SynchronousErrorModel errorModel = SynchronousErrorModel.builder().code("ECOM-DG-001")
                .path(request.getServletPath()).httpStatus(HttpStatus.BAD_REQUEST.toString())
                .timestamp(LocalDateTime.now()).errors(errors).build();

        log.error("Method Argument Not Valid: '{}'", ExceptionUtils.getStackTrace(ex));

        return errorModel;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintDeclarationException.class)
    SynchronousErrorModel handleConstraintDeclaration(final ConstraintDeclarationException ex,
                                                      final HttpServletRequest request) {
        final SynchronousErrorModel error = SynchronousErrorModel.builder().code("ECOM-DG-002")
                .path(request.getServletPath()).httpStatus(HttpStatus.BAD_REQUEST.toString())
                .timestamp(LocalDateTime.now())
                .message(String.format("Error when assembling enum: %s", ex.getLocalizedMessage())).build();

        log.error("Constraint Declaration: '{}'", ExceptionUtils.getStackTrace(ex));

        return error;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(IllegalArgumentException.class)
    SynchronousErrorModel handlerIllegalArgument(final IllegalArgumentException ex,
                                                 final HttpServletRequest request) {
        final SynchronousErrorModel error = SynchronousErrorModel.builder().code("ECOM-DG-003")
                .path(request.getServletPath()).httpStatus(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .timestamp(LocalDateTime.now()).message(ex.getMessage()).build();

        log.error("Illegal argument: '{}'", ExceptionUtils.getStackTrace(ex));

        return error;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(Exception.class)
    SynchronousErrorModel handlerException(final Exception ex, final HttpServletRequest request) {
        final SynchronousErrorModel error = SynchronousErrorModel.builder().code("ECOM-DG-999")
                .path(request.getServletPath()).httpStatus(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .timestamp(LocalDateTime.now()).message(ex.getMessage()).build();

        log.error("Exception: {}", ExceptionUtils.getStackTrace(ex));

        return error;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JWTDecodeException.class)
    SynchronousErrorModel handlerTokenDecode(final JWTDecodeException ex,
                                             final HttpServletRequest request) {
        final SynchronousErrorModel error = SynchronousErrorModel.builder().code("ECOM-DG-004")
                .path(request.getServletPath()).httpStatus(HttpStatus.UNAUTHORIZED.toString())
                .timestamp(LocalDateTime.now()).message(ex.getMessage()).build();

        log.error(String.format("Token Decode Exception: %s", ex.getMessage()));

        return error;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(EcomException.SynchronousException.class)
    SynchronousErrorModel handlerSessionCoreSynchronous(final EcomException.SynchronousException ex,
                                                        final HttpServletRequest request) {
        final SynchronousErrorModel error = SynchronousErrorModel.builder().code(ex.getCode())
                .path(request.getServletPath()).httpStatus(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .timestamp(LocalDateTime.now()).message(ex.getMessage()).build();

        log.error("Ecom Core Synchronous Exception: {}", ExceptionUtils.getStackTrace(ex));

        return error;
    }
}
