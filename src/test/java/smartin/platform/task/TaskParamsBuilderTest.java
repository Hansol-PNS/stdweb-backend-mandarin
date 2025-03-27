package smartin.platform.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import smartin.platform.task.contants.TaskConstants;
import smartin.platform.task.exception.TaskConfigurationException;
import smartin.platform.task.impl.GreetingTaskParams;
import smartin.platform.task.impl.TaskParamsBuilderImpl;

class TaskParamsBuilderTest {

  @Test
  @DisplayName("성공:buildTaskParams")
  void buildTaskParams() {

    //Given
    TaskParamsBuilder builder = new TaskParamsBuilderImpl();
    String testId = "GreetingTask";
    String testType = "GreetingTaskParams";
    String testDataNameKey = "name";
    String testDataNameValue = "홍길동";
    Map<String, Object> testData = Map.of(testDataNameKey, testDataNameValue);
    Map<String, Object> params = Map.of(
        TaskConstants.KEY_ID, testId,
        TaskConstants.KEY_TYPE, testType,
        TaskConstants.KEY_DATA, testData
    );
    // When
    // 정상시나리오에서는 다른 Exception 발생이 없어야 한다.
    TaskParams taskParams = assertDoesNotThrow(() -> builder.buildTaskParams(params));

    // Then
    // TaskParams는 GreetingTaskParams 클래스 타입이다.
    assertInstanceOf(GreetingTaskParams.class, taskParams);
    // TaskParams.getId()에 “GreetingTask” 문자열이 존재한다.
    assertEquals(testId, taskParams.getId());
    // TaskParams.getData("name")에 "홍길동"이 있음을 확인 한다.
    assertEquals(testData.get(testDataNameKey), taskParams.getData(testDataNameKey));


  }

  @Test
  @DisplayName("예외1:params == null 일 경우 예외 발생")
  void buildTaskParams_예외조건_1() {
    // Given
    TaskParamsBuilder builder = new TaskParamsBuilderImpl();
    Map<String, Object> params = null;
    // When
    Executable executable = () -> builder.buildTaskParams(params);
    // Then
    // assertThrows(NullPointerException.class, executable);
    assertThrows(TaskConfigurationException.class, executable);
  }

  @Test
  @DisplayName("예외2:params.get(type) == null 일 경우 예외 발생")
  void buildTaskParams_예외조건_2() {
    // Given
    TaskParamsBuilder builder = new TaskParamsBuilderImpl();
    String testType = null;
    Map<String, Object> params = new HashMap<>();
    params.put(TaskConstants.KEY_TYPE, testType);
    // When
    Executable executable = () -> builder.buildTaskParams(params);
    // Then
    // assertThrows(IllegalArgumentException.class, executable);
    assertThrows(TaskConfigurationException.class, executable);
  }

  @Test
  @DisplayName("예외3:params.get(type)에 없는 클래스를 가리키면 예외 발생")
  void buildTaskParams_예외조건_3() {
    // Given
    TaskParamsBuilder builder = new TaskParamsBuilderImpl();
    String testType = "예외발생1.class";
    Map<String, Object> params = Map.of(
        TaskConstants.KEY_TYPE, testType
    );
    // When
    Executable executable = () -> builder.buildTaskParams(params);
    // Then
    // assertThrows(IllegalArgumentException.class, executable);
    assertThrows(TaskConfigurationException.class, executable);
  }

  @Test
  @DisplayName("예외4:params.get(type)에 TaskParams 타입이 아닐 경우 예외 발생")
  void buildTaskParams_예외조건_4() {
    // Given
    TaskParamsBuilder builder = new TaskParamsBuilderImpl();
    String testType = "smartin.platform.service.ServiceParams";
    Map<String, Object> params = Map.of(
        TaskConstants.KEY_TYPE, testType
    );
    // When
    Executable executable = () -> builder.buildTaskParams(params);
    // Then
    // assertThrows(IllegalArgumentException.class, executable);
    assertThrows(TaskConfigurationException.class, executable);
  }
}