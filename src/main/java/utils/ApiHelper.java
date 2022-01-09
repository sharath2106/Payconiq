package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Login;

public class ApiHelper {
  public String generateToken() {
    Login loginRequestBody =
        Login.builder().username(System.getenv("user")).password(System.getenv("password")).build();
    Response response =
        RestAssured.given()
            .body(loginRequestBody)
            .contentType(ContentType.JSON)
            .when()
            .post("https://restful-booker.herokuapp.com/auth");
    return response.then().extract().path("token");
  }
}
