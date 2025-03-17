package smartin.platform.task;

public interface Task {
//  public Task create(TaskConfig taskConfig);
  public TaskResult execute(TaskParams taskParams);

}
