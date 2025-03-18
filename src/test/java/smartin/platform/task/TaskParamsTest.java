package smartin.platform.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.Test;
import smartin.platform.task.contants.TaskConstants;
import smartin.platform.task.impl.GreetingTaskParams;

class TaskParamsTest {

  @Test
  void getId() {
    // given
    String testId = "GreetingTask";

    Map<String, Object> params = Map.of(
        TaskConstants.KEY_ID, testId
    );

    // when
    TaskParams taskParams = new GreetingTaskParams(params);

    // then
    assertEquals(testId, taskParams.getId());
  }

  @Test
  void getType() {
    // given
    String testType = "smartin.platform.task.impl.GreetingTaskParams";
    Map<String, Object> params = Map.of(
        TaskConstants.KEY_TYPE, testType
    );

    // when
    TaskParams taskParams = new GreetingTaskParams(params);

    // then
    assertEquals(testType, taskParams.getType());
  }

  @Test
  void getDataMap() {
    // given
    String testDataNameKey = "name";
    String testDataNameValue = "홍길동";

    Map<String, Object> testData = Map.of(testDataNameKey, testDataNameValue);
    Map<String, Object> params = Map.of(
        TaskConstants.KEY_DATA, testData
    );

    // when
    TaskParams taskParams = new GreetingTaskParams(params);

    // then
    assertEquals(testData, taskParams.getDataMap());
  }

  @Test
  void getData() {
    // given
    String testDataNameKey = "name";
    String testDataNameValue = "홍길동";

    Map<String, Object> testData = Map.of(testDataNameKey, testDataNameValue);
    Map<String, Object> params = Map.of(
        TaskConstants.KEY_DATA, testData
    );

    // when
    TaskParams taskParams = new GreetingTaskParams(params);

    // then
    assertEquals(testDataNameValue, taskParams.getData(testDataNameKey));
  }

  @Test
  void hasKey() {
    // given
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

    // when
    TaskParams taskParams = new GreetingTaskParams(params);
    // then
    assertTrue(taskParams.hasKey(testDataNameKey));

    assertFalse(taskParams.hasKey("naaaaame"));
  }

  @Test
  void addTaskResult() {
    // TODO TaskExecute 테스트코드를 만든 후 작업

  }
}