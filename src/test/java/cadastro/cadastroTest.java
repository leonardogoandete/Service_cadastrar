package cadastro;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class cadastroTest{

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/q/health")
          .then()
             .statusCode(200);
    }

}