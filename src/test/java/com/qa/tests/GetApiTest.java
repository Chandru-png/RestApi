package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.util.TestUtil;

import restapi.TestBase;

public class GetApiTest extends TestBase {
	


	TestBase testBase;
	
	RestClient restClient;
	String apiurl;
	String serviceUrl;
public	JSONObject responseJson;
	
	CloseableHttpResponse clossableHttpResponse;
	
	String url;
	@BeforeMethod
	
	public void setUp() throws ParseException, IOException {
		// TODO Auto-generated method stub
		
		testBase = new TestBase();
		apiurl=prop.getProperty("URL");
		serviceUrl=prop.getProperty("serviceURL");
	
	url=apiurl+serviceUrl;
	
	
	
		

	}
	
	@Test
	
	public void getTestwithoutHeaders() throws ParseException, IOException {
		// TODO Auto-generated method stub
		 restClient=new RestClient();
		 clossableHttpResponse	=restClient.get(url);
			
			int statusCode=clossableHttpResponse.getStatusLine().getStatusCode();
			
			System.out.println("Status Code" +statusCode);
			
			Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200, "Status code is not 200");
			
			String responseString=EntityUtils.toString(clossableHttpResponse.getEntity(), "UTF-8");
			
			 responseJson= new JSONObject(responseString);
			
			System.out.println("Response json from API" +responseJson);
			
		String perpage=	TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("per page value" +perpage);
		Assert.assertEquals(Integer.parseInt(perpage), 6);
		
		String perpage1=	TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("per page value" +perpage1);
		Assert.assertEquals(Integer.parseInt(perpage1), 12);
		
		String perpage2=	TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String perpage3=	TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String perpage4=	TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String perpage5=	TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println(perpage2);
		
		System.out.println(perpage3);
		System.out.println(perpage4);
		System.out.println(perpage5);
		Assert.assertEquals(perpage5, "George");
		
		
		
			Header[] headerArray=clossableHttpResponse.getAllHeaders();
			
			HashMap<String,String> allHeaders=new HashMap<String,String>();
			
			
			for(Header header :headerArray )
			{
				allHeaders.put(header.getName(), header.getValue());
				
			}
			
			
			System.out.println("Headers Array" +allHeaders);
			
			
			
			
			
			
	}
	
	
	@Test
	public void getTestwithHeaders() throws ParseException, IOException {
		// TODO Auto-generated method stub
		 restClient=new RestClient();
		 clossableHttpResponse	=restClient.get(url);
			
		 HashMap<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Content-Type", "application/json");
//			headerMap.put("username", "test@amazon.com");
//			headerMap.put("password", "test213");
//			headerMap.put("Auth Token", "12345");

			int statusCode=clossableHttpResponse.getStatusLine().getStatusCode();
			
			System.out.println("Status Code" +statusCode);
			
			Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200, "Status code is not 200");
			
			String responseString=EntityUtils.toString(clossableHttpResponse.getEntity(), "UTF-8");
			
			 responseJson= new JSONObject(responseString);
			
			System.out.println("Response json from API" +responseJson);
			
		String perpage=	TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("per page value" +perpage);
		Assert.assertEquals(Integer.parseInt(perpage), 6);
		
		String perpage1=	TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("per page value" +perpage1);
		Assert.assertEquals(Integer.parseInt(perpage1), 12);
		
		String perpage2=	TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String perpage3=	TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String perpage4=	TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String perpage5=	TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println(perpage2);
		
		System.out.println(perpage3);
		System.out.println(perpage4);
		System.out.println(perpage5);
		Assert.assertEquals(perpage5, "George");
		
		
		
			Header[] headerArray=clossableHttpResponse.getAllHeaders();
			
			HashMap<String,String> allHeaders=new HashMap<String,String>();
			
			
			for(Header header :headerArray )
			{
				allHeaders.put(header.getName(), header.getValue());
				
			}
			
			
			System.out.println("Headers Array" +allHeaders);
	}
}

