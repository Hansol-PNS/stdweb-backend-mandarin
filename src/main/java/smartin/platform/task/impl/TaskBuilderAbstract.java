package smartin.platform.task.impl;

import java.util.Objects;
import smartin.platform.task.Task;
import smartin.platform.task.TaskBuilder;
import smartin.platform.task.TaskConfig;
import smartin.platform.task.exception.TaskConfigurationException;

public abstract class TaskBuilderAbstract implements TaskBuilder {

  @Override
  public Task buildTask(TaskConfig config) throws TaskConfigurationException {

    if (config == null) {
      throw new TaskConfigurationException(new NullPointerException("taskConfig is null"));
    }

    String className = config.getType();

    if (Objects.isNull(className) || className.isEmpty()) {
      throw new TaskConfigurationException(new IllegalArgumentException("Task type missing in config"));
    }

    return build(generate(className, config), config);
  }

  public abstract Task generate(String type, TaskConfig config) throws TaskConfigurationException;

  public abstract Task build(Task task, TaskConfig config) throws TaskConfigurationException;
}
