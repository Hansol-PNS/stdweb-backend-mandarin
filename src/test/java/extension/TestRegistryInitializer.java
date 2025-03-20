package extension;

import smartin.platform.task.impl.GreetingTask;
import smartin.platform.task.impl.GreetingTaskParams;
import smartin.platform.task.registry.TaskParamsRegistry;
import smartin.platform.task.registry.TaskRegistry;

/**
 * 테스트용 Registry Initializer
 */
public class TestRegistryInitializer {

  static {
    // TaskRegistry 추가
    TaskParamsRegistry.register("smartin.platform.task.impl.GreetingTaskParams", GreetingTaskParams::new);
    
    TaskRegistry.register("smartin.platform.task.impl.GreetingTask", (taskConfig) -> new GreetingTask(taskConfig));
  }

  public static void initialize() {
    // 의도적으로 빈 메서드 - 클래스 로딩을 트리거하기 위해 사용
  }
}
