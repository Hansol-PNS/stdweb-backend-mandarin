package smartin.platform.task.impl;

import smartin.platform.task.TaskConfig;
import smartin.platform.task.TaskParams;
import smartin.platform.task.TaskResult;
import smartin.platform.task.exception.TaskExecutionException;

public class GreetingTask extends TaskAbstract {

  private static final String MESSAGE_FORMAT = "Hello World, %s";

  public GreetingTask(TaskConfig config) {
    super(config);
  }

  @Override
  protected TaskResult run(TaskParams taskParams) throws TaskExecutionException {
    String name = (String) taskParams.getData("name");
    if (name == null || name.isEmpty()) {
      throw new TaskExecutionException("name is empty", new IllegalArgumentException());
    }
    TaskResult result = new GreetingTaskResult(taskParams.getId(), taskParams.getType());
    result.makeResult(true, "", String.format(MESSAGE_FORMAT, name));
    return result;
  }

  public String getId() {
    return super.getId();
  }

  public String getType() {
    return super.getType();
  }

}
