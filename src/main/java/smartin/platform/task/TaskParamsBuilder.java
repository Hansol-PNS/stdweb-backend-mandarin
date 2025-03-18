package smartin.platform.task;

import java.util.Map;

public interface TaskParamsBuilder {

  TaskParams buildTaskParams(Map params) throws NullPointerException, ClassNotFoundException, IllegalArgumentException;
}
