package smartin.platform.task;

import smartin.platform.task.exception.TaskConfigurationException;

public interface TaskBuilder {

  public Task buildTask(TaskConfig taskConfig) throws TaskConfigurationException;
}
