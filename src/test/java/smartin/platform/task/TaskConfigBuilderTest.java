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
import smartin.platform.task.impl.TaskBuilderImpl;
import smartin.platform.task.impl.TaskConfigBuilderImpl;
import smartin.platform.task.impl.TaskParamsBuilderImpl;

public class TaskConfigBuilderTest {

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
  void testBuildTaskConfig_WhenValidInput_ShouldCreateTaskConfigSuccessfully() {

    Map<String, Object> configMap = new HashMap<>();
    configMap.put(KEY_ID, "id_task_1");
    configMap.put(KEY_TYPE, "GreetingTask");

    TaskConfigBuilder taskConfigBuilderImpl = taskContainer.getTaskConfigBuilder();
    // 정상시나리오에서는 다른 Exception 발생이 없어야 한다.
    TaskConfig config = assertDoesNotThrow(() -> taskConfigBuilderImpl.buildTaskConfig(configMap));

    assertNotNull(config);
    assertEquals("id_task_1", config.getId());
    assertEquals("GreetingTask", config.getType());


  }

  @Test
  void testBuildTaskConfig_WhenMissingRequired_ShouldThrowException() {

    Map<String, Object> configMap = new HashMap<>();
    configMap.put(KEY_ID, "id_task_1");
    configMap.put(KEY_TYPE, "");

    // assertThrows(IllegalArgumentException.class, () -> {
    assertThrows(TaskConfigurationException.class, () -> {
      TaskConfigBuilder taskConfigBuilderImpl = taskContainer.getTaskConfigBuilder();
      TaskConfig config = taskConfigBuilderImpl.buildTaskConfig(configMap);
    });
  }

}