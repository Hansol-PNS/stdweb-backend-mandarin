package smartin.platform.task;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import smartin.platform.task.TaskBuilderTest.TaskConfigImpl;
import smartin.platform.task.contants.TaskConstants;
import smartin.platform.task.impl.GreetingTask;
import smartin.platform.task.impl.TaskParamsBuilderImpl;

@Slf4j
class TaskExecuteTest {

  @Test
  void execute() throws ClassNotFoundException {
    // given
    TaskParamsBuilder taskParamsBuilder = new TaskParamsBuilderImpl();
    String testId = "GreetingTask";
    String testType = "smartin.platform.task.impl.GreetingTaskParams";
    String testDataNameKey = "name";
    String testDataNameValue = "홍길동";
    Map<String, Object> testData = Map.of(testDataNameKey, testDataNameValue);
    Map<String, Object> params = Map.of(
        TaskConstants.KEY_ID, testId,
        TaskConstants.KEY_TYPE, testType,
        TaskConstants.KEY_DATA, testData
    );
    TaskParams taskParams = taskParamsBuilder.buildTaskParams(params);

    GreetingTask greetingTask = new GreetingTask(new TaskConfigImpl());
    String testTaskResultData = "Hello World, " + testDataNameValue;

    // When
    TaskResult taskResult = greetingTask.execute(taskParams);

    // then
    Assertions.assertNotNull(taskResult);
    log.debug((String) taskResult.getData());
    Assertions.assertEquals(testTaskResultData, String.valueOf(taskResult.getData()));
  }
}