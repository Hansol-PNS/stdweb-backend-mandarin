package extension;


import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Test용 객체 생성 레지스트리
 */
public class TestRegistryExtension implements BeforeAllCallback {

  @Override
  public void beforeAll(ExtensionContext extensionContext) {
    TestRegistryInitializer.initialize();
  }
}
