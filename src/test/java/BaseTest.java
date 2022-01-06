import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

  @BeforeEach
  void beforeEach() {
    RestAssured.baseURI = "https://restful-booker.herokuapp.com";
  }
}
