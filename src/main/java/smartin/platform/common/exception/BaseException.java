package smartin.platform.common.exception;

public class BaseException extends Exception {

  private Exception parent;

  public BaseException(Exception parent) {
    this.parent = parent;
  }

  @Override
  public String getMessage() {
    return parent.getMessage();
  }

  @Override
  public synchronized Throwable getCause() {
    return parent.getCause();
  }

  @Override
  public synchronized Throwable initCause(Throwable cause) {
    parent.initCause(cause);
    return this;
  }

  public Exception getParent() {
    return parent;
  }
}