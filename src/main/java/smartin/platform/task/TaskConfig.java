package smartin.platform.task;

public interface TaskConfig {

  public String getId();

  public String getType();

  public TaskConfigValue getConfigParam(String key);
}
