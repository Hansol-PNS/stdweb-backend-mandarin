package smartin.platform.task;

public class TaskContainer {

  private TaskBuilder taskBuilder;

  public TaskBuilder getTaskBuilder() {
    return taskBuilder;
  }

  public void setTaskBuilder(TaskBuilder taskBuilder) {
    this.taskBuilder = taskBuilder;
  }

  private TaskContainer() {
  }

  private static class Helper {

    private static final TaskContainer INSTANCE = new TaskContainer();
  }

  public static TaskContainer getInstance() {
    return Helper.INSTANCE;
  }
}
