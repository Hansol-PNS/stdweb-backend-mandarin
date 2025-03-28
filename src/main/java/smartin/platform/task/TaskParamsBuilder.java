package smartin.platform.task;

import java.util.Map;
import smartin.platform.task.exception.TaskConfigurationException;

public interface TaskParamsBuilder {

  TaskParams buildTaskParams(Map params) throws TaskConfigurationException;

}
