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
import smartin.platform.task.container.TaskContainer;
import smartin.platform.task.container.TaskContainerRegistry;
import smartin.platform.task.exception.TaskConfigurationException;
import smartin.platform.task.impl.GreetingTask;
import smartin.platform.task.impl.TaskBuilderImpl;
import smartin.platform.task.impl.TaskConfigBuilderImpl;
import smartin.platform.task.impl.TaskConfigImpl;
import smartin.platform.task.impl.TaskParamsBuilderImpl;

public class TaskBuilderTest {

  // TaskContainer 선언
  private TaskContainer taskContainer;

  @BeforeEach
  void setUp() {
    TaskContainerRegistry registry = new TaskContainerRegistry();
    registry.setTaskParamsBuilder(new TaskParamsBuilderImpl());
    registry.setTaskConfigBuilder(new TaskConfigBuilderImpl());
    registry.setTaskBuilder(new TaskBuilderImpl());
    taskContainer = TaskContainer.getInstance();
  }

  @Test
  void testBuildTask_WhenValidInput_ShouldCreateTaskSuccessfully() {

    Map<String, Object> configMap = new HashMap<>();
    configMap.put(KEY_ID, "id_task_1");
    configMap.put(KEY_TYPE, "GreetingTask");

    TaskConfig taskConfig = new TaskConfigImpl(configMap);

    TaskBuilder taskBuilder = taskContainer.getTaskBuilder();

    // 정상시나리오에서는 다른 Exception 발생이 없어야 한다.
    Task greetingTask = assertDoesNotThrow(() -> taskBuilder.buildTask(taskConfig));

    if (greetingTask instanceof GreetingTask) {
      assertNotNull(greetingTask);
      assertEquals("id_task_1", ((GreetingTask) greetingTask).getId());
      assertEquals(GreetingTask.class, greetingTask.getClass());
    }

  }

  @Test
  void testBuildTask_WhenTaskConfigIsNull_ShouldThrowException() {
    TaskConfig taskConfig = null;

    TaskBuilder taskBuilderImpl = taskContainer.getTaskBuilder();

    assertThrows(TaskConfigurationException.class, () -> {
      Task resultTask = taskBuilderImpl.buildTask(taskConfig);
    });
  }

  @Test
  void testBuildTask_WhenInvalidType_ShouldThrowException() {
    Map<String, Object> configMap = new HashMap<>();
    configMap.put(KEY_ID, "id_task_1");
    configMap.put(KEY_TYPE, "NotTaskType");

    TaskConfig taskConfig = new TaskConfigImpl(configMap);

    TaskBuilder taskBuilderImpl = taskContainer.getTaskBuilder();

    assertThrows(TaskConfigurationException.class, () -> {
      Task resultTask = taskBuilderImpl.buildTask(taskConfig);
    });
  }
}