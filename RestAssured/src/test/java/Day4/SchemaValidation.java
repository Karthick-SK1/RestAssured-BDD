package Day4;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import javax.xml.validation.Schema;

import static io.restassured.RestAssured.given;

public class SchemaValidation {

    @Test
    public void jsonSchemaTest(){
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("F:\\RestAssured\\RestAssured-BDD\\RestAssured\\src\\test\\resources\\Meta-data\\Json Files\\getUsersSchema.json"))
                .log().all();

    }
}
