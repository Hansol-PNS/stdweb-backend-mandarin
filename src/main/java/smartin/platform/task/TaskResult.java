package smartin.platform.task;

import java.util.Map;

public interface TaskResult {

  String getId();

  String getType();

  boolean isSuccess();

  String getMessage();

  Object getData();

  Map<String, Object> getFormattedResultMap();

  void makeResult(boolean success, String message, Object data);
}
