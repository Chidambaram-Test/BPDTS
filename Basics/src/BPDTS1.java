import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BPDTS1 {

// bpdtsTest1 to test number of users in City London
    @Test
	public void bpdtsTest1()
	{
		RestAssured.baseURI="http://bpdts-test-app-v2.herokuapp.com";
		
		String UsersResponse = given().log().all().when().get("city/London/users").then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath Js1 =new JsonPath(UsersResponse);
		
		int count =Js1.getInt("array.size");
		System.out.println(count);
		Assert.assertEquals(count,6);
		
		String Res1 =Js1.getString("first_name[0]");
		System.out.println(Res1);
		Assert.assertEquals(Res1,"Mechelle");
	}
    
// bpdtsTest2 to test the instructions
	@Test
	public void bpdtsTest2()
	{	
		
		given().log().all().when().get("instructions").then().log().all().assertThat().statusCode(200)
		.body("todo",equalTo("Create a short automated test for this API. Check that the data returned by the API is valid, and that ensure that each valid operation can be successfully called for each endpoint. Once you've built the tests, push the answer to Github or Gitlab, and send us a link. "));
	}
	
// bpdtsTest3 to test the specific user id = 688
	@Test
	public void bpdtsTest3()
	{	
		given().log().all().when().get("user/688").then().log().all().assertThat().statusCode(200)
		.body("email",equalTo("tcolbertsonj3@vimeo.com"));
	}

// bpdtsTest4 to test all the users	
	@Test
	public void bpdtsTest4()
	{	
		String Users=given().log().all().when().get("users").then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath Js2=new JsonPath(Users);
		int count1 =Js2.getInt("array.size");
		System.out.println(count1);
		Assert.assertEquals(count1, 1000);
		
		String Res2 =Js2.getString("first_name[1]");
		System.out.println(Res2);
		Assert.assertEquals(Res2,"Bendix");
	}	
}
