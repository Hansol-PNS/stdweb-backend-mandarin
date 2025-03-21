package smartin.platform.task.impl;

import java.util.Map;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import smartin.platform.task.TaskParams;
import smartin.platform.task.TaskParamsBuilder;
import smartin.platform.task.contants.TaskConstants;
import smartin.platform.task.registry.TaskParamsRegistry;

@Slf4j
@NoArgsConstructor
public class TaskParamsBuilderImpl implements TaskParamsBuilder {

  @Override
  public TaskParams buildTaskParams(Map params) throws NullPointerException, ClassNotFoundException, IllegalArgumentException {

    // 예외조건-1. params null 이거나 비어 있을 경우
    if (params == null || params.isEmpty()) {
      throw new NullPointerException("params is null");
    }

    String className = (String) params.get(TaskConstants.KEY_TYPE);

    // 예외조건-2. null 이거나 비어 있을 경우
    if (className == null || className.isBlank()) {
      throw new IllegalArgumentException("Class name is missing in params");
    }

    try {
      return TaskParamsRegistry.get(className, params);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error creating TaskParams for type: " + className, e);
    }

  }
}
