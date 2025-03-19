package smartin.platform.task.impl;

import smartin.platform.task.Task;
import smartin.platform.task.TaskConfig;
import smartin.platform.task.TaskParams;
import smartin.platform.task.TaskResult;

public abstract class TaskAbstract implements Task {

  private String id;
  private String type;

  public TaskAbstract(TaskConfig config) {
    this.id = config.getId();
    this.type = config.getType();
  }

  public String getId() {
    return this.id;
  }

  public String getType() {
    return this.type;
  }

  @Override
  public TaskResult execute(TaskParams taskParams) {
    if (taskParams == null) {
      throw new IllegalArgumentException("TaskParams cannot be null");
    }
    return run(taskParams);
  }

  protected abstract TaskResult run(TaskParams taskParams);
}
