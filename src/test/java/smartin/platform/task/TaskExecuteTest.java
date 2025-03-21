package smartin.platform.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static smartin.platform.task.contants.TaskConstants.KEY_ID;
import static smartin.platform.task.contants.TaskConstants.KEY_TYPE;

import extension.TestRegistryExtension;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import smartin.platform.task.contants.TaskConstants;
import smartin.platform.task.exception.TaskExecutionException;
import smartin.platform.task.impl.GreetingTask;
import smartin.platform.task.impl.TaskConfigImpl;
import smartin.platform.task.impl.TaskParamsBuilderImpl;

@Slf4j
@ExtendWith(TestRegistryExtension.class)
class TaskExecuteTest {

  @Test
  @DisplayName("정상:execute()")
  void execute() {

    assertDoesNotThrow(() -> {
      // given
      TaskParamsBuilder taskParamsBuilder = new TaskParamsBuilderImpl();
      String testId = "GreetingTask";
      String testType = "smartin.platform.task.impl.GreetingTaskParams";
      String testDataNameKey = "name";
      String testDataNameValue = "홍길동";
      Map<String, Object> testData = Map.of(testDataNameKey, testDataNameValue);
      Map<String, Object> params = Map.of(
          KEY_ID, testId,
          TaskConstants.KEY_TYPE, testType,
          TaskConstants.KEY_DATA, testData
      );
      TaskParams taskParams = taskParamsBuilder.buildTaskParams(params);

      Map<String, Object> configMap = Map.of(
          KEY_ID, "id_task_1",
          KEY_TYPE, "smartin.platform.task.impl.GreetingTask"
      );

      GreetingTask greetingTask = new GreetingTask(new TaskConfigImpl(configMap));
      String testTaskResultData = "Hello World, " + testDataNameValue;

      // When
      TaskResult taskResult = greetingTask.execute(taskParams);

      // then
      Assertions.assertNotNull(taskResult);
      log.debug((String) taskResult.getData());
      Assertions.assertEquals(testTaskResultData, String.valueOf(taskResult.getData()));
    });

  }

  @Test
  @DisplayName("예외1:TaskParams == null 일 경우 예외 발생")
  void execute_예외조건_1() {
    // given
    TaskParamsBuilder taskParamsBuilder = new TaskParamsBuilderImpl();
    String testId = "GreetingTask";
    String testType = "smartin.platform.task.impl.GreetingTaskParams";
    String testDataNameKey = "name";
    String testDataNameValue = "홍길동";
    Map<String, Object> testData = Map.of(testDataNameKey, testDataNameValue);
    Map<String, Object> params = Map.of(
        KEY_ID, testId,
        TaskConstants.KEY_TYPE, testType,
        TaskConstants.KEY_DATA, testData
    );
    TaskParams taskParams = null;

    Map<String, Object> configMap = Map.of(
        KEY_ID, "id_task_1",
        KEY_TYPE, "smartin.platform.task.impl.GreetingTask"
    );

    GreetingTask greetingTask = new GreetingTask(new TaskConfigImpl(configMap));
    String testTaskResultData = "Hello World, " + testDataNameValue;

    // When
    Executable executable = () -> greetingTask.execute(taskParams);

    // then
    // assertThrows(IllegalArgumentException.class, executable);
    assertThrows(TaskExecutionException.class, executable);
  }

  @Test
  @DisplayName("예외2:TaskParams.getData(”name”)이 null 이거나 비어 있을 경우 예외 발생")
  void execute_예외조건_2() {
    // given
    TaskParamsBuilder taskParamsBuilder = new TaskParamsBuilderImpl();
    String testId = "GreetingTask";
    String testType = "smartin.platform.task.impl.GreetingTaskParams";
    String testDataNameKey = "name";
    String testDataNameValue = "";
    Map<String, Object> testData = Map.of(testDataNameKey, testDataNameValue);
    Map<String, Object> params = Map.of(
        KEY_ID, testId,
        TaskConstants.KEY_TYPE, testType,
        TaskConstants.KEY_DATA, testData
    );
    // 본 테스트에서 확인할 부분은 TaskParams 생성에 대한 테스트가 아니기 때문에 다른 Exception은 발생하지 않아야 한다.
    TaskParams taskParams = assertDoesNotThrow(() -> taskParamsBuilder.buildTaskParams(params));

    Map<String, Object> configMap = Map.of(
        KEY_ID, "id_task_1",
        KEY_TYPE, "smartin.platform.task.impl.GreetingTask"
    );

    GreetingTask greetingTask = new GreetingTask(new TaskConfigImpl(configMap));

    // When
    TaskParams finalTaskParams = taskParams;
    Executable executable = () -> greetingTask.execute(taskParams);

    // then
    assertThrows(TaskExecutionException.class, executable);
  }
}