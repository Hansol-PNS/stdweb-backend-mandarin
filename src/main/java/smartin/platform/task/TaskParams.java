package smartin.platform.task;

import java.util.Map;

public interface TaskParams {
  public TaskParams create(Map paramMap);
  public Object getData(String key);
  public boolean hasKey(String key);
  public void addTaskResult(TaskResult taskResult);
}
