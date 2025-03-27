package smartin.platform.task.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static smartin.platform.task.contants.TaskConstants.KEY_ID;
import static smartin.platform.task.contants.TaskConstants.KEY_TYPE;

import java.util.Map;
import org.junit.jupiter.api.Test;

public class TaskConfigImplTest {

  @Test
  void testCreateTaskConfig_WhenValidInput_ShouldCreateTaskConfigSuccessfully() {
    Map<String, Object> configMap = Map.of(
        KEY_ID, "id_task_1",
        KEY_TYPE, "GreetingTask"
    );

    TaskConfigImpl taskConfig = assertDoesNotThrow(() -> new TaskConfigImpl(configMap));

    assertEquals("id_task_1", taskConfig.getId());
    assertEquals("GreetingTask", taskConfig.getType());
  }
}