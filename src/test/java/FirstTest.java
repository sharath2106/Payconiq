import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Booking;
import model.BookingDates;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import utils.ApiHelper;

public class FirstTest extends BaseTest {
  ApiHelper apiHelper = new ApiHelper();

  @Test
  void getBookingIds() {
    Response response = RestAssured.given().when().get("/booking/");
    response.then().statusCode(HttpStatus.SC_OK);
    response.body().prettyPrint();
  }

  @Test
  void getBooking() {
    Response response = RestAssured.given().when().get("/booking/10");
    response.then().statusCode(HttpStatus.SC_OK);
  }

  @Test
  void createBooking() {
    Booking booking =
        Booking.builder()
            .firstname("Jim")
            .lastname("Brown")
            .totalprice(111)
            .depositpaid(true)
            .bookingdates(BookingDates.builder().checkin("").checkout("").build())
            .additionalneeds("Breakfast")
            .build();
    Response response =
        RestAssured.given().contentType(ContentType.JSON).body(booking).when().post("/booking/");
    response.then().statusCode(HttpStatus.SC_OK);
    response.body().prettyPrint();
  }

  @Test
  void updateBooking() {
    String token = "token=" + apiHelper.generateToken();
    Booking booking =
        Booking.builder()
            .firstname("Jim")
            .lastname("Brown")
            .totalprice(111)
            .depositpaid(true)
            .bookingdates(BookingDates.builder().checkin("").checkout("").build())
            .additionalneeds("Breakfast")
            .build();
    Response response =
        RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .header("Cookie", token)
            .body(booking)
            .when()
            .put("/booking/4");
    response.then().statusCode(HttpStatus.SC_OK);
    response.body().prettyPrint();
  }

  @Test
  void partialUpdateBooking() {
    String token = "token=" + apiHelper.generateToken();
    Response response =
        RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .header("Cookie", token)
            .body(Booking.builder().firstname("James").lastname("Black").build())
            .when()
            .patch("/booking/4");
    response.then().statusCode(HttpStatus.SC_OK);
    response.body().prettyPrint();
  }

  @Test
  void deleteBooking() {
    Response response =
        RestAssured.given()
            .auth()
            .basic("admin", "password123")
            .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
            .contentType(ContentType.JSON)
            .when()
            .delete("/booking/2");
    response.then().statusCode(HttpStatus.SC_CREATED);
  }
}
