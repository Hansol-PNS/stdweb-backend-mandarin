package smartin.platform.task.impl;

import smartin.platform.task.TaskBuilder;
import smartin.platform.task.TaskConfigBuilder;
import smartin.platform.task.TaskParamsBuilder;

public class TaskContainer {

  // 정적 필드
  private static TaskContainer taskContainer;

  private final TaskParamsBuilder taskParamsBuilder;
  private final TaskConfigBuilder taskConfigBuilder;
  private final TaskBuilder taskBuilder;

  // @NOTE 빈 생성자 : default Builder를 담는다
  private TaskContainer() {
    this.taskParamsBuilder = new TaskParamsBuilderImpl();
    this.taskConfigBuilder = new TaskConfigBuilderImpl();
    this.taskBuilder = new TaskBuilderImpl();
  }

  // @NOTE Builder 포함 생성자
  private TaskContainer(TaskParamsBuilder taskParamsBuilder, TaskConfigBuilder taskConfigBuilder, TaskBuilder taskBuilder) {
    this.taskParamsBuilder = taskParamsBuilder;
    this.taskConfigBuilder = taskConfigBuilder;
    this.taskBuilder = taskBuilder;
  }

  // @NOTE 정적 메소드(싱글톤) : Default Builder 활용
  public static TaskContainer getInstance() {
    if (taskContainer == null) {
      taskContainer = new TaskContainer();
    }
    return taskContainer;
  }

  // @NOTE 정적 메소드(싱글톤) : 외부주입 Builder 활용
  public static TaskContainer getInstance(TaskParamsBuilder taskParamsBuilder, TaskConfigBuilder taskConfigBuilder, TaskBuilder taskBuilder) {
    if (taskContainer == null) {
      taskContainer = new TaskContainer(taskParamsBuilder, taskConfigBuilder, taskBuilder);
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
}
