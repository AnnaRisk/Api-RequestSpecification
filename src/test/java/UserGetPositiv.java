import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserGetPositiv {



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
                    .header("Content-Type", containsString("application/json"))
                    .body("$.size()", equalTo(20))
                    .body("[0].id", notNullValue());
        }

        @Test
        public void pathGetParams() {
            int existingUserId = 99;
            RestAssured
                    .given().log().all()
                    .pathParam("userId", existingUserId)
                    .when()
                    .get(USER_URL)
                    .then().log().all()
                    .statusCode(200)
                    .body("id", equalTo(existingUserId));
        }

        @Test
        public void queryGetParams() {
            Map<String, String> filter = new HashMap<>();
            filter.put("page", "1");


            RestAssured
                    .given().log().all()
                    .queryParams(filter)
                    .when()
                    .get(USERS_URL)
                    .then().log().all()
                    .statusCode(200)
                    .body("$.size()", equalTo(20));



        }
    @Test
    public void queryGetParams2() {
        Map<String, String> filter = new HashMap<>();
        filter.put("page", "25");


        RestAssured
                .given().log().all()
                .queryParams(filter)
                .when()
                .get(USERS_URL)
                .then().log().all()
                .statusCode(200)
                .body("$.size()", equalTo(20));



    }

    }

