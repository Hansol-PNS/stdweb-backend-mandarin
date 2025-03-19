package smartin.platform.task;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    TaskBuilderTest.class,
    TaskConfigBuilderTest.class,
    TaskExecuteTest.class,
    TaskParamsBuilderTest.class,
    TaskParamsTest.class,
    TaskResultTest.class
})

public class AllTests {

}
