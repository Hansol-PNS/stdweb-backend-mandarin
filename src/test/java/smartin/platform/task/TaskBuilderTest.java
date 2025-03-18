package smartin.platform.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import smartin.platform.task.impl.TaskAbstract;
import smartin.platform.task.impl.TaskImpl;

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

      return new TaskImpl(taskConfig);
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

  @Test
  void test_buildTask() {
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
}