import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class GetBookingTest extends BaseTest {
  @Test
  void getAllBookingIds() {
    Response response = RestAssured.given().when().get("/booking/");
    response.then().statusCode(HttpStatus.SC_OK);
    response.body().prettyPrint();
  }

  @Test
  void getBookingIdByFirstNameAndLastName() {
    Response response =
        RestAssured.given()
            .param("firstname", "Jim")
            .param("lastname", "Brown")
            .when()
            .get("/booking/");
    response.then().statusCode(HttpStatus.SC_OK);
    response.body().prettyPrint();
  }

  @Test
  void getBookingIdByCheckinDate() {
    Response response =
        RestAssured.given()
            .param("checkin", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE))
            .when()
            .get("/booking/");
    response.then().statusCode(HttpStatus.SC_OK);
    response.body().prettyPrint();
  }

  @Test
  void getBookingById() {
    Response response = RestAssured.given().when().get("/booking/10");
    response.then().statusCode(HttpStatus.SC_OK);
    response.body().prettyPrint();
  }
}
