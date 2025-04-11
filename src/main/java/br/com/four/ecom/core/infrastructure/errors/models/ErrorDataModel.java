package br.com.four.ecom.core.infrastructure.errors.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDataModel {

  private String field;
  private String message;
}
