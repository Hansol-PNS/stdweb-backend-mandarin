package smartin.platform.task.impl;

import smartin.platform.task.Task;
import smartin.platform.task.TaskConfig;
import smartin.platform.task.exception.TaskConfigurationException;

public class TaskBuilderImpl extends TaskBuilderAbstract {

  @Override
  public Task generate(String type, TaskConfig config) throws TaskConfigurationException {
    switch (type) {
      case "GreetingTask":
        return new GreetingTask(config);
      default:
        throw new TaskConfigurationException("No matching type found: " + type, new IllegalArgumentException());
    }
  }

  @Override
  public Task build(Task task, TaskConfig config) throws TaskConfigurationException {
    return task;
  }
}
