package smartin.platform.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static smartin.platform.task.contants.TaskConstants.KEY_ID;
import static smartin.platform.task.contants.TaskConstants.KEY_TYPE;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import smartin.platform.task.exception.TaskConfigurationException;
import smartin.platform.task.impl.GreetingTask;
import smartin.platform.task.impl.TaskConfigImpl;
import smartin.platform.task.impl.TaskContainer;

public class TaskBuilderTest {

  // static 선언
  private static final TaskContainer taskContainer = TaskContainer.getInstance();

  @Test
  void testBuildTask_WhenValidInput_ShouldCreateTaskSuccessfully() {

    Map<String, Object> configMap = new HashMap<>();
    configMap.put(KEY_ID, "id_task_1");
    configMap.put(KEY_TYPE, "GreetingTask");

    TaskConfig taskConfig = new TaskConfigImpl(configMap);

    TaskBuilder taskBuilder = taskContainer.getTaskBuilder();

    // 정상시나리오에서는 다른 Exception 발생이 없어야 한다.
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