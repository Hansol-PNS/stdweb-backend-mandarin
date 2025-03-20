package smartin.platform.task.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import smartin.platform.task.Task;
import smartin.platform.task.TaskConfig;

// TaskParams 객체 정보를 담고 있는 저장소
public class TaskRegistry {

  private static final Map<String, Function<TaskConfig, Task>> registry = new HashMap<>();

  public static void register(String typeName, Function<TaskConfig, Task> creator) {
    registry.put(typeName.toUpperCase(), creator);
  }

  public static Task get(String typeName, TaskConfig taskConfig) {
    Function<TaskConfig, Task> creator = registry.get(typeName.toUpperCase());
    if (creator == null) {
      throw new IllegalArgumentException("Unknown task type: " + typeName);
    }

    return creator.apply(taskConfig);
  }
}
