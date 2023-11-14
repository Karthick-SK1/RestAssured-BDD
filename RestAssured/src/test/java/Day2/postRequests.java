package Day2;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import Pojo.PojoClass;

import org.hamcrest.core.IsEqual;
import org.json.JSONObject;
import org.json.JSONTokener;

import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class postRequests {

	int id;
	String token;

	/**
	 * Using HashMap but this is not recommended as the values are hard-coded
	 * 
	 * @author ksha
	 *
	 */
	@Test
	public void using_HashMap() {
		HashMap postMap = new HashMap<String, Object>();
		postMap.put("email", "eve.holt@reqres.in");
		postMap.put("password", "GodofWar");
		id = given().body(postMap).contentType("application/json").when().post("https://reqres.in/api/register")
				.jsonPath().getInt("id");
		System.out.println("id for the registered user is : " + id);

	}

	/**
	 * Using JSONObject
	 * 
	 * @author ksha
	 *
	 */
	@Test
	public void using_JSONObject() {
		JSONObject postMap = new JSONObject();
		postMap.put("email", "eve.holt@reqres.in");
		postMap.put("password", "GodofWar");
		id = given().body(postMap.toString()).contentType("application/json").when()
				.post("https://reqres.in/api/register").jsonPath().getInt("id");
		System.out.println("id for the registered user is : " + id);

	}

	/**
	 * Using Pojo Class
	 * 
	 * @author ksha
	 *
	 */
	@Test
	public void using_Pojo() {
		PojoClass postMap = new PojoClass();
		postMap.setName("Khalid");
		postMap.setJob("Software Engineer");
		id = given().body(postMap).contentType("application/json").when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");
		System.out.println("id for the registered user is : " + id);

	}

	/**
	 * Using Json Files
	 * 
	 * @author ksha
	 * @throws FileNotFoundException
	 *
	 */
	@Test
	public void using_Json() throws FileNotFoundException {

		File jsonFile = new File(
				"C:\\Users\\wwwka\\eclipse-workspace\\RestAssured\\src\\test\\resources\\Meta-data\\Json Files\\login.json");
		FileReader read = new FileReader(jsonFile);
		JSONTokener token = new JSONTokener(read);
		JSONObject postMap = new JSONObject(token);

		id = given().body(postMap.toString()).contentType("application/json").when().post("https://reqres.in/api/users")
				.jsonPath().getInt("id");
		System.out.println("id for the registered user is : " + id);

	}

}
