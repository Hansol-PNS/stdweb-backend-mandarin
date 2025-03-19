package smartin.platform.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static smartin.platform.task.contants.TaskConstants.KEY_ID;
import static smartin.platform.task.contants.TaskConstants.KEY_TYPE;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import smartin.platform.task.impl.TaskConfigBuilderImpl;

public class TaskConfigBuilderTest {

  @Test
  void testBuildTaskConfig_WhenValidInput_ShouldCreateTaskConfigSuccessfully() {

    Map<String, Object> configMap = new HashMap<>();
    configMap.put(KEY_ID, "id_task_1");
    configMap.put(KEY_TYPE, "GreetingTask");

    TaskConfigBuilder taskConfigBuilderImpl = new TaskConfigBuilderImpl();
    TaskConfig config = taskConfigBuilderImpl.buildTaskConfig(configMap);

    assertNotNull(config);
    assertEquals("id_task_1", config.getId());
    assertEquals("GreetingTask", config.getType());
  }

  @Test
  void testBuildTaskConfig_WhenMissingRequired_ShouldThrowException() {

    Map<String, Object> configMap = new HashMap<>();
    configMap.put(KEY_ID, "id_task_1");
    configMap.put(KEY_TYPE, "");

    assertThrows(IllegalArgumentException.class, () -> {
      TaskConfigBuilder taskConfigBuilderImpl = new TaskConfigBuilderImpl();
      TaskConfig config = taskConfigBuilderImpl.buildTaskConfig(configMap);
    });
  }

}