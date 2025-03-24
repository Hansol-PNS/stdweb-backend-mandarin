package smartin.platform.task.impl;

import smartin.platform.task.Task;
import smartin.platform.task.TaskConfig;
import smartin.platform.task.TaskParams;
import smartin.platform.task.TaskResult;
import smartin.platform.task.exception.TaskExecutionException;

public abstract class TaskAbstract implements Task {

  private String id;
  private String type;

  protected TaskAbstract(TaskConfig config) {
    this.id = config.getId();
    this.type = config.getType();
  }

  protected String getId() {
    return this.id;
  }

  protected String getType() {
    return this.type;
  }

  @Override
  public TaskResult execute(TaskParams taskParams) throws TaskExecutionException {
    if (taskParams == null) {
      throw new TaskExecutionException(new IllegalArgumentException("TaskParams cannot be null"));
    }
    return run(taskParams);
  }

  protected abstract TaskResult run(TaskParams taskParams) throws TaskExecutionException;
}
