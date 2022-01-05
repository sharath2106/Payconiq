import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import utils.ApiHelper;

public class FirstTest {
  ApiHelper apiHelper = new ApiHelper();

  @Test
  void getBookingIds() {
    Response response =
        RestAssured.given().when().get("https://restful-booker.herokuapp.com/booking");
    response.then().statusCode(HttpStatus.SC_OK);
    response.body().prettyPrint();
  }

  @Test
  void getBooking() {
    Response response =
        RestAssured.given().when().get("https://restful-booker.herokuapp.com/booking/20");
    response.then().statusCode(HttpStatus.SC_OK);
  }

  @Test
  void createBooking() {
    Response response =
        RestAssured.given()
            .body(
                "{\n"
                    + "    \"firstname\" : \"Jim\",\n"
                    + "    \"lastname\" : \"Brown\",\n"
                    + "    \"totalprice\" : 111,\n"
                    + "    \"depositpaid\" : true,\n"
                    + "    \"bookingdates\" : {\n"
                    + "        \"checkin\" : \"2018-01-01\",\n"
                    + "        \"checkout\" : \"2019-01-01\"\n"
                    + "    },\n"
                    + "    \"additionalneeds\" : \"Breakfast\"\n"
                    + "}")
            .contentType(ContentType.JSON)
            .when()
            .post("https://restful-booker.herokuapp.com/booking");
    response.then().statusCode(HttpStatus.SC_OK);
    response.body().prettyPrint();
  }

  @Test
  void partialUpdateBooking() {
    String token = apiHelper.generateToken();
    Response response =
        RestAssured.given()
            .header("Cookie", "token=" + token)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(
                "{\n" + "    \"firstname\" : \"James\",\n" + "    \"lastname\" : \"Brown\"\n" + "}")
            .when()
            .patch("https://restful-booker.herokuapp.com/booking/14");
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
            .delete("https://restful-booker.herokuapp.com/booking/8");
    response.then().statusCode(HttpStatus.SC_CREATED);
  }
}
