package smartin.platform.task.impl;

import java.util.Map;
import smartin.platform.task.TaskParams;
import smartin.platform.task.TaskParamsBuilder;
import smartin.platform.task.contants.TaskConstants;
import smartin.platform.task.exception.TaskConfigurationException;

public abstract class TaskParamsBuilderAbstract implements TaskParamsBuilder {

  @Override
  public TaskParams buildTaskParams(Map params) throws TaskConfigurationException {
    // 예외조건-1. params null 이거나 비어 있을 경우
    if (params == null || params.isEmpty()) {
      throw new TaskConfigurationException(new NullPointerException("params is null"));
    }

    String className = (String) params.get(TaskConstants.KEY_TYPE);

    // 예외조건-2. null 이거나 비어 있을 경우
    if (className == null || className.isBlank()) {
      throw new TaskConfigurationException(new IllegalArgumentException("Class name is missing in params"));
    }

    try {
      return build(generate(className, params), params);
    } catch (Exception e) {
      throw new TaskConfigurationException(new IllegalArgumentException("Error creating TaskParams for type: " + className, e));
    }
  }

  public abstract TaskParams generate(String type, Map params) throws TaskConfigurationException;

  public abstract TaskParams build(TaskParams taskParams, Map params) throws TaskConfigurationException;
}
