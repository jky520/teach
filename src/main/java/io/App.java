package io;

import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.fabric.Response;

public class App {
	static String BASE_URL = "http://127.0.0.1:8888";
	public static void main(String[] args) {
		RestTemplate rt = new RestTemplate();
		String body = rt.getForObject(BASE_URL + "/soa/product/1", String.class);
		System.out.println(body);
		//Response resp = new Gson().fromJson(body, Response.class);
		//System.out.println(resp.getResultSet());
		JsonObject returnData = new JsonParser().parse(body).getAsJsonObject();
		System.out.println(new JsonParser().parse(returnData.get("product").toString()).getAsJsonObject().get("pid"));
	}
}
