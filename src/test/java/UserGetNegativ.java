import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;

public class UserGetNegativ {


    // Base url
    final String BASE_URL = "https://gorest.co.in/public/v2";

    // Endpoints
    final String USERS_URL = "/users";
    final String USER_URL = USERS_URL + "/{userId}";

    @BeforeEach
    void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void getMethod() {
        RestAssured
                .given().log().all()
                .when()
                .get(USERS_URL)
                .then().log().all()
                .statusCode(greaterThanOrEqualTo(200))
                .statusCode(lessThanOrEqualTo(299))
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Content-Type", containsString("application/xml"))
                .body("[0].id", nullValue());
    }

    @Test
    public void getMethod2() {
        String token = "9250be4657a5e41dd29064afb6f793d064a640b2165a98cdf5e3ee746ff50e33";
        String token2 = "YW5uYUBtYWlsLnJ1 ";
        RestAssured

                .given().log().all()
                .when()
                .get(USERS_URL)
                .then().log().all()
                .header("Authorization", "Bearer " + token)
                .header("Authorization", "Basic " + token2);

    }

    @Test
    public void pathParams() {
        int existingUserId = 101;
        RestAssured
                .given().log().all()
                .pathParam("userId", existingUserId)
                .when()
                .get(USER_URL)
                .then().log().all()
                .statusCode(404)
                .body("id", equalTo(existingUserId));
    }

    @Test
    public void pathParams2() {
        int existingUserId = -1;
        RestAssured
                .given().log().all()
                .pathParam("userId", existingUserId)
                .when()
                .get(USER_URL)
                .then().log().all()
                .statusCode(404)
                .body("id", equalTo(existingUserId));
    }

    @Test
    public void pathParams3() {
        int existingUserId = 999999999;
        RestAssured
                .given().log().all()
                .pathParam("userId", existingUserId)
                .when()
                .get(USER_URL)
                .then().log().all()
                .statusCode(404)
                .body("id", equalTo(existingUserId));
    }

    @Test
    public void pathParams4() {
        double existingUserId = 1.4;
        RestAssured
                .given().log().all()
                .pathParam("userId", existingUserId)
                .when()
                .get(USER_URL)
                .then().log().all()
                .statusCode(404)
                .body("id", equalTo(existingUserId));
    }

    @Test
    public void queryParams() {
        Map<String, String> filter = new HashMap<>();
        filter.put("page", "1");

        RestAssured
                .given().log().all()
                .queryParams(filter)
                .when()
                .get(USERS_URL)
                .then().log().all()
                .statusCode(200)
                .body("$.size()", equalTo(21));

    }

    @Test
    public void queryParams2() {
        Map<String, String> filter = new HashMap<>();
        filter.put("page", "-1");

        RestAssured
                .given().log().all()
                .queryParams(filter)
                .when()
                .get(USERS_URL)
                .then().log().all()
                .statusCode(200)
                .body("$.size()", notNullValue());

    }

    @Test
    public void queryParams3() {
        Map<String, String> filter = new HashMap<>();
        filter.put("page", "200");

        RestAssured
                .given().log().all()
                .queryParams(filter)
                .when()
                .get(USERS_URL)
                .then().log().all()
                .statusCode(200)
                .body("$.size()", notNullValue());

    }

    @Test
    public void queryParams4() {
        Map<String, String> filter = new HashMap<>();
        filter.put("page", "1.4");

        RestAssured
                .given().log().all()
                .queryParams(filter)
                .when()
                .get(USERS_URL)
                .then().log().all()
                .statusCode(200)
                .body("$.size()", notNullValue());

    }

}

