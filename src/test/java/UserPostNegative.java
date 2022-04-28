import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

public class UserPostNegative {


    // Base url
    final String BASE_URL = "https://gorest.co.in/public/v2";

    // Endpoints
    final String USERS_URL = "/users";
    final String USER_URL = USERS_URL + "/{userId}";
    final String COMMENTS_URL = "posts/99/comments";

    @BeforeEach
    void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    void postRequestString() {
        String json = "{" +
                "\"name\": \"Anna Rain\"," +
                "\"post_id\"\": \"100\"," +
                "\"email\": \"testanna@mail.ru\"," +
                "\"body\": \"Facilis sequi laudantium. Eos nobis optio\"" +
                "}";


        RestAssured
                .given().log().all()
                .body(json)
                .when()
                .post(COMMENTS_URL)
                .then().log().all()
                .statusCode(200)
                .body("$", everyItem(hasEntry("email", "testanna!!!mail.ru")))
                .body("$", everyItem(hasEntry("name", "AnnaRainNegative")));
    }
    @Test
    void postHeaderObject() {
        Header header = new Header("Authorization", "Bearer test");
        RestAssured
                .given().log().all()
                .header(header)
                .when()
                .post(USERS_URL)
                .then().log().all()
                .statusCode(401);
    }


}

