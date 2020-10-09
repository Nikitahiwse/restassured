package Testng;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Practice{
	
	 public static ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./Report/extent.html");
	 public static ExtentReports extent = new ExtentReports();
	 public static ExtentTest logger1;
	 public static ExtentTest logger2;
	 public static ExtentTest logger3;
	 public static ExtentTest logger4;
	 public static ExtentTest logger5;
	 public static ExtentTest logger6;
@BeforeSuite
void initialize()
{
	 extent.attachReporter(htmlReporter);
	logger1=extent.createTest("GetTest");
	logger2=extent.createTest("PostTest");
	logger3=extent.createTest("PutTest");
	logger4=extent.createTest("Delete");
	logger5=extent.createTest("PatchTest");
	logger6=extent.createTest("API Chaining Test");
}
{
	
}
@Test

void getrequest()
{
	Response res=RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02");
    int statuscode=res.getStatusCode();
    String Statusbody=res.asString();
    System.out.println("Statusbody is "+ Statusbody);
    System.out.println("Status code is "+ statuscode);
    Assert.assertEquals(statuscode, 200);
   	logger1.log(Status.PASS, "Get request is passed and status code is "+ statuscode);
   	
   	JsonPath path=res.jsonPath();
   	String cityname=path.get("name");
    
    System.out.println("name of city is "+ cityname);
}
//@Test
void postrequest()
{
	{
		RequestSpecification request= RestAssured.given();
		request.header("Content-Type","application/json");
		
		JSONObject json= new JSONObject();
		
		json.put("name", "nikita");
		json.put("job", "Tester");
		
		request.body(json.toJSONString());
		Response response=request.post("https://reqres.in/api/users");
		int stat=response.getStatusCode();
		String body=response.asString();
		System.out.println("status code is "+ stat);
		System.out.println("Status body is "+ body);
		logger2.log(Status.PASS, "Post request is passed and status code is "+ stat);
		
		
		
	}
	
	
}

//@Test
void postcreate()
{
	RequestSpecification request= RestAssured.given();
	request.header("Content-Type","application/json");
	
	JSONObject json= new JSONObject();
	
	json.put("first_name", "nika");
	json.put("last_name", "wankhede");
	json.put("email", "nikita.hiwse@gmail.com");
	json.put("pass", "4567");
	json.put("confirmPass", "4567");
	json.put("phone_no", "8788761097");
	json.put("gender", "female");
	
	request.body(json.toJSONString());
	Response response=request.post("http://180.149.241.208:3022/register");
	int stat=response.getStatusCode();
	String body=response.asString();
	System.out.println("status code is "+ stat);
	System.out.println("Status body is "+ body);
	logger2.log(Status.PASS, "Post request is passed and status code is "+ stat);
}

//@Test
void putupdate()
{
	RequestSpecification request= RestAssured.given();
	request.header("Content-Type","application/json");
	
	JSONObject json= new JSONObject();
	
	json.put("name", "nidhi");
	json.put("job", "developer");
	
	request.body(json.toJSONString());
	Response response=request.put("https://reqres.in/api/users/2");
	int stat=response.getStatusCode();
	String body=response.asString();
	System.out.println("status code is "+ stat);
	System.out.println("Status body is "+ body);
	logger3.log(Status.PASS, "Put request is passed and status code is "+ stat);
}
//@Test
void delete()
{
	RequestSpecification request= RestAssured.given();
	
	
	Response response=request.delete("https://reqres.in/api/users/2");
	int stat=response.getStatusCode();
	String body=response.asString();
	System.out.println("status code is "+ stat);
	System.out.println("Status body is "+ body);
	Assert.assertEquals(stat, 204);
	logger4.log(Status.PASS, "Delete request is passed and status code is "+ stat);
}

//@Test
void patch()
{
	RequestSpecification request= RestAssured.given();
	request.header("Content-Type","application/json");
	
	JSONObject json= new JSONObject();
	
	json.put("name", "nid");
	json.put("job", "dev");
	
	request.body(json.toJSONString());
	Response response=request.put("https://reqres.in/api/users/2");
	int stat=response.getStatusCode();
	String body=response.asString();
	System.out.println("status code is "+ stat);
	System.out.println("Status body is "+ body);
	logger5.log(Status.PASS, "Patch request is passed and status code is "+ stat);
}




@AfterSuite
void terminate()
{
	extent.flush();
	
}

}






