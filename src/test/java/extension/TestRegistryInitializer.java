package extension;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import smartin.platform.task.impl.GreetingTask;
import smartin.platform.task.impl.GreetingTaskParams;
import smartin.platform.task.registry.TaskParamsRegistry;
import smartin.platform.task.registry.TaskRegistry;

/**
 * 테스트용 Registry Initializer
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestRegistryInitializer {

  public static void initialize() {
    log.info("Initializing TestRegistry");
    // TaskParamsRegistry 추가
    TaskParamsRegistry.register("smartin.platform.task.impl.GreetingTaskParams", GreetingTaskParams::new);
    // TaskRegistry 추가
    TaskRegistry.register("smartin.platform.task.impl.GreetingTask", (taskConfig) -> new GreetingTask(taskConfig));

  }
}
