import io.restassured.RestAssured;
import io.restassured.http.Header;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasEntry;

public class UserPostPositive {


    // Base url
    final String BASE_URL = "https://gorest.co.in/public/v2";

    // Endpoints
    final String USERS_URL = "/users";
    final String COMMENTS_URL = "posts/99/comments";
    final String USER_URL = USERS_URL + "/{userId}";

    @BeforeEach
    void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    void postHeaderObject() {
        User user = User.builder()
                .name("Anna Rain")
                .email("testanna2@mail.ru")
                .status("active")
                .gender("female")
                .build();

        Header header = new Header("Authorization", "Bearer 9250be4657a5e41dd29064afb6f793d064a640b2165a98cdf5e3ee746ff50e33");
        RestAssured
                .given().log().all()
                .body(user)
                .header(header)
                .when()
                .post(USERS_URL)
                .then().log().all()
                .statusCode(201);
    }

    @Test
    void postRequestObject() {
        User user = User.builder()
                .name("Anna Rain")
                .email("testanna@mail.ru")
                .status("active")
                .gender("female")
                .build();

        RestAssured
                .given().log().all()
                .body(user)
                .when()
                .post(USERS_URL)
                .then().log().all()
                .statusCode(200)
                .body("$", everyItem(hasEntry("status", "active")))
                .body("$", everyItem(hasEntry("gender", "female")));
    }
    @Test
    void postHeader() {
        User user = User.builder()
                .name("Anna Rain")
                .email("testanna2@mail.ru")
                .status("active")
                .gender("female")
                .build();

        RestAssured
                .given().log().all()
                .header("Authorization", "Bearer 9250be4657a5e41dd29064afb6f793d064a640b2165a98cdf5e3ee746ff50e33")
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(USERS_URL)
                .then().log().all()
                .statusCode(201);
    }

    @Test
    void postAuthorization() {
        User user = User.builder()
                .name("Anna Rain")
                .email("testanna2@mail.ru")
                .status("active")
                .gender("female")
                .build();

        RestAssured
                .given().log().all()
                .auth().oauth2("Bearer 9250be4657a5e41dd29064afb6f793d064a640b2165a98cdf5e3ee746ff50e33")
                .body(user)
                .when()
                .post(USERS_URL)
                .then().log().all()
                .statusCode(200)
                .body("$", everyItem(hasEntry("status", "active")))
                .body("$", everyItem(hasEntry("gender", "female")));
    }

    @Test
    void postRequestString() {
        String json = "{" +
                "\"name\": \"Anna Rain\"," +
                "\"post_id\"\": \"100\"," +
                "\"email\": \"testanna2@mail.ru\"," +
                "\"body\": \"Facilis sequi laudantium. Eos nobis optio\"" +
                "}";


        RestAssured
                .given().log().all()
                .body(json)
                .when()
                .post(COMMENTS_URL)
                .then().log().all()
                .statusCode(200)
                .body("$", everyItem(hasEntry("email", "testanna2@mail.ru")))
                .body("$", everyItem(hasEntry("name", "Anna Rain")));
    }


}

