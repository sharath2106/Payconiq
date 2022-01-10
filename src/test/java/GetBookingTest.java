import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetBookingTest extends BaseTest {
  @Test
  @Tag("get-booking")
  @DisplayName("should return list of all bookings")
  void getAllBookingIds() {
    Response response = given().when().get(BOOKING_API);
    assertThat(response.body()).isNotNull();
    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
  }

  @Test
  @Tag("get-booking")
  @DisplayName("should return list of available bookings for the first and last name")
  void getBookingIdByFirstNameAndLastName() {
    Response response =
        given().param("firstname", "Jim").param("lastname", "Brown").when().get(BOOKING_API);
    assertThat(response.body()).isNotNull();
    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
  }

  @Test
  @Tag("get-booking")
  @DisplayName("should return list of available bookings for the checkin date")
  void getBookingIdByCheckinDate() {
    Response response =
        given()
            .param("checkin", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE))
            .when()
            .get(BOOKING_API);
    assertThat(response.body()).isNotNull();
    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
  }

  @Test
  @Tag("get-booking")
  @DisplayName("should return booking details for the booking id")
  void verifyBookingDetailsForTheBookingId() {
    Response response = given().when().get(BOOKING_API + bookingId);
    assertThat(response.body()).isNotNull();
    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
  }

  @Test
  @Tag("get-booking")
  @DisplayName("should return no booking details for invalid booking id")
  void verifyBookingIsNotAvailableForInvalidBookingId() {
    int invalidBookingId = bookingId + Integer.MAX_VALUE;
    Response response = given().when().get(BOOKING_API + invalidBookingId);
    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
  }
}
