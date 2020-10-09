package Testng;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Apichaining extends Practice
{
	
	String firstname;
	
@Test(priority=1)
void getreq()
{
	

	Response resp=RestAssured.get("https://reqres.in/api/users/2");
    int statuscode=resp.getStatusCode();
    String Statusbody=resp.asString();
    System.out.println("Statusbody is "+ Statusbody);
    System.out.println("Status code is "+ statuscode);
    Assert.assertEquals(statuscode, 200);
   	logger6.log(Status.PASS, "Get request is passed and status code is "+ statuscode);
   	
   	JsonPath path=resp.jsonPath();
   	
   	
   	firstname=path.getString("data.first_name");
    logger6.log(Status.PASS, "Firstname get from get request is "+ firstname);
    System.out.println("firstname is  "+ firstname);
}
	
@Test(priority=2)
void putreq()
{
	RequestSpecification request= RestAssured.given();
	request.header("Content-Type","application/json");
	
	JSONObject json= new JSONObject();
	
	json.put("name", firstname);
	json.put("job", "developer");
	
	request.body(json.toJSONString());
	Response response=request.post("https://reqres.in/api/users");
	int stat=response.getStatusCode();
	String body=response.asString();
	System.out.println("status code is "+ stat);
	System.out.println("Status body is "+ body);
	logger6.log(Status.PASS, "Put request is passed and status code is "+ stat);
	logger6.log(Status.PASS,"resource created with firstname "+ firstname);
	//extent.flush();
}
	
}
