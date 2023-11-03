package User;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserAction {
    public static ValidatableResponse createNewUser(User user) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/auth/register")
                .then().log().all();
    }

    public static ValidatableResponse authorizationUser(User userCredential) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(userCredential)
                .when()
                .post("/api/auth/login")
                .then().log().all();
    }

    public static ValidatableResponse deleteCreatedUser(String accessToken) {
        return given().log().all()
                .header("authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then().log().all();
    }
}
