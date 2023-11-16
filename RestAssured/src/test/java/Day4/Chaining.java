package Day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import javax.naming.Context;

import static io.restassured.RestAssured.*;

public class Chaining {

    @Test
    public void simpleChaining(ITestContext context){

       int id = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("[7].id");
                context.getSuite().setAttribute("user_id",id);
        System.out.println(id);
    }
}
