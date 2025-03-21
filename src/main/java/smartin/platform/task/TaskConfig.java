package smartin.platform.task;

import smartin.platform.task.exception.TaskConfigurationException;

public interface TaskConfig {

  public String getId();

  public String getType();

  public TaskConfigValue getConfigParam(String key) throws TaskConfigurationException;
}
