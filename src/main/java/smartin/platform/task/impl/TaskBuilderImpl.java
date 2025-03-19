package smartin.platform.task.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import smartin.platform.task.Task;
import smartin.platform.task.TaskBuilder;
import smartin.platform.task.TaskConfig;

public class TaskBuilderImpl implements TaskBuilder {

  @Override
  public Task buildTask(TaskConfig taskConfig) {

    try {
      if (taskConfig == null) {
        throw new NullPointerException("taskConfig is null");
      }

      String className = taskConfig.getType();

      if (Objects.isNull(className) || className.isEmpty()) {
        throw new IllegalArgumentException("Class name is missing in config");
      }

      Class<?> clazz = Class.forName(className);

      Constructor<?> constructor = clazz.getConstructor(TaskConfig.class);

      Object newInstance = constructor.newInstance(taskConfig);

      if (!(newInstance instanceof Task)) {
        throw new IllegalArgumentException("Object is Not instance of Task");
      }

      return (Task) newInstance;

    } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
