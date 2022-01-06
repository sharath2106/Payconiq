import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class DeleteBookingTest extends BaseTest {
  @Test
  void deleteBooking() {
    Response response =
        RestAssured.given()
            .spec(requestSpec)
            .contentType(ContentType.JSON)
            .when()
            .delete("/booking/" + bookingId);
    response.then().statusCode(HttpStatus.SC_CREATED);
  }

  @Test
  void deleteBookingThatDoesntExists() {
    int wrongBookingId = bookingId + 1;
    Response response =
        RestAssured.given()
            .spec(requestSpec)
            .contentType(ContentType.JSON)
            .when()
            .delete("/booking/" + 90);
    response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
  }
}
