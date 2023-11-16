package Day4;

import org.hamcrest.core.IsEqual;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.internal.http.Status;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class JsonResponseValidations {
	
	 @Test
	    public void normalValidation() {

	        given()
	            .contentType(ContentType.JSON)
	        .when()
	            .get("https://reqres.in/api/users?page=2")
	        .then()
	            .statusCode(200)
	            .body("data[4].first_name", equalTo("George"))
	            .body("data[4].last_name", equalTo("Edwards"))
	            .body("data[4].email", equalTo("george.edwards@reqres.in"))
	            .log().all();
	    }
	 
	 
	 @Test 
	 public void usingResponseObject() {
		 
		 Response response;
		response =  given()
		.contentType(ContentType.JSON)
		 .when()
		 .get("https://reqres.in/api/users?page=2");
		
		int actualStatusCode = response.getStatusCode();
		
		String actualEmail = response.jsonPath().get("data[4].email").toString();
		Assert.assertEquals(actualStatusCode, 200);
		Assert.assertEquals(actualEmail,"george.edwards@reqres.in");
		String data = response.jsonPath().getJsonObject("data").toString();
		System.out.println(data + "\n");
	 }
	 
	 @Test
	 public void usingJsonObject() {
		 
		 Response response = given()
		 .contentType(ContentType.JSON)
		 .when()
		 .get("https://reqres.in/api/users?page=2");
		JSONObject jsonObject = new JSONObject(response.asString());
	
		 for(int k = 0;k <jsonObject.getJSONArray("data").length();k++) {
			 String email = jsonObject.getJSONArray("data").getJSONObject(k).get("email").toString();
			 System.out.println("Email : "+ email);		 
		 }

	 }
	}
