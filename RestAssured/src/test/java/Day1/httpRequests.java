package Day1;

import org.hamcrest.core.IsEqual;
import org.testng.annotations.Test;

import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class httpRequests {
	
	int id;
	
   @Test(priority = 0)
   void getUserById() {
	
	   when()
	   .get("https://reqres.in/api/users/2")
	   .then()
	   .statusCode(200)
	   .body("data.id",equalTo(2))
	   .log().all();
}
   
   @Test(priority = 1)
   public void createUser(){
	   HashMap postMap = new HashMap<String, Object>();
	   postMap.put("username", "Karthick");
	   postMap.put("job", "Senior Software Engineer");
	  
	   
	    id = given()
	   .body(postMap)
	   .contentType("application/json")
	   .when()
	   .post("https://reqres.in/api/users")
	   .jsonPath().getInt("id");
//	   .then()
//	   .statusCode(201)
//	   .log().all();
   }
   
   @Test(priority = 2,dependsOnMethods = {"createUser"})
   public void updateUser() {
	   HashMap putMap = new HashMap<String, Object>();
	   putMap.put("username", "Karthick");
	   putMap.put("job", "Technical Lead");
	   given()
	   .contentType("application/json")
	   .body(putMap)
	   
	   .when()
	   .put("https://reqres.in/api/users/"+id)
	   
	   .then()
	   .statusCode(200)
	   .log().all();
   }
   
   @Test(priority = 3)
   public void deleteUser() {
	
	   when()
	   .delete("https://reqres.in/api/users/"+id)
	   .then()
	   .statusCode(204);
	   
   }

}
