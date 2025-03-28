package smartin.platform.task.container;

import smartin.platform.task.TaskBuilder;
import smartin.platform.task.TaskConfigBuilder;
import smartin.platform.task.TaskParamsBuilder;

public class TaskContainerRegistry {

  private TaskContainer taskContainer;

  public TaskContainerRegistry() {
    taskContainer = TaskContainer.createInstance();
  }

  public void setTaskParamsBuilder(TaskParamsBuilder taskParamsBuilder) {
    taskContainer.setTaskParamsBuilder(taskParamsBuilder);
  }

  public void setTaskConfigBuilder(TaskConfigBuilder taskConfigBuilder) {
    taskContainer.setTaskConfigBuilder(taskConfigBuilder);
  }

  public void setTaskBuilder(TaskBuilder taskBuilder) {
    taskContainer.setTaskBuilder(taskBuilder);
  }

}
