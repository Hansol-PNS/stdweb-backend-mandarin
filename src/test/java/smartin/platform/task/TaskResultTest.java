package smartin.platform.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import smartin.platform.task.impl.GreetingTaskResult;

/**
 * TODO 작업 필요
 */
class TaskResultTest {

  @Test
  void isSuccess() {
    // given
    String testId = "GreetingTask";
    String testType = "smartin.platform.task.impl.GreetingTaskParams";
    GreetingTaskResult taskResult = new GreetingTaskResult(testId, testType);
    taskResult.make(true, null, null);

    // when
    boolean success = taskResult.isSuccess();

    // then
    Assertions.assertTrue(success);
  }

  @Test
  void getMessage() {
    // given
    String testId = "GreetingTask";
    String testType = "smartin.platform.task.impl.GreetingTaskParams";
    GreetingTaskResult taskResult = new GreetingTaskResult(testId, testType);
    taskResult.make(true, null, null);

    // when
    boolean success = taskResult.isSuccess();

    // then
    Assertions.assertTrue(success);
  }

  @Test
  void getData() {

  }

  @Test
  void makeResult() {
    // TODO 수정 필요 addTaskResult의 스펙과 taskExecute의 스펙이 다름

  }
}