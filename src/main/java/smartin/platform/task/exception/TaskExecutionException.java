package smartin.platform.task.exception;

public class TaskExecutionException extends Exception {

  public TaskExecutionException(String message, Exception parent) {
    super(message, parent);
  }

}
