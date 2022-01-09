import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import model.Booking;
import model.BookingDates;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ModifyBookingTest extends BaseTest {

  @Test
  @DisplayName("user should update the booking id successfully and return 200")
  void verifyUserIsAbleToSuccessfullyUpdateUserBooking() {
    Booking booking =
        Booking.builder()
            .firstname(faker.name().firstName())
            .lastname(faker.name().lastName())
            .totalprice(12)
            .depositpaid(true)
            .bookingdates(BookingDates.builder().checkin("").checkout("").build())
            .additionalneeds(faker.food().fruit())
            .build();
    Response response = given().spec(requestSpec).body(booking).when().put(BOOKING_API + bookingId);
    response.then().statusCode(HttpStatus.SC_OK);
  }

  @Test
  @DisplayName(
      "user should not be able to update booking when the request body is invalid/empty and return 400")
  void verifyUserIsNotAbleToUpdateBookingWithEmptyOrInvalidRequest() {
    Booking booking =
        Booking.builder()
            .firstname(faker.name().firstName())
            .lastname(faker.name().lastName())
            .totalprice(12)
            .depositpaid(true)
            .build();
    Response response = given().spec(requestSpec).body(booking).when().put(BOOKING_API + bookingId);
    response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test
  @DisplayName("user should partially update the booking and return 200")
  void verifyUserIsAbleToUpdateBookingPartially() {
    Booking requestBody =
        Booking.builder()
            .firstname(faker.name().firstName())
            .lastname(faker.name().lastName())
            .bookingdates(BookingDates.builder().checkin("").checkout("").build())
            .build();
    Response response =
        given().spec(requestSpec).body(requestBody).when().patch(BOOKING_API + bookingId);
    response.then().statusCode(HttpStatus.SC_OK);
  }
}
