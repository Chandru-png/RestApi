package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.qa.data.Users;


import restapi.TestBase;

public class PostApiTest  extends TestBase{
	
	
TestBase testBase;
	
	RestClient restClient;
	String apiurl;
	String serviceUrl;
	String url;
	
	CloseableHttpResponse closeableHttpResponse;
	
@BeforeMethod
	
	public void setUp() throws ParseException, IOException {
		// TODO Auto-generated method stub
		
		testBase = new TestBase();
		apiurl=prop.getProperty("URL");
		serviceUrl=prop.getProperty("serviceURL");
	
	url=apiurl+serviceUrl;

}

@Test

private void postApiTesst() throws JsonGenerationException, JsonMappingException, IOException {
	// TODO Auto-generated method stub
	
	restClient= new RestClient();
	HashMap<String, String> headerMap = new HashMap<String, String>();
	headerMap.put("Content-Type", "application/json");
	
	//jackson API:
			ObjectMapper mapper = new ObjectMapper();
			Users users=new Users("morpheus", "leader"); //expected users obejct
			mapper.writeValue(new File("C:\\Users\\itsupport\\eclipse-workspace\\RestApiTest\\src\\main\\java\\com\\qa\\data\\Users.File"), users);
			
			
			//object to json string
			
			String usersJsonString=mapper.writeValueAsString(users);
			System.out.println(usersJsonString);
			
			closeableHttpResponse=	restClient.post(url, usersJsonString, headerMap);
			
			//Status code
			
			int status=closeableHttpResponse.getStatusLine().getStatusCode();
			Assert.assertEquals(status, testBase.RESPONSE_STATUS_CODE_201);
			
			//json string
			
			String rsposnseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

JSONObject responJson=new JSONObject(rsposnseString);
System.out.println("the response from API" +responJson);

Users userObj=mapper.readValue(rsposnseString, Users.class);//actual userobject
System.out.println(userObj);
System.out.println(users.getName().equals(userObj.getName()));
System.out.println(users.getJob().equals(userObj.getJob()));
Assert.assertTrue(users.getName().equals(userObj.getName()));
Assert.assertTrue(users.getJob().equals(userObj.getJob()));
System.out.println(userObj.getId());
System.out.println(userObj.getCreatedAt());





}
} 