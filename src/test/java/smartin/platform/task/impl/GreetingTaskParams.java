package smartin.platform.task.impl;

import java.util.Map;
import smartin.platform.task.TaskResult;

public class GreetingTaskParams extends TaskParamsAbstract {

  public GreetingTaskParams(Map<String, ?> params) {
    super(params);
  }

  @Override
  public Object getData(String key) {
    if (key == null) {
      throw new NullPointerException("key is null");
    }
    return dataMap.get(key);
  }

  @Override
  public boolean hasKey(String key) {
    return dataMap.containsKey(key);
  }

  @Override
  public void addTaskResult(TaskResult taskResult) {

  }
}
