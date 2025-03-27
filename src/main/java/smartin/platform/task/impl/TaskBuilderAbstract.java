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
      throw new TaskConfigurationException("taskConfig is null", new NullPointerException());
    }

    String className = config.getType();

    if (Objects.isNull(className) || className.isEmpty()) {
      throw new TaskConfigurationException("Task type missing in config", new IllegalArgumentException());
    }

    return build(generate(className, config), config);
  }

  public abstract Task generate(String type, TaskConfig config) throws TaskConfigurationException;

  public abstract Task build(Task task, TaskConfig config) throws TaskConfigurationException;
}
