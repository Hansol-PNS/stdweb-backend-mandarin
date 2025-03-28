package smartin.platform.task.container;

import smartin.platform.task.TaskBuilder;
import smartin.platform.task.TaskConfigBuilder;
import smartin.platform.task.TaskParamsBuilder;

public class TaskContainer {

  // 정적 필드
  protected static TaskContainer taskContainer;

  private TaskParamsBuilder taskParamsBuilder;
  private TaskConfigBuilder taskConfigBuilder;
  private TaskBuilder taskBuilder;

  protected TaskContainer() {
  }

  // @NOTE 정적 메소드(싱글톤) : Default Builder 활용
  public static TaskContainer getInstance() {
    if (taskContainer == null) {
      taskContainer = new TaskContainer();
    }
    return taskContainer;
  }

  // @NOTE 정적 메소드(싱글톤) : 외부주입 Builder 활용
  static TaskContainer createInstance() {
    if (taskContainer == null) {
      taskContainer = new TaskContainer();
    }
    return taskContainer;
  }

  public TaskParamsBuilder getTaskParamsBuilder() {
    return this.taskParamsBuilder;
  }

  public TaskConfigBuilder getTaskConfigBuilder() {
    return taskConfigBuilder;
  }

  public TaskBuilder getTaskBuilder() {
    return this.taskBuilder;
  }

  void setTaskParamsBuilder(TaskParamsBuilder taskParamsBuilder) {
    this.taskParamsBuilder = taskParamsBuilder;
  }

  void setTaskConfigBuilder(TaskConfigBuilder taskConfigBuilder) {
    this.taskConfigBuilder = taskConfigBuilder;
  }

  void setTaskBuilder(TaskBuilder taskBuilder) {
    this.taskBuilder = taskBuilder;
  }
}
