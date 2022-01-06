import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Booking;
import model.BookingDates;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class ModifyBookingTest extends BaseTest {
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
        RestAssured.given().spec(requestSpec).body(booking).when().post("/booking/");
    response.then().statusCode(HttpStatus.SC_OK);
    response.body().prettyPrint();
  }

  @Test
  void updateBooking() {
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
        RestAssured.given().spec(requestSpec).body(booking).when().put("/booking/" + bookingId);
    response.then().statusCode(HttpStatus.SC_OK);
    response.body().prettyPrint();
  }

  @Test
  void partialUpdateBooking() {
    Booking requestBody =
        Booking.builder()
            .firstname("James")
            .lastname("Black")
            .bookingdates(BookingDates.builder().checkin("").checkout("").build())
            .build();
    Response response =
        RestAssured.given()
            .spec(requestSpec)
            .body(requestBody)
            .when()
            .patch("/booking/" + bookingId);
    response.then().statusCode(HttpStatus.SC_OK);
    response.body().prettyPrint();
  }
}
