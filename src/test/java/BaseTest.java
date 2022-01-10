import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.Booking;
import model.BookingDates;
import model.CreateBooking;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.ApiHelper;

public class BaseTest {

  protected static RequestSpecification requestSpec;
  protected int bookingId;
  protected Faker faker = Faker.instance();
  protected static String BOOKING_API = "/booking/";

  @BeforeAll
  static void beforeAll() {
    RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    String token = "token=" + new ApiHelper().generateToken();
    requestSpec =
        new RequestSpecBuilder()
            .setAccept("application/json")
            .setContentType("application/json")
            .addHeader("Cookie", token)
            .build();
  }

  @BeforeEach
  void beforeEach() {
    createBooking();
  }

  private void createBooking() {
    String firstName = "Jim";
    String lastName = "Brown";
    int totalPrice = 111;
    String checkinDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    String checkoutDate = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
    String additionalNeeds = "Breakfast";
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
    Response response =
        RestAssured.given().spec(requestSpec).body(booking).when().post(BOOKING_API);

    assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
    CreateBooking createBooking = response.as(CreateBooking.class);
    assertThat(createBooking.getBooking().getFirstname()).isEqualTo(firstName);
    assertThat(createBooking.getBooking().getLastname()).isEqualTo(lastName);
    assertThat(createBooking.getBooking().getTotalprice()).isEqualTo(totalPrice);
    assertThat(createBooking.getBooking().getBookingdates().checkin).isEqualTo(checkinDate);
    assertThat(createBooking.getBooking().getBookingdates().checkout).isEqualTo(checkoutDate);
    assertThat(createBooking.getBooking().getAdditionalneeds()).isEqualTo(additionalNeeds);
    bookingId = createBooking.getBookingid();
  }
}
