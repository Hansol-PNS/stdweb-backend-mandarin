package smartin.platform.task;

import java.util.Map;

public interface TaskConfigBuilder {

  public TaskConfig buildTaskConfig(Map<String, Object> configMap);
}
