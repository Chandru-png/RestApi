package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	
	
	
	public CloseableHttpResponse get(String url) throws ParseException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		
	CloseableHttpResponse clossableHttpResponse=	httpClient.execute(httpget);
	
	return clossableHttpResponse;
	
	}
	
	//2. GET Method with Headers:
			public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url); //http get request

			for(Map.Entry<String,String> entry : headerMap.entrySet()){
				httpget.addHeader(entry.getKey(), entry.getValue());
			}
			CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL
			return closebaleHttpResponse;

			}
			
			
			//3.post method
			
			public CloseableHttpResponse post(String url, String entityString, HashMap<String,String>headerMap) throws ClientProtocolException, IOException {
				
				CloseableHttpClient httpClient = HttpClients.createDefault();
			
				HttpPost httpPost= new HttpPost(url);
				
				httpPost.setEntity(new StringEntity(entityString));//for payload
				
				for(Map.Entry<String,String> entry : headerMap.entrySet()){
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
				CloseableHttpResponse closebaleHttpResponse=httpClient.execute(httpPost);
				return closebaleHttpResponse;

				
			}
}