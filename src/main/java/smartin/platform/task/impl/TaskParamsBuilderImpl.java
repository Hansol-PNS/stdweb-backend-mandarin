package smartin.platform.task.impl;

import java.util.Map;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import smartin.platform.task.TaskParams;
import smartin.platform.task.exception.TaskConfigurationException;

@Slf4j
@NoArgsConstructor
public class TaskParamsBuilderImpl extends TaskParamsBuilderAbstract {

  @Override
  public TaskParams generate(String type, Map params) throws TaskConfigurationException {
    switch (type) {
      case "GreetingTaskParams":
        return new GreetingTaskParams(params);
      default:
        throw new TaskConfigurationException(new IllegalArgumentException("No matching type found: " + type));
    }
  }

  @Override
  public TaskParams build(TaskParams taskParams, Map params) throws TaskConfigurationException {
    return taskParams;
  }
}
