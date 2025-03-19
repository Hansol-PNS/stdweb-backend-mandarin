package smartin.platform.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import smartin.platform.task.impl.GreetingTask;
import smartin.platform.task.impl.TaskBuilderImpl;

public class TaskBuilderTest {

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

    TaskBuilder taskBuilder = new TaskBuilderImpl();
    Task resultTask = taskBuilder.buildTask(taskConfig);

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