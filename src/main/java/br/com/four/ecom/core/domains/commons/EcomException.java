package br.com.four.ecom.core.domains.commons;

import lombok.Getter;

@Getter
public abstract class EcomException extends RuntimeException {

  protected final String code;
  protected final String message;

  private EcomException(String code, String message, Throwable throwable) {
    super(message, throwable);
    this.code = code;
    this.message = message;
  }

  @Getter
  @SuppressWarnings("PMD.NonSerializableClass")
  public abstract static class AsynchronousException extends EcomException {

    private final String exceptionName;

    protected AsynchronousException(String code, String message,
                                    Throwable throwable, String exceptionName) {
      super(code, message, throwable);
      this.exceptionName = exceptionName;
    }
  }

  @Getter
  public abstract static class SynchronousException extends EcomException {

    protected SynchronousException(String code, String message, Throwable throwable) {
      super(code, message, throwable);
    }

    protected SynchronousException(String code, String message) {
      super(code, message, null);
    }
  }
}
