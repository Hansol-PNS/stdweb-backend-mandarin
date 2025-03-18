package smartin.platform.task.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import smartin.platform.task.TaskParams;
import smartin.platform.task.TaskParamsBuilder;
import smartin.platform.task.contants.TaskConstants;

@Slf4j
@NoArgsConstructor
public class TaskParamsBuilderImpl implements TaskParamsBuilder {

  @Override
  public TaskParams buildTaskParams(Map params) throws NullPointerException, ClassNotFoundException, IllegalArgumentException {
    try {
      // params null 이거나 비어 있을 경우
      if (params == null || params.isEmpty()) {
        throw new NullPointerException("params is null");
      }

      String className = (String) params.get(TaskConstants.KEY_TYPE);

      // null 이거나 비어 있을 경우
      if (className == null || "".equals(className)) {
        throw new IllegalArgumentException("Class name is missing in params");
      }

      Class<?> clazz = Class.forName(className);
      // Map을 인자로 받는 생성자 찾기
      Constructor<?> constructor = clazz.getConstructor(Map.class);

      // 인스턴스 생성
      Object newInstance = constructor.newInstance(params);

      // TaskParams 인스턴스가 아닌 경우 에러발생
      if (!(newInstance instanceof TaskParams)) {
        throw new IllegalArgumentException("Object is Not instance of TaskParams");
      }
      return (TaskParams) newInstance;
    } catch (NoSuchMethodException e) {
      throw new IllegalArgumentException(e);
    } catch (InvocationTargetException e) {
      throw new IllegalArgumentException(e);
    } catch (InstantiationException e) {
      throw new IllegalArgumentException(e);
    } catch (IllegalAccessException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
