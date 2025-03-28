package smartin.platform.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static smartin.platform.task.contants.TaskConstants.KEY_ID;
import static smartin.platform.task.contants.TaskConstants.KEY_TYPE;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smartin.platform.task.exception.TaskConfigurationException;
import smartin.platform.task.impl.GreetingTask;
import smartin.platform.task.impl.TaskBuilderAbstract;
import smartin.platform.task.impl.TaskConfigImpl;

public class TaskBuilderTest {

  @BeforeEach
  void init() {
    TaskContainer taskContainer = TaskContainer.getInstance();

    class TaskBuilderImplForTest extends TaskBuilderAbstract {

      @Override
      public Task generate(String type, TaskConfig config) throws TaskConfigurationException {
        switch (type) {
          case "GreetingTask":
            return new GreetingTask(config);
          default:
            throw new TaskConfigurationException("No matching type found: " + type, new IllegalArgumentException());
        }
      }

      @Override
      public Task build(Task task, TaskConfig config) throws TaskConfigurationException {
        return task;
      }
    }
    taskContainer.setTaskBuilder(new TaskBuilderImplForTest());
  }

  @Test
  void testBuildTask_WhenValidInput_ShouldCreateTaskSuccessfully() {

    Map<String, Object> configMap = new HashMap<>();
    configMap.put(KEY_ID, "id_task_1");
    configMap.put(KEY_TYPE, "GreetingTask");

    TaskConfig taskConfig = new TaskConfigImpl(configMap);
    TaskBuilder taskBuilder = TaskContainer.getInstance().getTaskBuilder();

    Task resultTask = assertDoesNotThrow(() -> taskBuilder.buildTask(taskConfig));

    if (resultTask instanceof GreetingTask greetingTask) {
      assertNotNull(greetingTask);
      assertEquals("id_task_1", GreetingTask.ForTest.getId(greetingTask));
      assertEquals(GreetingTask.class, resultTask.getClass());
    }

  }

  @Test
  void testBuildTask_WhenTaskConfigIsNull_ShouldThrowException() {
    TaskConfig taskConfig = null;

    TaskBuilder taskBuilder = TaskContainer.getInstance().getTaskBuilder();

    assertThrows(TaskConfigurationException.class, () -> {
      Task resultTask = taskBuilder.buildTask(taskConfig);
    });
  }

  @Test
  void testBuildTask_WhenInvalidType_ShouldThrowException() {
    Map<String, Object> configMap = new HashMap<>();
    configMap.put(KEY_ID, "id_task_1");
    configMap.put(KEY_TYPE, "NotTaskType");

    TaskConfig taskConfig = new TaskConfigImpl(configMap);

    TaskBuilder taskBuilder = TaskContainer.getInstance().getTaskBuilder();

    assertThrows(TaskConfigurationException.class, () -> {
      Task resultTask = taskBuilder.buildTask(taskConfig);
    });
  }
}