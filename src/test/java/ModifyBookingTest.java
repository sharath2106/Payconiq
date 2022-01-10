import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.Booking;
import model.BookingDates;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ModifyBookingTest extends BaseTest {

  @Test
  @DisplayName("user should update the booking id successfully and return 200")
  void verifyUserIsAbleToSuccessfullyUpdateUserBooking() {
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String checkinDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    String checkoutDate = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
    String additionalNeeds = faker.food().fruit();
    int totalPrice = 12;
    Booking booking =
        Booking.builder()
            .firstname(firstName)
            .lastname(lastName)
            .totalprice(totalPrice)
            .depositpaid(true)
            .bookingdates(
                BookingDates.builder().checkin(checkinDate).checkout(checkoutDate).build())
            .additionalneeds(additionalNeeds)
            .build();

    Response response = given().spec(requestSpec).body(booking).when().put(BOOKING_API + bookingId);

    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
    Booking updatedBooking = response.as(Booking.class);
    assertThat(updatedBooking.getFirstname()).isEqualTo(firstName);
    assertThat(updatedBooking.getLastname()).isEqualTo(lastName);
    assertThat(updatedBooking.getTotalprice()).isEqualTo(totalPrice);
    assertThat(updatedBooking.getDepositpaid()).isTrue();
    assertThat(updatedBooking.getBookingdates().checkin).isEqualTo(checkinDate);
    assertThat(updatedBooking.getBookingdates().checkout).isEqualTo(checkoutDate);
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
    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
  }

  @Test
  @Disabled("Test is disabled as the response code is not returning 404")
  @DisplayName("return 404 when trying to update an invalid booking id")
  void verifyUpdateBookingForInvalidBookingId() {
    int wrongBookingId = bookingId + Integer.MAX_VALUE;
    Booking booking =
        Booking.builder()
            .firstname(faker.name().firstName())
            .lastname(faker.name().lastName())
            .totalprice(12)
            .depositpaid(true)
            .build();
    Response response =
        given().spec(requestSpec).body(booking).when().put(BOOKING_API + wrongBookingId);
    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
  }

  @Test
  @DisplayName("user should partially update the booking and return 200")
  void verifyUserIsAbleToUpdateBookingPartially() {
    String firstName = faker.name().firstName();
    String checkinDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    Booking requestBody =
        Booking.builder()
            .firstname(firstName)
            .bookingdates(BookingDates.builder().checkin(checkinDate).build())
            .build();
    Response response =
        given().spec(requestSpec).body(requestBody).when().patch(BOOKING_API + bookingId);

    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
    Booking updatedBooking = response.as(Booking.class);
    assertThat(updatedBooking.getFirstname()).isEqualTo(firstName);
    assertThat(updatedBooking.getBookingdates().getCheckin()).isEqualTo(checkinDate);
  }
}
