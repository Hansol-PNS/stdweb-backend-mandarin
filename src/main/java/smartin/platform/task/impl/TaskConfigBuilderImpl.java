package smartin.platform.task.impl;

import static smartin.platform.task.contants.TaskConstants.KEY_ID;
import static smartin.platform.task.contants.TaskConstants.KEY_TYPE;

import java.util.Map;
import smartin.platform.task.TaskConfig;
import smartin.platform.task.TaskConfigBuilder;
import smartin.platform.task.exception.TaskConfigurationException;

public class TaskConfigBuilderImpl implements TaskConfigBuilder {

  @Override
  public TaskConfig buildTaskConfig(Map<String, Object> configMap) throws TaskConfigurationException {
    if (configMap.get(KEY_ID) == null || configMap.get(KEY_ID).toString().isEmpty()) {
      throw new TaskConfigurationException("'id' cannot be null or empty", new IllegalArgumentException());
    }

    if (configMap.get(KEY_TYPE) == null || configMap.get(KEY_TYPE).toString().isEmpty()) {
      throw new TaskConfigurationException("'type' cannot be null or empty", new IllegalArgumentException());
    }

    return new TaskConfigImpl(configMap);
  }
}
