import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeleteBookingTest extends BaseTest {
  @Test
  @DisplayName("should return 200 when the booking is deleted successfully")
  void verifySuccessfulDeletionOfBooking() {
    Response response =
        RestAssured.given().spec(requestSpec).when().delete("/booking/" + bookingId);
    response.then().statusCode(HttpStatus.SC_CREATED);
  }

  @Test
  @DisplayName("should return 404 when trying to delete a booking that's not-available/invalid")
  void verifyUserIsNotAbleToDeleteAnInvalidBookingId() {
    int wrongBookingId = bookingId + Integer.MAX_VALUE;
    Response response =
        RestAssured.given().spec(requestSpec).when().delete("/booking/" + wrongBookingId);
    response.then().statusCode(HttpStatus.SC_NOT_FOUND);
  }
}
