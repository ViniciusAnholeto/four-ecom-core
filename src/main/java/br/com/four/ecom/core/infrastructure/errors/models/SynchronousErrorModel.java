package br.com.four.ecom.core.infrastructure.errors.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SynchronousErrorModel {

  private String code;
  private String path;
  private String httpStatus;
  private LocalDateTime timestamp;
  private String message;
  private Set<ErrorDataModel> errors;
}
