package smartin.platform.task.impl;

import static smartin.platform.task.contants.TaskConstants.KEY_ID;
import static smartin.platform.task.contants.TaskConstants.KEY_TASK_CONFIG_PARAMS;
import static smartin.platform.task.contants.TaskConstants.KEY_TYPE;

import java.util.Map;
import smartin.platform.task.TaskConfig;
import smartin.platform.task.TaskConfigValue;

public class TaskConfigImpl implements TaskConfig {

  private String id;

  private String type;

  private Map<String, TaskConfigValue> params;

  public TaskConfigImpl(Map<String, ?> configMap) {
    this.id = configMap.get(KEY_ID).toString();
    this.type = configMap.get(KEY_TYPE).toString();
    this.params = (Map<String, TaskConfigValue>) configMap.get(KEY_TASK_CONFIG_PARAMS);
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public TaskConfigValue getConfigParam(String key) {

    if (!params.containsKey(key)) {
      throw new IllegalArgumentException();
    }

    return params.get(key);
  }

}
