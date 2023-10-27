package User;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;

public class UserAssertion {
    public static boolean isAuthorizated(ValidatableResponse response) {
        ExtractableResponse result = response.extract();
        if (result.statusCode() == HttpURLConnection.HTTP_OK
                && (boolean) Optional.ofNullable(result.body().jsonPath().get("success")).get()) {
            return true;
        }
        return false;
    }

    public static void assertRequiredFieldsWrongFilledIn(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .body("message", equalTo("email or password are incorrect"));
    }

    public static void assertUserDelete(ValidatableResponse loginResponse) {
        loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_ACCEPTED)
                .body("message", equalTo("User successfully removed"));
    }

}
