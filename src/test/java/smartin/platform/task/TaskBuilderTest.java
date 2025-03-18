package smartin.platform.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import smartin.platform.task.impl.TaskAbstract;

public class TaskBuilderTest {

  static class GreetingTask extends TaskAbstract {

    public GreetingTask(TaskConfig config) {
      super(config.getId(), config.getType());
    }

    @Override
    protected TaskResult run(TaskParams taskParams) {
      return null;
    }
  }

  static class TaskBuilderImpl implements TaskBuilder {

    @Override
    public Task buildTask(TaskConfig taskConfig) {
      if ("GreetingTask".equals(taskConfig.getType())) {
        return new GreetingTask(taskConfig);
      }

      throw new IllegalArgumentException();
    }
  }

  static class TaskConfigImpl implements TaskConfig {

    @Override
    public String getId() {
      return "id_task_1";
    }

    @Override
    public String getType() {
      return "GreetingTask";
    }

    @Override
    public TaskConfigValue getConfigParam(String key) {
      return null;
    }
  }


  static class InvalidTaskConfigImpl implements TaskConfig {

    @Override
    public String getId() {
      return "id_task_1";
    }

    @Override
    public String getType() {
      return "NotTaskType";
    }

    @Override
    public TaskConfigValue getConfigParam(String key) {
      return null;
    }
  }


  @Test
  void testBuildTask_WhenValidInput_ShouldCreateTaskSuccessfully() {
    TaskConfig taskConfig = new TaskConfigImpl();

    TaskBuilder taskBuilderImpl = new TaskBuilderImpl();
    Task resultTask = taskBuilderImpl.buildTask(taskConfig);

    System.out.println("resultTask.getClass() = " + resultTask.getClass());

    if (resultTask instanceof GreetingTask greetingTask) {
      assertNotNull(greetingTask);
      assertEquals("id_task_1", greetingTask.getId());
      assertEquals(GreetingTask.class, resultTask.getClass());
    }
  }

  @Test
  void testBuildTask_WhenTaskConfigIsNull_ShouldThrowException() {
    TaskConfig taskConfig = null;

    TaskBuilder taskBuilderImpl = new TaskBuilderImpl();

    assertThrows(NullPointerException.class, () -> {
      Task resultTask = taskBuilderImpl.buildTask(taskConfig);
    });
  }

  @Test
  void testBuildTask_WhenInvalidType_ShouldThrowException() {
    TaskConfig taskConfig = new InvalidTaskConfigImpl();

    TaskBuilder taskBuilderImpl = new TaskBuilderImpl();

    assertThrows(IllegalArgumentException.class, () -> {
      Task resultTask = taskBuilderImpl.buildTask(taskConfig);
    });
  }
}