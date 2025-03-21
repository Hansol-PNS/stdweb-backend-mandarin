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
import smartin.platform.task.impl.TaskConfigBuilderImpl;

public class TaskConfigBuilderTest {

  @Test
  void testBuildTaskConfig_WhenValidInput_ShouldCreateTaskConfigSuccessfully() {

    Map<String, Object> configMap = new HashMap<>();
    configMap.put(KEY_ID, "id_task_1");
    configMap.put(KEY_TYPE, "GreetingTask");

    TaskConfigBuilder taskConfigBuilderImpl = new TaskConfigBuilderImpl();
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
      TaskConfigBuilder taskConfigBuilderImpl = new TaskConfigBuilderImpl();
      TaskConfig config = taskConfigBuilderImpl.buildTaskConfig(configMap);
    });
  }

}