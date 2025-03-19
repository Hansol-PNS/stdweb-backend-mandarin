package smartin.platform.task.impl;

import java.util.HashMap;
import java.util.Map;
import smartin.platform.task.TaskResult;
import smartin.platform.task.contants.TaskConstants;

public abstract class TaskResultAbstract implements TaskResult {

  protected String id;
  protected String type;
  protected boolean isSuccess;
  protected String message;
  protected Object data;

  public TaskResultAbstract(String id, String type) {
    this.id = id;
    this.type = type;
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public boolean isSuccess() {
    return isSuccess;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public Object getData() {
    return data;
  }

  @Override
  public void makeResult(boolean success, String message, Object data) {
    make(success, message, data);
  }

  @Override
  public Map<String, Object> getFormattedResultMap() {
    Map<String, Object> root = new HashMap<>();
    Map<String, Object> resultData = new HashMap<>();
    resultData.put(TaskConstants.KEY_ID, this.id);
    resultData.put(TaskConstants.KEY_TYPE, this.type);
    resultData.put(TaskConstants.KEY_DATA, data);
    root.put(TaskConstants.KEY_TASK_RESULT_ROOT, resultData);
    return root;
  }

  public abstract void make(boolean success, String message, Object data);

}
