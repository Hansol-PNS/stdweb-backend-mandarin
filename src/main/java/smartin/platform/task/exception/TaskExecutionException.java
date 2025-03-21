package smartin.platform.task.exception;

import smartin.platform.common.exception.BaseException;

public class TaskExecutionException extends BaseException {

  public TaskExecutionException(Exception parent) {
    super(parent);
  }

}
