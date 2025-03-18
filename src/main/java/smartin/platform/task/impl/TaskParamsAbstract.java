package smartin.platform.task.impl;

import java.util.Map;
import smartin.platform.task.TaskParams;
import smartin.platform.task.contants.TaskConstants;

public abstract class TaskParamsAbstract implements TaskParams {

  protected String id;
  protected String type;
  protected Map<String, ?> dataMap;

  public TaskParamsAbstract(Map<String, ?> params) {
    this.id = params.get(TaskConstants.KEY_ID).toString();
    this.type = params.get(TaskConstants.KEY_TYPE).toString();
    this.dataMap = (Map<String, ?>) params.get(TaskConstants.KEY_DATA);
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
  public Map<String, ?> getDataMap() {
    return this.dataMap;
  }
}
