package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiHelper {
  public String generateToken() {
    Response response =
        RestAssured.given()
            .body(
                "{\n"
                    + "    \"username\" : \"admin\",\n"
                    + "    \"password\" : \"password123\"\n"
                    + "}")
            .contentType(ContentType.JSON)
            .when()
            .post("https://restful-booker.herokuapp.com/auth");
    return response.body().jsonPath().getString("token");
  }
}
