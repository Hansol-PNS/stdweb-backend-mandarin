package smartin.platform.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static smartin.platform.task.contants.TaskConstants.KEY_ID;
import static smartin.platform.task.contants.TaskConstants.KEY_TYPE;

import extension.TestRegistryExtension;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import smartin.platform.task.impl.GreetingTask;
import smartin.platform.task.impl.TaskBuilderImpl;
import smartin.platform.task.impl.TaskConfigImpl;

@ExtendWith(TestRegistryExtension.class)
public class TaskBuilderTest {

  @Test
  void testBuildTask_WhenValidInput_ShouldCreateTaskSuccessfully() {
    Map<String, Object> configMap = new HashMap<>();
    configMap.put(KEY_ID, "id_task_1");
    configMap.put(KEY_TYPE, "smartin.platform.task.impl.GreetingTask");

    TaskConfig taskConfig = new TaskConfigImpl(configMap);
    TaskBuilder taskBuilder = new TaskBuilderImpl();
    Task resultTask = taskBuilder.buildTask(taskConfig);

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
    Map<String, Object> configMap = new HashMap<>();
    configMap.put(KEY_ID, "id_task_1");
    configMap.put(KEY_TYPE, "NotTaskType");

    TaskConfig taskConfig = new TaskConfigImpl(configMap);

    TaskBuilder taskBuilderImpl = new TaskBuilderImpl();

    assertThrows(IllegalArgumentException.class, () -> {
      Task resultTask = taskBuilderImpl.buildTask(taskConfig);
    });
  }
}