package smartin.platform.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import smartin.platform.task.contants.TaskConstants;
import smartin.platform.task.impl.GreetingTaskParams;

class TaskParamsTest {

  @Test
  @DisplayName("성공:getId(): TaskParams id 존재 여부 확인")
  void getId() {
    // given
    String testId = "GreetingTask";

    Map<String, Object> params = Map.of(
        TaskConstants.KEY_ID, testId
    );

    TaskParams taskParams = new GreetingTaskParams(params);

    // when
    String result = taskParams.getId();

    // then
    assertEquals(testId, result);
  }

  @Test
  @DisplayName("성공:getType(): TaskParams type 존재 여부 확인")
  void getType() {
    // given
    String testType = "smartin.platform.task.impl.GreetingTaskParams";
    Map<String, Object> params = Map.of(
        TaskConstants.KEY_TYPE, testType
    );

    TaskParams taskParams = new GreetingTaskParams(params);

    // when
    String result = taskParams.getType();

    // then
    assertEquals(testType, result);
  }

  @Test
  @DisplayName("성공:getDataMap(): TaskParams DataMap 존재 여부 확인")
  void getDataMap() {
    // given
    String testDataNameKey = "name";
    String testDataNameValue = "홍길동";

    Map<String, Object> testData = Map.of(testDataNameKey, testDataNameValue);
    Map<String, Object> params = Map.of(
        TaskConstants.KEY_DATA, testData
    );

    TaskParams taskParams = new GreetingTaskParams(params);

    // when
    Object dataMap = taskParams.getDataMap();

    // then
    assertEquals(testData, dataMap);
  }

  @Test
  @DisplayName("성공:getData():name을 key로 TaskParams data 존재 여부 확인")
  void getData() {
    // given
    String testDataNameKey = "name";
    String testDataNameValue = "홍길동";

    Map<String, Object> testData = Map.of(testDataNameKey, testDataNameValue);
    Map<String, Object> params = Map.of(
        TaskConstants.KEY_DATA, testData
    );

    TaskParams taskParams = new GreetingTaskParams(params);

    // when
    Object result = taskParams.getData(testDataNameKey);

    // then
    assertEquals(testDataNameValue, result);
  }

  @Test
  @DisplayName("예외1:key == null 일 경우 예외 발생")
  void getData_예외조건_1() {
    // given
    String testDataNameKey = "name";
    String testDataNameValue = "홍길동";

    Map<String, Object> testData = Map.of(testDataNameKey, testDataNameValue);
    Map<String, Object> params = Map.of(
        TaskConstants.KEY_DATA, testData
    );
    TaskParams taskParams = new GreetingTaskParams(params);

    String testKey = null;

    // when
    Executable executable = () -> taskParams.getData(testKey);

    // Then
    assertThrows(NullPointerException.class, executable);
  }

  @Test
  @DisplayName("성공:hasKey():name을 key로 TaskParams key 존재 여부 확인")
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
    TaskParams taskParams = new GreetingTaskParams(params);

    // when
    boolean result1 = taskParams.hasKey(testDataNameKey);
    boolean result2 = taskParams.hasKey("naaaaame");

    // then
    assertTrue(result1);
    assertFalse(result2);
  }

  @Test
  @DisplayName("예외1:key == null 일 경우 예외 발생")
  void hasKey_예외조건_1() {
    // given
    String testDataNameKey = "name";
    String testDataNameValue = "홍길동";

    Map<String, Object> testData = Map.of(testDataNameKey, testDataNameValue);
    Map<String, Object> params = Map.of(
        TaskConstants.KEY_DATA, testData
    );
    TaskParams taskParams = new GreetingTaskParams(params);

    String testKey = null;

    // when
    Executable executable = () -> taskParams.hasKey(testKey);

    // Then
    assertThrows(NullPointerException.class, executable);
  }

  @Test
  void addTaskResult() {
    // TODO TaskExecute 테스트코드를 만든 후 작업

  }
}