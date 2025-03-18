package smartin.platform.task.impl;

import smartin.platform.task.Task;
import smartin.platform.task.TaskParams;
import smartin.platform.task.TaskResult;

public abstract class TaskAbstract implements Task {

  private String id;
  private String type;

  public TaskAbstract(String id, String type) {
    this.id = id;
    this.type = type;
  }

  public String getId() {
    return this.id;
  }

  public String getType() {
    return this.type;
  }

  @Override
  public TaskResult execute(TaskParams taskParams) {
    return run(taskParams);
  }

  protected abstract TaskResult run(TaskParams taskParams);
}
