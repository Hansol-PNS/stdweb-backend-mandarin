package smartin.platform.task.impl;

public class GreetingTaskResult extends TaskResultAbstract {

  public GreetingTaskResult(String id, String type) {
    super(id, type);
  }

  @Override
  public void make(boolean success, String message, Object data) {
    this.isSuccess = success;
    this.message = message;
    this.data = data;
  }
}
