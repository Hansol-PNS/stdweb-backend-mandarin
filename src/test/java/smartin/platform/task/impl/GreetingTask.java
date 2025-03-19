package smartin.platform.task.impl;

import smartin.platform.task.TaskConfig;
import smartin.platform.task.TaskParams;
import smartin.platform.task.TaskResult;

public class GreetingTask extends TaskAbstract {

  private static final String MESSAGE_FORMAT = "Hello World, %s";

  public GreetingTask(TaskConfig config) {
    super(config.getId(), config.getType());
  }

  @Override
  protected TaskResult run(TaskParams taskParams) {
    String name = (String) taskParams.getData("name");
    TaskResult result = new GreetingTaskResult(taskParams.getId(), taskParams.getType());
    result.makeResult(true, "", String.format(MESSAGE_FORMAT, name));
    return result;
  }
}
