package smartin.platform.task;

import smartin.platform.task.exception.TaskExecutionException;

public interface Task {

  //  public Task create(TaskConfig taskConfig);
  public TaskResult execute(TaskParams taskParams) throws TaskExecutionException;

}
