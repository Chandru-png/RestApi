package restAp;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Post_Delete_PutExample {
	
	@Test
	public void test1() {
		// TODO Auto-generated method stub
		
		
		RequestSpecification request=RestAssured.given();
		request.header("Content-Type", "application/json");
		JSONObject json= new JSONObject();
		
		json.put("id", "25");
		json.put("title", "APITest_Webdriver");
		json.put("author", "Chandru");
		
		request.body(json.toJSONString());
	Response response=	request.post("http://localhost:3000/posts");
		
	int status=response.getStatusCode();
	Assert.assertEquals(status, 201);
		

	}

}
