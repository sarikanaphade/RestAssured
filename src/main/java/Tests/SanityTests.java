package Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Utils.FileUtils;
import Utils.RestUtils;
import io.restassured.response.Response;

public class SanityTests extends BaseTest{
	
	@Test
	public void test_GetData() {
		Response response = RestUtils.getData();
		System.out.println(response.prettyPrint());
		assertEquals(response.statusCode(),200);
		System.out.println(response.time());
		assertTrue(response.time()<20000,"Response time is less than 20000ms");
	}
	
	@Test
	public void test_AddData() {
		Response response = RestUtils.addData(FileUtils.addDataPayload(), FileUtils.readData().getProperty("addData"));
		System.out.println(response.prettyPrint());
		assertEquals(response.statusCode(),201);
	}
	
	@Test
	public void test_logout() {
		Response response = RestUtils.Logout(FileUtils.readData().getProperty("logout"));
		System.out.println(response.prettyPrint());
		assertEquals(response.statusCode(),200);
	}
}
