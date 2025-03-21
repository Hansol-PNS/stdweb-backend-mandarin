package smartin.platform.task.impl;

import java.util.Objects;
import smartin.platform.task.Task;
import smartin.platform.task.TaskBuilder;
import smartin.platform.task.TaskConfig;
import smartin.platform.task.registry.TaskRegistry;

public class TaskBuilderImpl implements TaskBuilder {

  @Override
  public Task buildTask(TaskConfig taskConfig) {
    if (taskConfig == null) {
      throw new NullPointerException("taskConfig is null");
    }

    String className = taskConfig.getType();

    if (Objects.isNull(className) || className.isEmpty()) {
      throw new IllegalArgumentException("Class name is missing in config");
    }

    try {
      return TaskRegistry.get(className, taskConfig);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error creating Task for type: " + className, e);
    }
  }
}
