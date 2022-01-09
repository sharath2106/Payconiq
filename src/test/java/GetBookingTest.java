import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetBookingTest extends BaseTest {
  @Test
  @DisplayName("should return list of all bookings")
  void getAllBookingIds() {
    Response response = RestAssured.given().when().get();
    response.then().statusCode(HttpStatus.SC_OK);
  }

  @Test
  @DisplayName("should return list of available bookings for the first and last name")
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
  @DisplayName("should return list of available bookings for the checkin and checkout dates")
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
  @DisplayName("should return booking details for the booking id")
  void getBookingById() {
    Response response = RestAssured.given().when().get("/booking/" + bookingId);
    response.then().statusCode(HttpStatus.SC_OK);
    response.body().prettyPrint();
  }
}
