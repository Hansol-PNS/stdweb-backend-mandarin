package smartin.platform.task;

import java.util.Map;
import smartin.platform.task.exception.TaskConfigurationException;

public interface TaskConfigBuilder {

  public TaskConfig buildTaskConfig(Map<String, Object> configMap) throws TaskConfigurationException;
}
