package Day3;

import org.hamcrest.core.IsEqual;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class HeadersAndCookies {

	Response response;

	@Test
	public void testCookies() {

		response = given().when().get("https://www.google.com/");
		Map<String, String> cookies = response.getCookies();
		for (String key : cookies.keySet()) {
			response.getCookie(key);
			System.out.println(key + "  " + cookies);
		}
	}
		
		@Test
		public void testHeaders() {
			response = given()
					.when()
					.get("https://www.google.com/");
					Headers headers = response.getHeaders();
					for(Header h : headers) {
						System.out.println(h);
					}
	
		}

	}


