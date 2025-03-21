package smartin.platform.task.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import smartin.platform.task.TaskParams;

// TaskParams 객체 정보를 담고 있는 저장소
public class TaskParamsRegistry {

  private static final Map<String, Function<Map<String, Object>, TaskParams>> registry = new HashMap<>();

  public static void register(String typeName, Function<Map<String, Object>, TaskParams> creator) {
    registry.put(typeName.toUpperCase(), creator);
  }

  public static TaskParams get(String typeName, Map<String, Object> params) {
    Function<Map<String, Object>, TaskParams> creator = registry.get(typeName.toUpperCase());
    if (creator == null) {
      throw new IllegalArgumentException("Unknown task params type: " + typeName);
    }

    return creator.apply(params);
  }
}
