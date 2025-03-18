package smartin.platform.task;

public interface TaskParams {

  //  public TaskParams create(Map paramMap);
  public String getId();

  public String getType();

  public Object getDataMap();

  public Object getData(String key);

  public boolean hasKey(String key);

  public void addTaskResult(TaskResult taskResult);
}
