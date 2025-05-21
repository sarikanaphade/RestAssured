package Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import BaseClass.BaseTest;
import Utils.FileUtils;
import Utils.RestUtils;
import io.restassured.response.Response;

public class GetDataTests extends BaseTest{
	
	public static String token = null;
	
	@BeforeMethod
	public void setup1() {
		Response response = RestUtils.login(FileUtils.LoginPayload());
		token = RestUtils.getToken(response);
	}
	
	@Test(priority=3)
	public void test_validGetData() {
		Response response = RestUtils.getData(token);
		//System.out.println(response.prettyPrint());
		assertEquals(response.statusCode(),200); // if invalid token given status code should not be 200
		System.out.println(response.time());
		//assertTrue(response.time()<3000,"Response time is less than 20000ms");
	}
	
	@Test(priority=1)
	public void test_invalidGetData1() {
		token="abvc";
		Response response = RestUtils.getData(token);
		//System.out.println(response.prettyPrint());
		assertEquals(response.statusCode(),200); // if invalid token given status code should not be 200
		System.out.println(response.time());
		assertTrue(response.time()<3000,"Response time is less than 20000ms");
	}
	
	@Test(priority=2)
	public void test_invalidGetData2() {
		token="";
		Response response = RestUtils.getData(token);
		//System.out.println(response.prettyPrint());
		assertEquals(response.statusCode(),200); // if invalid token given status code should not be 200
		System.out.println(response.time());
		assertTrue(response.time()<3000,"Response time is less than 20000ms");
	}
}
