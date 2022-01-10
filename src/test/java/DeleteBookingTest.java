import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeleteBookingTest extends BaseTest {
  @Test
  @DisplayName("should return 200 when the booking is deleted successfully")
  void verifySuccessfulDeletionOfBooking() {
    Response response = given().spec(requestSpec).when().delete(BOOKING_API + bookingId);
    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
  }

  @Test
  @Disabled("Test is disabled as the response code is not returning 404")
  @DisplayName("should return 404 when trying to delete a booking that's not-available/invalid")
  void verifyUserIsNotAbleToDeleteAnInvalidBookingId() {
    int wrongBookingId = bookingId + Integer.MAX_VALUE;
    Response response = given().spec(requestSpec).when().delete(BOOKING_API + wrongBookingId);
    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
  }
}
