package smartin.platform.task.impl;

import smartin.platform.task.TaskConfig;
import smartin.platform.task.TaskParams;
import smartin.platform.task.TaskResult;

public class TaskImpl extends TaskAbstract {

  public TaskImpl(TaskConfig taskConfig) {
    super(taskConfig.getId(), taskConfig.getType());
  }

  @Override
  protected TaskResult run(TaskParams taskParams) {
    return null;
  }
}
