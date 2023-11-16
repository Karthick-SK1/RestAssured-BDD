package Day4;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UploadAndDownload {

    @Test
    public void singleFileUpload() {
        File file = new File("C:\\Users\\karthick.sha\\Downloads\\Test.txt");
        given()
                .multiPart("file", file)
                .contentType("multipart/form-data")
                .when()
                .post("https://v2.convertapi.com/upload")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test
    public void download() {
        given()
                .when()
                .get("https://v2.convertapi.com/d/3ivoofxt6eqns3d52y9expl17w1uxzfs")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void attributeTest(ITestContext context){
        int userid = (int) context.getSuite().getAttribute("user_id");
        System.out.println(userid);
    }


}
